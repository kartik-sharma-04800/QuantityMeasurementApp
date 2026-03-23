class Test_UC13 {
    public static void main(String[] args) {
        System.out.println("Testing UC13 centralized arithmetic logic...");

        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);
        
        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);
        System.out.println("l1 base value: " + l1.unit.convertToBaseUnit(l1.value));
        System.out.println("l2 base value: " + l2.unit.convertToBaseUnit(l2.value));
        System.out.println("l1 + l2: " + l1.add(l2));
        System.out.println("Expected: 10.5 FEET");
        Quantity<LengthUnit> addResult = l1.add(l2);
        System.out.println("Add result base value: " + addResult.unit.convertToBaseUnit(addResult.value));
        System.out.println("Expected result base value: " + (10.0 + 6.0/12.0));
        System.out.println("Expected result in feet: " + ((10.0 + 6.0/12.0)));
        assert Math.abs(addResult.value - 10.5) < 1e-6;
        System.out.println("Addition test passed");
        
        System.out.println("l1 - l2: " + l1.subtract(l2));
        System.out.println("Expected: 9.5 FEET");
        assert l1.subtract(l2).equals(new Quantity<>(9.5, LengthUnit.FEET));
        System.out.println("Subtraction test passed");
        
        System.out.println("l1 ÷ l2: " + l1.divide(l2));
        System.out.println("Expected: ~20.0");
        assert Math.abs(l1.divide(l2) - 20.0) < 1e-6;
        System.out.println("Division test passed");

        try {
            l1.add(null);
            assert false;
        } catch (IllegalArgumentException e) {}
        try {
            l1.subtract(null);
            assert false;
        } catch (IllegalArgumentException e) {}
        try {
            l1.divide(null);
            assert false;
        } catch (IllegalArgumentException e) {}

        try {
            l1.add(new Quantity<>(1.0, LengthUnit.FEET));
            assert true;
        } catch (IllegalArgumentException e) {}
        try {
            l1.subtract(new Quantity<>(1.0, LengthUnit.FEET));
            assert true;
        } catch (IllegalArgumentException e) {}
        try {
            l1.divide(new Quantity<>(1.0, LengthUnit.FEET));
            assert true;
        } catch (IllegalArgumentException e) {}

        try {
            l1.divide(new Quantity<>(0.0, LengthUnit.FEET));
            assert false;
        } catch (ArithmeticException e) {}

        Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = original.add(l2);
        assert original.value == 10.0;
        assert result.value == 10.5;

        assert Math.abs(Quantity.add(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES).value - 2.0) < 1e-6;
        assert Math.abs(Quantity.subtract(10.0, LengthUnit.FEET, 6.0, LengthUnit.INCHES).value - 9.5) < 1e-6;
        assert Math.abs(Quantity.divide(24.0, LengthUnit.INCHES, 2.0, LengthUnit.FEET) - 1.0) < 1e-6;

        System.out.println("All UC13 centralized arithmetic tests passed!");
    }
}
