class QuantityLength {
    final double value;
    final LengthUnit unit;
    
    QuantityLength(double v, LengthUnit u) {
        if (u == null || !Double.isFinite(v)) throw new IllegalArgumentException();
        value = v;
        unit = u;
    }
    
    static double convert(double v, LengthUnit s, LengthUnit t) {
        if (s == null || t == null || !Double.isFinite(v)) throw new IllegalArgumentException();
        double baseValue = s.convertToBaseUnit(v);
        return t.convertFromBaseUnit(baseValue);
    }
    
    QuantityLength to(LengthUnit t) {
        return new QuantityLength(convert(value, unit, t), t);
    }
    
    static QuantityLength add(QuantityLength q1, QuantityLength q2) {
        if (q1 == null || q2 == null) throw new IllegalArgumentException();
        double sumInBase = q1.unit.convertToBaseUnit(q1.value) + q2.unit.convertToBaseUnit(q2.value);
        return new QuantityLength(q1.unit.convertFromBaseUnit(sumInBase), q1.unit);
    }
    
    static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        return add(new QuantityLength(v1, u1), new QuantityLength(v2, u2));
    }
    
    QuantityLength add(QuantityLength other) {
        return add(this, other);
    }
    
    private static QuantityLength addWithTargetUnit(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
        double sumInBase = q1.unit.convertToBaseUnit(q1.value) + q2.unit.convertToBaseUnit(q2.value);
        return new QuantityLength(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }
    
    static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
        return addWithTargetUnit(q1, q2, targetUnit);
    }
    
    static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit targetUnit) {
        return add(new QuantityLength(v1, u1), new QuantityLength(v2, u2), targetUnit);
    }
    
    QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        return add(this, other, targetUnit);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantityLength)) return false;
        QuantityLength q = (QuantityLength) o;
        return Math.abs(unit.convertToBaseUnit(value) - q.unit.convertToBaseUnit(q.value)) < 1e-5;
    }
    
    @Override
    public String toString() {
        return value == (int)value ? value + " " + unit : String.format("%.2f %s", value, unit);
    }
}
