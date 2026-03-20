class Test_UC11 {
    public static void main(String[] args) {
        System.out.println("Testing UC11 volume operations...");

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        assert v1.equals(v2);
        assert Math.abs(v1.to(VolumeUnit.MILLILITRE).value - 1000.0) < 1e-6;
        assert Math.abs(v1.to(VolumeUnit.GALLON).value - 0.264172) < 1e-6;
        assert Math.abs(v3.to(VolumeUnit.LITRE).value - 3.78541) < 1e-6;

        assert Quantity.add(v1, v2).equals(new Quantity<>(2.0, VolumeUnit.LITRE));
        assert Math.abs(Quantity.add(v1, v2, VolumeUnit.GALLON).value - 0.528344) < 1e-6;
        assert Math.abs(Quantity.add(v1, v3, VolumeUnit.MILLILITRE).value - 4785.41) < 1e-2;

        assert !v1.equals(new Quantity<>(1.0, LengthUnit.FEET));
        assert !v1.equals(new Quantity<>(1.0, WeightUnit.KILOGRAM));

        try {
            new Quantity<>(Double.NaN, VolumeUnit.LITRE);
            assert false;
        } catch (IllegalArgumentException e) {}

        System.out.println("All UC11 volume tests passed!");
    }
}
