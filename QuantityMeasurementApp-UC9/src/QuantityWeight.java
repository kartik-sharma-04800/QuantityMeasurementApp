class QuantityWeight {
    final double value;
    final WeightUnit unit;

    QuantityWeight(double v, WeightUnit u) {
        if (u == null || !Double.isFinite(v)) throw new IllegalArgumentException();
        value = v;
        unit = u;
    }

    static double convert(double v, WeightUnit s, WeightUnit t) {
        if (s == null || t == null || !Double.isFinite(v)) throw new IllegalArgumentException();
        double baseValue = s.convertToBaseUnit(v);
        return t.convertFromBaseUnit(baseValue);
    }

    QuantityWeight to(WeightUnit t) {
        return new QuantityWeight(convert(value, unit, t), t);
    }

    static QuantityWeight add(QuantityWeight q1, QuantityWeight q2) {
        if (q1 == null || q2 == null) throw new IllegalArgumentException();
        double sumInBase = q1.unit.convertToBaseUnit(q1.value) + q2.unit.convertToBaseUnit(q2.value);
        return new QuantityWeight(q1.unit.convertFromBaseUnit(sumInBase), q1.unit);
    }

    static QuantityWeight add(double v1, WeightUnit u1, double v2, WeightUnit u2) {
        return add(new QuantityWeight(v1, u1), new QuantityWeight(v2, u2));
    }

    QuantityWeight add(QuantityWeight other) {
        return add(this, other);
    }

    private static QuantityWeight addWithTargetUnit(QuantityWeight q1, QuantityWeight q2, WeightUnit targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
        double sumInBase = q1.unit.convertToBaseUnit(q1.value) + q2.unit.convertToBaseUnit(q2.value);
        return new QuantityWeight(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    static QuantityWeight add(QuantityWeight q1, QuantityWeight q2, WeightUnit targetUnit) {
        return addWithTargetUnit(q1, q2, targetUnit);
    }

    static QuantityWeight add(double v1, WeightUnit u1, double v2, WeightUnit u2, WeightUnit targetUnit) {
        return add(new QuantityWeight(v1, u1), new QuantityWeight(v2, u2), targetUnit);
    }

    QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        return add(this, other, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantityWeight)) return false;
        QuantityWeight q = (QuantityWeight) o;
        return Math.abs(unit.convertToBaseUnit(value) - q.unit.convertToBaseUnit(q.value)) < 1e-5;
    }

    @Override
    public String toString() {
        return value == (int)value ? value + " " + unit : String.format("%.2f %s", value, unit);
    }
}
