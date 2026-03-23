enum LengthUnit implements IMeasurable {
    FEET(1),
    INCHES(1.0 / 12),
    YARDS(3),
    CM(0.0328084),
    CENTIMETERS(0.0328084);

    final double factor;

    LengthUnit(double f) {
        factor = f;
    }

    double getFactor() {
        return factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException();
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        if (!Double.isFinite(baseValue)) throw new IllegalArgumentException();
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}
