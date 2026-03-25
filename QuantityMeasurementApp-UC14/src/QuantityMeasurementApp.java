class QuantityMeasurementApp {
    static <T extends IMeasurable> void show(double v, T f, T t) {
        System.out.printf("%.2f %s = %.6f %s%n", v, f.getUnitName(), Quantity.convert(v, f, t), t.getUnitName());
    }
    
    static <T extends IMeasurable> void show(Quantity<T> q, T t) {
        System.out.println(q + " = " + q.to(t));
    }
    
    static <T extends IMeasurable> void showAddition(Quantity<T> q1, Quantity<T> q2) {
        Quantity<T> result = Quantity.add(q1, q2);
        System.out.println(q1 + " + " + q2 + " = " + result);
    }
    
    static <T extends IMeasurable> void showAdditionWithTarget(Quantity<T> q1, Quantity<T> q2, T target) {
        Quantity<T> result = Quantity.add(q1, q2, target);
        System.out.println(q1 + " + " + q2 + " = " + result + " (target: " + target + ")");
    }

    static <T extends IMeasurable> void demonstrateEquality(Quantity<T> q1, Quantity<T> q2) {
        System.out.println(q1 + " equals " + q2 + " -> " + q1.equals(q2));
    }

    static <T extends IMeasurable> void demonstrateConversion(Quantity<T> q, T target) {
        System.out.println(q + " -> " + q.to(target));
    }

    static <T extends IMeasurable> void demonstrateAddition(Quantity<T> q1, Quantity<T> q2) {
        System.out.println(q1 + " + " + q2 + " = " + Quantity.add(q1, q2));
    }

    static <T extends IMeasurable> void demonstrateAdditionWithTarget(Quantity<T> q1, Quantity<T> q2, T target) {
        System.out.println(q1 + " + " + q2 + " = " + Quantity.add(q1, q2, target) + " (target: " + target + ")");
    }

    static <T extends IMeasurable> void demonstrateSubtraction(Quantity<T> q1, Quantity<T> q2) {
        System.out.println(q1 + " - " + q2 + " = " + Quantity.subtract(q1, q2));
    }

    static <T extends IMeasurable> void demonstrateSubtractionWithTarget(Quantity<T> q1, Quantity<T> q2, T target) {
        System.out.println(q1 + " - " + q2 + " = " + Quantity.subtract(q1, q2, target) + " (target: " + target + ")");
    }

    static <T extends IMeasurable> void demonstrateDivision(Quantity<T> q1, Quantity<T> q2) {
        System.out.println(q1 + " ÷ " + q2 + " = " + Quantity.divide(q1, q2));
    }

    public static void main(String[] args) {
        System.out.println("=== UC14: Temperature & Selective Arithmetic Demo ===");

        System.out.println("\n--- Length Operations ---");
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
        demonstrateEquality(l1, l2);
        demonstrateConversion(l1, LengthUnit.INCHES);
        demonstrateAddition(l1, l2);
        demonstrateAdditionWithTarget(l1, l2, LengthUnit.YARDS);
        demonstrateSubtraction(l1, l2);
        demonstrateSubtractionWithTarget(l1, l2, LengthUnit.INCHES);
        demonstrateDivision(l1, l2);

        System.out.println("\n--- Weight Operations ---");
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        demonstrateEquality(w1, w2);
        demonstrateConversion(w1, WeightUnit.GRAM);
        demonstrateAddition(w1, w2);
        demonstrateAdditionWithTarget(w1, w2, WeightUnit.POUND);
        demonstrateSubtraction(w1, w2);
        demonstrateSubtractionWithTarget(w1, w2, WeightUnit.GRAM);
        demonstrateDivision(w1, w2);

        System.out.println("\n--- Volume Operations ---");
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        demonstrateEquality(v1, v2);
        demonstrateConversion(v1, VolumeUnit.MILLILITRE);
        demonstrateAddition(v1, v2);
        demonstrateAdditionWithTarget(v1, v2, VolumeUnit.GALLON);
        demonstrateSubtraction(v1, v2);
        demonstrateSubtractionWithTarget(v1, v2, VolumeUnit.GALLON);
        demonstrateDivision(v1, v2);

        System.out.println("\n--- Temperature Operations ---");
        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> t3 = new Quantity<>(273.15, TemperatureUnit.KELVIN);
        demonstrateEquality(t1, t2);
        demonstrateConversion(t1, TemperatureUnit.FAHRENHEIT);
        demonstrateConversion(t1, TemperatureUnit.KELVIN);
        demonstrateConversion(t2, TemperatureUnit.CELSIUS);

        try {
            demonstrateAddition(t1, t2);
        } catch (UnsupportedOperationException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        try {
            demonstrateSubtraction(t1, t2);
        } catch (UnsupportedOperationException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        try {
            demonstrateDivision(t1, t2);
        } catch (UnsupportedOperationException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println("\n--- Cross-Category Prevention ---");
        System.out.println("Length vs Temperature: " + l1.equals(t1));
        System.out.println("Weight vs Temperature: " + w1.equals(t1));
        System.out.println("Volume vs Temperature: " + v1.equals(t1));
    }
}
