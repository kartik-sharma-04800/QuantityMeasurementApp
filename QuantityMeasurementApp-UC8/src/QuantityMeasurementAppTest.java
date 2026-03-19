class Test {
    public static void main(String[] args) {
        System.out.println("Testing conversions...");
        
        // UC5 Conversion Tests
        assert QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES) == 12.0;
        assert QuantityLength.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET) == 2.0;
        assert QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES) == 36.0;
        assert Math.abs(QuantityLength.convert(2.54, LengthUnit.CM, LengthUnit.INCHES) - 1.0) < 1e-6;
        
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);
        assert q1.equals(q2);
        
        try {
            QuantityLength.convert(1.0, null, LengthUnit.INCHES);
            assert false;
        } catch (IllegalArgumentException e) {}
        
        try {
            new QuantityLength(Double.NaN, LengthUnit.FEET);
            assert false;
        } catch (IllegalArgumentException e) {}
        
        System.out.println("Conversion tests passed!");
        
        System.out.println("Testing addition operations...");
        
        // UC6 Addition Tests
        testAddition_SameUnit_FeetPlusFeet();
        testAddition_SameUnit_InchPlusInch();
        testAddition_CrossUnit_FeetPlusInches();
        testAddition_CrossUnit_InchPlusFeet();
        testAddition_CrossUnit_YardPlusFeet();
        testAddition_CrossUnit_CentimeterPlusInch();
        testAddition_Commutativity();
        testAddition_WithZero();
        testAddition_NegativeValues();
        testAddition_NullSecondOperand();
        testAddition_LargeValues();
        testAddition_SmallValues();
        
        System.out.println("All addition tests passed!");
    }
    
    static void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(1.0, LengthUnit.FEET), 
            new QuantityLength(2.0, LengthUnit.FEET)
        );
        assert result.equals(new QuantityLength(3.0, LengthUnit.FEET));
        assert result.unit == LengthUnit.FEET;
    }
    
    static void testAddition_SameUnit_InchPlusInch() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(6.0, LengthUnit.INCHES), 
            new QuantityLength(6.0, LengthUnit.INCHES)
        );
        assert result.equals(new QuantityLength(12.0, LengthUnit.INCHES));
        assert result.unit == LengthUnit.INCHES;
    }
    
    static void testAddition_CrossUnit_FeetPlusInches() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(1.0, LengthUnit.FEET), 
            new QuantityLength(12.0, LengthUnit.INCHES)
        );
        assert result.equals(new QuantityLength(2.0, LengthUnit.FEET));
        assert result.unit == LengthUnit.FEET;
    }
    
    static void testAddition_CrossUnit_InchPlusFeet() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(12.0, LengthUnit.INCHES), 
            new QuantityLength(1.0, LengthUnit.FEET)
        );
        assert result.equals(new QuantityLength(24.0, LengthUnit.INCHES));
        assert result.unit == LengthUnit.INCHES;
    }
    
    static void testAddition_CrossUnit_YardPlusFeet() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(1.0, LengthUnit.YARDS), 
            new QuantityLength(3.0, LengthUnit.FEET)
        );
        assert result.equals(new QuantityLength(2.0, LengthUnit.YARDS));
        assert result.unit == LengthUnit.YARDS;
    }
    
    static void testAddition_CrossUnit_CentimeterPlusInch() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(2.54, LengthUnit.CM), 
            new QuantityLength(1.0, LengthUnit.INCHES)
        );
        QuantityLength expected = new QuantityLength(5.08, LengthUnit.CM);
        assert Math.abs(result.value - expected.value) < 1e-2;
        assert result.unit == LengthUnit.CM;
    }
    
    static void testAddition_Commutativity() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);
        
        QuantityLength result1 = QuantityLength.add(q1, q2);
        QuantityLength result2 = QuantityLength.add(q2, q1);
        

        assert result1.equals(result2);
    }
    
    static void testAddition_WithZero() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(5.0, LengthUnit.FEET), 
            new QuantityLength(0.0, LengthUnit.INCHES)
        );
        assert result.equals(new QuantityLength(5.0, LengthUnit.FEET));
        assert result.unit == LengthUnit.FEET;
    }
    
    static void testAddition_NegativeValues() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(5.0, LengthUnit.FEET), 
            new QuantityLength(-2.0, LengthUnit.FEET)
        );
        assert result.equals(new QuantityLength(3.0, LengthUnit.FEET));
        assert result.unit == LengthUnit.FEET;
    }
    
    static void testAddition_NullSecondOperand() {
        try {
            QuantityLength.add(new QuantityLength(1.0, LengthUnit.FEET), (QuantityLength) null);
            assert false;
        } catch (IllegalArgumentException e) {}
    }
    
    static void testAddition_LargeValues() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(1e6, LengthUnit.FEET), 
            new QuantityLength(1e6, LengthUnit.FEET)
        );
        assert Math.abs(result.value - 2e6) < 1e-6;
        assert result.unit == LengthUnit.FEET;
    }
    
    static void testAddition_SmallValues() {
        QuantityLength result = QuantityLength.add(
            new QuantityLength(0.001, LengthUnit.FEET), 
            new QuantityLength(0.002, LengthUnit.FEET)
        );
        assert Math.abs(result.value - 0.003) < 1e-6;
        assert result.unit == LengthUnit.FEET;
    }
}
