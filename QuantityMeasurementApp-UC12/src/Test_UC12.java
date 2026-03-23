class Test_UC12 {
    public static void main(String[] args) {
        System.out.println("Testing UC12 subtraction and division operations...");

        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        assert Quantity.subtract(l1, l2).equals(new Quantity<>(9.5, LengthUnit.FEET));
        assert Quantity.subtract(l1, l2, LengthUnit.INCHES).equals(new Quantity<>(114.0, LengthUnit.INCHES));
        assert Quantity.subtract(w1, w2).equals(new Quantity<>(5.0, WeightUnit.KILOGRAM));
        assert Quantity.subtract(w1, w2, WeightUnit.GRAM).equals(new Quantity<>(5000.0, WeightUnit.GRAM));
        assert Quantity.subtract(v1, v2).equals(new Quantity<>(4.5, VolumeUnit.LITRE));
        assert Math.abs(Quantity.subtract(v1, v2, VolumeUnit.MILLILITRE).value - 4500.0) < 1e-6;

        assert Quantity.subtract(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(10.0, LengthUnit.FEET)).equals(new Quantity<>(-5.0, LengthUnit.FEET));
        assert Quantity.subtract(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(120.0, LengthUnit.INCHES)).equals(new Quantity<>(0.0, LengthUnit.FEET));
        assert Quantity.subtract(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(0.0, LengthUnit.INCHES)).equals(new Quantity<>(5.0, LengthUnit.FEET));

        assert Math.abs(Quantity.divide(l1, new Quantity<>(2.0, LengthUnit.FEET)) - 5.0) < 1e-6;
        assert Math.abs(Quantity.divide(new Quantity<>(24.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.FEET)) - 1.0) < 1e-6;
        assert Math.abs(Quantity.divide(w1, new Quantity<>(5.0, WeightUnit.KILOGRAM)) - 2.0) < 1e-6;
        assert Math.abs(Quantity.divide(new Quantity<>(2000.0, WeightUnit.GRAM), new Quantity<>(1.0, WeightUnit.KILOGRAM)) - 2.0) < 1e-6;
        assert Math.abs(Quantity.divide(v1, new Quantity<>(10.0, VolumeUnit.LITRE)) - 0.5) < 1e-6;
        assert Math.abs(Quantity.divide(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), new Quantity<>(1.0, VolumeUnit.LITRE)) - 1.0) < 1e-6;

        assert Math.abs(Quantity.divide(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(5.0, LengthUnit.FEET)) - 2.0) < 1e-6;
        assert Math.abs(Quantity.divide(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(10.0, LengthUnit.FEET)) - 0.5) < 1e-6;
        assert Math.abs(Quantity.divide(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(10.0, LengthUnit.FEET)) - 1.0) < 1e-6;

        try {
            l1.subtract((Quantity<LengthUnit>) null);
            assert false;
        } catch (IllegalArgumentException e) {}
        try {
            l1.subtract(l2, (LengthUnit) null);
            assert false;
        } catch (IllegalArgumentException e) {}
        try {
            l1.divide((Quantity<LengthUnit>) null);
            assert false;
        } catch (IllegalArgumentException e) {}
        try {
            l1.divide(new Quantity<>(0.0, LengthUnit.FEET));
            assert false;
        } catch (ArithmeticException e) {}

        try {
            l1.subtract(new Quantity<>(1.0, LengthUnit.FEET));
            assert true;
        } catch (IllegalArgumentException e) {}
        try {
            l1.divide(new Quantity<>(1.0, LengthUnit.FEET));
            assert true;
        } catch (IllegalArgumentException e) {}

        Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = original.subtract(l2);
        assert original.value == 10.0;
        assert result.value == 9.5;

        System.out.println("All UC12 subtraction and division tests passed!");
    }
}
