public class Quantity<U extends IMeasurable> {
    public final double value;
    public final U unit;

    public Quantity(double v, U u) {
        if (u == null || !Double.isFinite(v)) throw new IllegalArgumentException();
        value = v;
        unit = u;
    }

    public static <T extends IMeasurable> double convert(double v, T s, T t) {
        if (s == null || t == null || !Double.isFinite(v)) throw new IllegalArgumentException();
        double baseValue = s.convertToBaseUnit(v);
        return t.convertFromBaseUnit(baseValue);
    }

    public Quantity<U> to(U targetUnit) {
        return new Quantity<>(convert(value, unit, targetUnit), targetUnit);
    }

    public static <T extends IMeasurable> Quantity<T> add(Quantity<T> q1, Quantity<T> q2) {
        if (q1 == null || q2 == null) throw new IllegalArgumentException();
        double sumInBase = q1.unit.convertToBaseUnit(q1.value) + q2.unit.convertToBaseUnit(q2.value);
        return new Quantity<>(q1.unit.convertFromBaseUnit(sumInBase), q1.unit);
    }

    public static <T extends IMeasurable> Quantity<T> add(double v1, T u1, double v2, T u2) {
        return add(new Quantity<>(v1, u1), new Quantity<>(v2, u2));
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(this, other);
    }

    private static <T extends IMeasurable> Quantity<T> addWithTargetUnit(Quantity<T> q1, Quantity<T> q2, T targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
        double sumInBase = q1.unit.convertToBaseUnit(q1.value) + q2.unit.convertToBaseUnit(q2.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    public static <T extends IMeasurable> Quantity<T> add(Quantity<T> q1, Quantity<T> q2, T targetUnit) {
        return addWithTargetUnit(q1, q2, targetUnit);
    }

    public static <T extends IMeasurable> Quantity<T> add(double v1, T u1, double v2, T u2, T targetUnit) {
        return add(new Quantity<>(v1, u1), new Quantity<>(v2, u2), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        return add(this, other, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(this, other);
    }

    public static <T extends IMeasurable> Quantity<T> subtract(Quantity<T> q1, Quantity<T> q2) {
        if (q1 == null || q2 == null) throw new IllegalArgumentException();
        double diffInBase = q1.unit.convertToBaseUnit(q1.value) - q2.unit.convertToBaseUnit(q2.value);
        return new Quantity<>(q1.unit.convertFromBaseUnit(diffInBase), q1.unit);
    }

    public static <T extends IMeasurable> Quantity<T> subtract(double v1, T u1, double v2, T u2) {
        return subtract(new Quantity<>(v1, u1), new Quantity<>(v2, u2));
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        return subtract(this, other, targetUnit);
    }

    public static <T extends IMeasurable> Quantity<T> subtract(Quantity<T> q1, Quantity<T> q2, T targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
        double diffInBase = q1.unit.convertToBaseUnit(q1.value) - q2.unit.convertToBaseUnit(q2.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(diffInBase), targetUnit);
    }

    public static <T extends IMeasurable> Quantity<T> subtract(double v1, T u1, double v2, T u2, T targetUnit) {
        return subtract(new Quantity<>(v1, u1), new Quantity<>(v2, u2), targetUnit);
    }

    public double divide(Quantity<U> other) {
        return divide(this, other);
    }

    public static <T extends IMeasurable> double divide(Quantity<T> q1, Quantity<T> q2) {
        if (q1 == null || q2 == null) throw new IllegalArgumentException();
        if (Math.abs(q2.unit.convertToBaseUnit(q2.value)) < 1e-10) throw new ArithmeticException("Division by zero");
        return q1.unit.convertToBaseUnit(q1.value) / q2.unit.convertToBaseUnit(q2.value);
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
