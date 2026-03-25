class Test_UC14_Comprehensive {
    private static final double EPSILON = 1e-6;

    public static void main(String[] args) {
        System.out.println("=== UC14 Comprehensive Temperature Tests ===");
        
        // Temperature Equality Tests
        testTemperatureEquality_CelsiusToCelsius_SameValue();
        testTemperatureEquality_FahrenheitToFahrenheit_SameValue();
        testTemperatureEquality_KelvinToKelvin_SameValue();
        testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit();
        testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit();
        testTemperatureEquality_CelsiusToKelvin_0Celsius273Kelvin();
        testTemperatureEquality_FahrenheitToKelvin_32Fahrenheit273Kelvin();
        testTemperatureEquality_Negative40Equal();
        testTemperatureEquality_SymmetricProperty();
        testTemperatureEquality_ReflexiveProperty();
        
        // Temperature Conversion Tests
        testTemperatureConversion_CelsiusToFahrenheit_VariousValues();
        testTemperatureConversion_FahrenheitToCelsius_VariousValues();
        testTemperatureConversion_CelsiusToKelvin_VariousValues();
        testTemperatureConversion_KelvinToCelsius_VariousValues();
        testTemperatureConversion_FahrenheitToKelvin_VariousValues();
        testTemperatureConversion_KelvinToFahrenheit_VariousValues();
        testTemperatureConversion_RoundTrip_PreservesValue();
        testTemperatureConversion_SameUnit();
        testTemperatureConversion_ZeroValue();
        testTemperatureConversion_NegativeValues();
        testTemperatureConversion_LargeValues();
        testTemperatureConversion_EdgeCase_AbsoluteZero();
        testTemperatureConversion_EdgeCase_EqualPoint();
        
        // Unsupported Operation Tests
        testTemperatureUnsupportedOperation_Add();
        testTemperatureUnsupportedOperation_Subtract();
        testTemperatureUnsupportedOperation_Divide();
        testTemperatureUnsupportedOperation_ErrorMessage();
        
        // Cross-Category Prevention Tests
        testTemperatureVsLengthIncompatibility();
        testTemperatureVsWeightIncompatibility();
        testTemperatureVsVolumeIncompatibility();
        
        // Operation Support Method Tests
        testOperationSupportMethods_TemperatureUnit();
        testOperationSupportMethods_LengthUnit();
        testOperationSupportMethods_WeightUnit();
        testOperationSupportMethods_VolumeUnit();
        
        // Interface Evolution Tests
        testIMeasurableInterface_Evolution_BackwardCompatible();
        testTemperatureDefaultMethodInheritance();
        
        // Validation Tests
        testTemperatureNullUnitValidation();
        testTemperatureNullOperandValidation_InComparison();
        testTemperatureDifferentValuesInequality();
        testTemperatureConversionPrecision_Epsilon();
        testTemperatureEnumImplementsIMeasurable();
        testTemperatureUnit_AllConstants();
        testTemperatureUnit_NameMethod();
        testTemperatureUnit_ConversionFactor();
        
        // Integration Tests
        testTemperatureIntegrationWithGenericQuantity();
        testTemperatureCrossUnitAdditionAttempt();
        testTemperatureValidateOperationSupport_MethodBehavior();
        testTemperatureBackwardCompatibility_UC1_Through_UC13();
        
        System.out.println("\n=== All UC14 Comprehensive Tests Passed! ===");
    }

    // Temperature Equality Tests
    static void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        assert new Quantity<>(0.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS));
        assert new Quantity<>(100.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(100.0, TemperatureUnit.CELSIUS));
    }

    static void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        assert new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT).equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT));
        assert new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT).equals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT));
    }

    static void testTemperatureEquality_KelvinToKelvin_SameValue() {
        assert new Quantity<>(273.15, TemperatureUnit.KELVIN).equals(new Quantity<>(273.15, TemperatureUnit.KELVIN));
        assert new Quantity<>(373.15, TemperatureUnit.KELVIN).equals(new Quantity<>(373.15, TemperatureUnit.KELVIN));
    }

    static void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        assert new Quantity<>(0.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT));
    }

    static void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        assert new Quantity<>(100.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT));
    }

    static void testTemperatureEquality_CelsiusToKelvin_0Celsius273Kelvin() {
        assert new Quantity<>(0.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(273.15, TemperatureUnit.KELVIN));
    }

    static void testTemperatureEquality_FahrenheitToKelvin_32Fahrenheit273Kelvin() {
        assert new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT).equals(new Quantity<>(273.15, TemperatureUnit.KELVIN));
    }

    static void testTemperatureEquality_Negative40Equal() {
        assert new Quantity<>(-40.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT));
    }

    static void testTemperatureEquality_SymmetricProperty() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assert q1.equals(q2) && q2.equals(q1);
    }

    static void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> q = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        assert q.equals(q);
    }

    // Temperature Conversion Tests
    static void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
        assert Math.abs(new Quantity<>(0.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.FAHRENHEIT).value - 32.0) < EPSILON;
        assert Math.abs(new Quantity<>(100.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.FAHRENHEIT).value - 212.0) < EPSILON;
        assert Math.abs(new Quantity<>(-40.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.FAHRENHEIT).value - (-40.0)) < EPSILON;
        assert Math.abs(new Quantity<>(50.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.FAHRENHEIT).value - 122.0) < EPSILON;
    }

    static void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
        assert Math.abs(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT).to(TemperatureUnit.CELSIUS).value - 0.0) < EPSILON;
        assert Math.abs(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT).to(TemperatureUnit.CELSIUS).value - 100.0) < EPSILON;
        assert Math.abs(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT).to(TemperatureUnit.CELSIUS).value - (-40.0)) < EPSILON;
        assert Math.abs(new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT).to(TemperatureUnit.CELSIUS).value - 50.0) < EPSILON;
    }

    static void testTemperatureConversion_CelsiusToKelvin_VariousValues() {
        assert Math.abs(new Quantity<>(0.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.KELVIN).value - 273.15) < EPSILON;
        assert Math.abs(new Quantity<>(100.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.KELVIN).value - 373.15) < EPSILON;
        assert Math.abs(new Quantity<>(-273.15, TemperatureUnit.CELSIUS).to(TemperatureUnit.KELVIN).value - 0.0) < EPSILON;
    }

    static void testTemperatureConversion_KelvinToCelsius_VariousValues() {
        assert Math.abs(new Quantity<>(273.15, TemperatureUnit.KELVIN).to(TemperatureUnit.CELSIUS).value - 0.0) < EPSILON;
        assert Math.abs(new Quantity<>(373.15, TemperatureUnit.KELVIN).to(TemperatureUnit.CELSIUS).value - 100.0) < EPSILON;
        assert Math.abs(new Quantity<>(0.0, TemperatureUnit.KELVIN).to(TemperatureUnit.CELSIUS).value - (-273.15)) < EPSILON;
    }

    static void testTemperatureConversion_FahrenheitToKelvin_VariousValues() {
        assert Math.abs(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT).to(TemperatureUnit.KELVIN).value - 273.15) < EPSILON;
        assert Math.abs(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT).to(TemperatureUnit.KELVIN).value - 373.15) < EPSILON;
    }

    static void testTemperatureConversion_KelvinToFahrenheit_VariousValues() {
        assert Math.abs(new Quantity<>(273.15, TemperatureUnit.KELVIN).to(TemperatureUnit.FAHRENHEIT).value - 32.0) < EPSILON;
        assert Math.abs(new Quantity<>(373.15, TemperatureUnit.KELVIN).to(TemperatureUnit.FAHRENHEIT).value - 212.0) < EPSILON;
    }

    static void testTemperatureConversion_RoundTrip_PreservesValue() {
        double original = 50.0;
        double roundTrip = new Quantity<>(original, TemperatureUnit.CELSIUS)
            .to(TemperatureUnit.FAHRENHEIT)
            .to(TemperatureUnit.CELSIUS).value;
        assert Math.abs(original - roundTrip) < EPSILON;
    }

    static void testTemperatureConversion_SameUnit() {
        assert Math.abs(new Quantity<>(50.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.CELSIUS).value - 50.0) < EPSILON;
    }

    static void testTemperatureConversion_ZeroValue() {
        assert Math.abs(new Quantity<>(0.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.FAHRENHEIT).value - 32.0) < EPSILON;
    }

    static void testTemperatureConversion_NegativeValues() {
        assert Math.abs(new Quantity<>(-20.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.FAHRENHEIT).value - (-4.0)) < EPSILON;
    }

    static void testTemperatureConversion_LargeValues() {
        assert Math.abs(new Quantity<>(1000.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.FAHRENHEIT).value - 1832.0) < EPSILON;
    }

    static void testTemperatureConversion_EdgeCase_AbsoluteZero() {
        assert Math.abs(new Quantity<>(-273.15, TemperatureUnit.CELSIUS).to(TemperatureUnit.KELVIN).value - 0.0) < EPSILON;
        assert Math.abs(new Quantity<>(0.0, TemperatureUnit.KELVIN).to(TemperatureUnit.FAHRENHEIT).value - (-459.67)) < EPSILON;
    }

    static void testTemperatureConversion_EdgeCase_EqualPoint() {
        assert Math.abs(new Quantity<>(-40.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.FAHRENHEIT).value - (-40.0)) < EPSILON;
    }

    // Unsupported Operation Tests
    static void testTemperatureUnsupportedOperation_Add() {
        try {
            new Quantity<>(100.0, TemperatureUnit.CELSIUS).add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
            assert false;
        } catch (UnsupportedOperationException e) {
            assert e.getMessage().contains("addition");
        }
    }

    static void testTemperatureUnsupportedOperation_Subtract() {
        try {
            new Quantity<>(100.0, TemperatureUnit.CELSIUS).subtract(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
            assert false;
        } catch (UnsupportedOperationException e) {
            assert e.getMessage().contains("subtraction");
        }
    }

    static void testTemperatureUnsupportedOperation_Divide() {
        try {
            new Quantity<>(100.0, TemperatureUnit.CELSIUS).divide(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
            assert false;
        } catch (UnsupportedOperationException e) {
            assert e.getMessage().contains("division");
        }
    }

    static void testTemperatureUnsupportedOperation_ErrorMessage() {
        try {
            new Quantity<>(100.0, TemperatureUnit.CELSIUS).add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
            assert false;
        } catch (UnsupportedOperationException e) {
            assert e.getMessage().contains("Temperature does not support");
        }
    }

    // Cross-Category Prevention Tests
    static void testTemperatureVsLengthIncompatibility() {
        assert !new Quantity<>(100.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(100.0, LengthUnit.FEET));
    }

    static void testTemperatureVsWeightIncompatibility() {
        assert !new Quantity<>(50.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(50.0, WeightUnit.KILOGRAM));
    }

    static void testTemperatureVsVolumeIncompatibility() {
        assert !new Quantity<>(25.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(25.0, VolumeUnit.LITRE));
    }

    // Operation Support Method Tests
    static void testOperationSupportMethods_TemperatureUnit() {
        assert !TemperatureUnit.CELSIUS.supportsArithmetic();
        assert !TemperatureUnit.FAHRENHEIT.supportsArithmetic();
        assert !TemperatureUnit.KELVIN.supportsArithmetic();
    }

    static void testOperationSupportMethods_LengthUnit() {
        assert LengthUnit.FEET.supportsArithmetic();
        assert LengthUnit.INCHES.supportsArithmetic();
        assert LengthUnit.YARDS.supportsArithmetic();
    }

    static void testOperationSupportMethods_WeightUnit() {
        assert WeightUnit.KILOGRAM.supportsArithmetic();
        assert WeightUnit.GRAM.supportsArithmetic();
        assert WeightUnit.POUND.supportsArithmetic();
    }

    static void testOperationSupportMethods_VolumeUnit() {
        assert VolumeUnit.LITRE.supportsArithmetic();
        assert VolumeUnit.MILLILITRE.supportsArithmetic();
        assert VolumeUnit.GALLON.supportsArithmetic();
    }

    // Interface Evolution Tests
    static void testIMeasurableInterface_Evolution_BackwardCompatible() {
        // Test that existing units still work without modification
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        
        assert length.add(length) != null;
        assert weight.add(weight) != null;
        assert volume.add(volume) != null;
    }

    static void testTemperatureDefaultMethodInheritance() {
        // Test that non-temperature units inherit default true values
        assert LengthUnit.FEET.supportsArithmetic();
        assert WeightUnit.KILOGRAM.supportsArithmetic();
        assert VolumeUnit.LITRE.supportsArithmetic();
    }

    // Validation Tests
    static void testTemperatureNullUnitValidation() {
        try {
            new Quantity<>(100.0, (TemperatureUnit) null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    static void testTemperatureNullOperandValidation_InComparison() {
        Quantity<TemperatureUnit> q = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        assert !q.equals(null);
    }

    static void testTemperatureDifferentValuesInequality() {
        assert !new Quantity<>(50.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(100.0, TemperatureUnit.CELSIUS));
    }

    static void testTemperatureConversionPrecision_Epsilon() {
        double converted = new Quantity<>(0.0, TemperatureUnit.CELSIUS).to(TemperatureUnit.FAHRENHEIT).value;
        assert Math.abs(converted - 32.0) < EPSILON;
    }

    static void testTemperatureEnumImplementsIMeasurable() {
        assert TemperatureUnit.CELSIUS instanceof IMeasurable;
        assert TemperatureUnit.FAHRENHEIT instanceof IMeasurable;
        assert TemperatureUnit.KELVIN instanceof IMeasurable;
    }

    static void testTemperatureUnit_AllConstants() {
        TemperatureUnit[] units = TemperatureUnit.values();
        assert units.length == 3;
        assert units[0] == TemperatureUnit.CELSIUS;
        assert units[1] == TemperatureUnit.FAHRENHEIT;
        assert units[2] == TemperatureUnit.KELVIN;
    }

    static void testTemperatureUnit_NameMethod() {
        assert "CELSIUS".equals(TemperatureUnit.CELSIUS.getUnitName());
        assert "FAHRENHEIT".equals(TemperatureUnit.FAHRENHEIT.getUnitName());
        assert "KELVIN".equals(TemperatureUnit.KELVIN.getUnitName());
    }

    static void testTemperatureUnit_ConversionFactor() {
        assert TemperatureUnit.CELSIUS.getConversionFactor() == 1.0;
        assert TemperatureUnit.FAHRENHEIT.getConversionFactor() == 1.0;
        assert TemperatureUnit.KELVIN.getConversionFactor() == 1.0;
    }

    // Integration Tests
    static void testTemperatureIntegrationWithGenericQuantity() {
        Quantity<TemperatureUnit> temp = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        assert temp != null;
        assert temp.value == 50.0;
        assert temp.unit == TemperatureUnit.CELSIUS;
    }

    static void testTemperatureCrossUnitAdditionAttempt() {
        try {
            new Quantity<>(50.0, TemperatureUnit.CELSIUS).add(new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT));
            assert false;
        } catch (UnsupportedOperationException e) {
            assert true;
        }
    }

    static void testTemperatureValidateOperationSupport_MethodBehavior() {
        try {
            TemperatureUnit.CELSIUS.validateOperationSupport("addition");
            assert false;
        } catch (UnsupportedOperationException e) {
            assert e.getMessage().contains("Temperature does not support addition");
        }
    }

    static void testTemperatureBackwardCompatibility_UC1_Through_UC13() {
        // Test that existing UC1-UC13 functionality still works
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assert l1.equals(l2);
        
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assert w1.equals(w2);
        
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assert v1.equals(v2);
    }
}
