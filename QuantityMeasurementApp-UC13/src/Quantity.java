import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {
    public final double value;
    public final U unit;

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (Math.abs(b) < 1e-10) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator compute;

        ArithmeticOperation(DoubleBinaryOperator compute) {
            this.compute = compute;
        }

        public double applyAsDouble(double left, double right) {
            return compute.applyAsDouble(left, right);
        }
    }

    public Quantity(double v, U u) {
        if (u == null || !Double.isFinite(v)) throw new IllegalArgumentException();
        value = v;
        unit = u;
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass())) throw new IllegalArgumentException("Incompatible measurement categories");
        if (!Double.isFinite(other.value)) throw new IllegalArgumentException("Other value must be finite");
        if (targetUnitRequired && targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (targetUnit != null && !targetUnit.getClass().equals(this.unit.getClass())) throw new IllegalArgumentException("Target unit category mismatch");
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return operation.applyAsDouble(thisBase, otherBase);
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static <T extends IMeasurable> double convert(double v, T s, T t) {
        if (s == null || t == null || !Double.isFinite(v)) throw new IllegalArgumentException();
        double baseValue = s.convertToBaseUnit(v);
        return t.convertFromBaseUnit(baseValue);
    }

    public Quantity<U> to(U targetUnit) {
        return new Quantity<>(convert(value, unit, targetUnit), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        double resultInBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double resultInTarget = roundToTwoDecimals(this.unit.convertFromBaseUnit(resultInBase));
        return new Quantity<>(resultInTarget, this.unit);
    }

    public static <T extends IMeasurable> Quantity<T> add(Quantity<T> q1, Quantity<T> q2) {
        return q1.add(q2);
    }

    public static <T extends IMeasurable> Quantity<T> add(double v1, T u1, double v2, T u2) {
        return add(new Quantity<>(v1, u1), new Quantity<>(v2, u2));
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double resultInBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double resultInTarget = roundToTwoDecimals(targetUnit.convertFromBaseUnit(resultInBase));
        return new Quantity<>(resultInTarget, targetUnit);
    }

    public static <T extends IMeasurable> Quantity<T> add(Quantity<T> q1, Quantity<T> q2, T targetUnit) {
        return q1.add(q2, targetUnit);
    }

    public static <T extends IMeasurable> Quantity<T> add(double v1, T u1, double v2, T u2, T targetUnit) {
        return add(new Quantity<>(v1, u1), new Quantity<>(v2, u2), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        double resultInBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double resultInTarget = roundToTwoDecimals(this.unit.convertFromBaseUnit(resultInBase));
        return new Quantity<>(resultInTarget, this.unit);
    }

    public static <T extends IMeasurable> Quantity<T> subtract(Quantity<T> q1, Quantity<T> q2) {
        return q1.subtract(q2);
    }

    public static <T extends IMeasurable> Quantity<T> subtract(double v1, T u1, double v2, T u2) {
        return subtract(new Quantity<>(v1, u1), new Quantity<>(v2, u2));
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double resultInBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double resultInTarget = roundToTwoDecimals(targetUnit.convertFromBaseUnit(resultInBase));
        return new Quantity<>(resultInTarget, targetUnit);
    }

    public static <T extends IMeasurable> Quantity<T> subtract(Quantity<T> q1, Quantity<T> q2, T targetUnit) {
        return q1.subtract(q2, targetUnit);
    }

    public static <T extends IMeasurable> Quantity<T> subtract(double v1, T u1, double v2, T u2, T targetUnit) {
        return subtract(new Quantity<>(v1, u1), new Quantity<>(v2, u2), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    public static <T extends IMeasurable> double divide(Quantity<T> q1, Quantity<T> q2) {
        return q1.divide(q2);
    }

    public static <T extends IMeasurable> double divide(double v1, T u1, double v2, T u2) {
        return divide(new Quantity<>(v1, u1), new Quantity<>(v2, u2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;
        Quantity<?> that = (Quantity<?>) o;
        if (!this.unit.getClass().equals(that.unit.getClass())) return false;
        return Math.abs(unit.convertToBaseUnit(value) - that.unit.convertToBaseUnit(that.value)) < 1e-5;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return value == (int)value ? value + " " + unit.getUnitName() : String.format("%.2f %s", value, unit.getUnitName());
    }
}
