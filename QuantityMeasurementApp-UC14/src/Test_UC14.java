class Test_UC14 {
    public static void main(String[] args) {
        System.out.println("Testing UC14 temperature operations...");

        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> t3 = new Quantity<>(273.15, TemperatureUnit.KELVIN);

        assert t1.equals(t2);
        assert t1.equals(t3);
        assert Math.abs(t1.to(TemperatureUnit.FAHRENHEIT).value - 32.0) < 1e-6;
        assert Math.abs(t1.to(TemperatureUnit.KELVIN).value - 273.15) < 1e-6;
        assert Math.abs(t2.to(TemperatureUnit.CELSIUS).value - 0.0) < 1e-6;
        assert Math.abs(t3.to(TemperatureUnit.CELSIUS).value - 0.0) < 1e-6;

        try {
            t1.add(t2);
            assert false;
        } catch (UnsupportedOperationException e) {
            assert e.getMessage().contains("addition");
        }

        try {
            t1.subtract(t2);
            assert false;
        } catch (UnsupportedOperationException e) {
            assert e.getMessage().contains("subtraction");
        }

        try {
            t1.divide(t2);
            assert false;
        } catch (UnsupportedOperationException e) {
            assert e.getMessage().contains("division");
        }

        assert !t1.equals(new Quantity<>(1.0, LengthUnit.FEET));
        assert !t1.equals(new Quantity<>(1.0, WeightUnit.KILOGRAM));
        assert !t1.equals(new Quantity<>(1.0, VolumeUnit.LITRE));

        try {
            new Quantity<>(1.0, TemperatureUnit.CELSIUS);
        } catch (IllegalArgumentException e) {
            assert true;
        }

        try {
            new Quantity<>(1.0, (TemperatureUnit) null);
        } catch (IllegalArgumentException e) {
            assert true;
        }

        System.out.println("All UC14 temperature tests passed!");
    }
}
