class Test_UC10 {
    public static void main(String[] args) {
        System.out.println("Testing UC10 generic Quantity...");


        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);
        assert l1.equals(l2);
        System.out.println("Equality test passed");
        Quantity<LengthUnit> converted = l1.to(LengthUnit.INCHES);
        System.out.println("Converted: " + converted);
        assert Math.abs(converted.value - 12.0) < 1e-6;
        System.out.println("Conversion test passed");
        Quantity<LengthUnit> sum = Quantity.add(l1, l2);
        System.out.println("Sum: " + sum);
        System.out.println("Expected: 2.0 FEET");

        Quantity<LengthUnit> result = Quantity.add(l1, l2, LengthUnit.YARDS);
        System.out.println("Yards result: " + result.value);


        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assert w1.equals(w2);
        assert w1.to(WeightUnit.GRAM).value == 1000.0;
        assert Quantity.add(w1, w2).equals(new Quantity<>(2.0, WeightUnit.KILOGRAM));
        assert Quantity.add(w1, w2, WeightUnit.POUND).value > 4.4 && Quantity.add(w1, w2, WeightUnit.POUND).value < 4.5;


        assert !l1.equals(w1);


        try {
            new Quantity<>(Double.NaN, LengthUnit.FEET);
            assert false;
        } catch (IllegalArgumentException e) {}
        try {
            new Quantity<>(1.0, null);
            assert false;
        } catch (IllegalArgumentException e) {}


        try {
            Quantity.convert(1.0, null, LengthUnit.INCHES);
            assert false;
        } catch (IllegalArgumentException e) {}
        try {
            Quantity.add(null, l2);
            assert false;
        } catch (IllegalArgumentException e) {}

        System.out.println("All UC10 generic tests passed!");
    }
}
