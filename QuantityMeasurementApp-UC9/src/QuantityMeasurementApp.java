class QuantityMeasurementApp {
    static void show(double v, LengthUnit f, LengthUnit t) {
        System.out.printf("%.2f %s = %.6f %s%n", v, f, QuantityLength.convert(v, f, t), t);
    }
    
    static void show(QuantityLength q, LengthUnit t) {
        System.out.println(q + " = " + q.to(t));
    }
    
    static void showAddition(QuantityLength q1, QuantityLength q2) {
        QuantityLength result = QuantityLength.add(q1, q2);
        System.out.println(q1 + " + " + q2 + " = " + result);
    }
    
    static void showAdditionWithTarget(QuantityLength q1, QuantityLength q2, LengthUnit target) {
        QuantityLength result = QuantityLength.add(q1, q2, target);
        System.out.println(q1 + " + " + q2 + " = " + result + " (target: " + target + ")");
    }

    // UC9: Weight demo methods
    static void show(double v, WeightUnit f, WeightUnit t) {
        System.out.printf("%.2f %s = %.6f %s%n", v, f, QuantityWeight.convert(v, f, t), t);
    }
    
    static void show(QuantityWeight q, WeightUnit t) {
        System.out.println(q + " = " + q.to(t));
    }
    
    static void showAddition(QuantityWeight q1, QuantityWeight q2) {
        QuantityWeight result = QuantityWeight.add(q1, q2);
        System.out.println(q1 + " + " + q2 + " = " + result);
    }
    
    static void showAdditionWithTarget(QuantityWeight q1, QuantityWeight q2, WeightUnit target) {
        QuantityWeight result = QuantityWeight.add(q1, q2, target);
        System.out.println(q1 + " + " + q2 + " = " + result + " (target: " + target + ")");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Unit Conversion ===");
        show(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        show(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        show(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        show(1.0, LengthUnit.CM, LengthUnit.INCHES);
        
        QuantityLength q = new QuantityLength(2.0, LengthUnit.YARDS);
        show(q, LengthUnit.INCHES);
        show(q, LengthUnit.FEET);
        
        System.out.println("\n=== Addition Operations ===");
        showAddition(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(2.0, LengthUnit.FEET));
        showAddition(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES));
        showAddition(new QuantityLength(12.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET));
        showAddition(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET));
        showAddition(new QuantityLength(36.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.YARDS));
        showAddition(new QuantityLength(2.54, LengthUnit.CM), new QuantityLength(1.0, LengthUnit.INCHES));
        showAddition(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(0.0, LengthUnit.INCHES));
        showAddition(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(-2.0, LengthUnit.FEET));
        
        System.out.println("\n=== UC7: Addition with Target Unit Specification ===");
        showAdditionWithTarget(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        showAdditionWithTarget(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        showAdditionWithTarget(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        showAdditionWithTarget(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        showAdditionWithTarget(new QuantityLength(36.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.YARDS), LengthUnit.FEET);
        showAdditionWithTarget(new QuantityLength(2.54, LengthUnit.CM), new QuantityLength(1.0, LengthUnit.INCHES), LengthUnit.CM);
        showAdditionWithTarget(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(0.0, LengthUnit.INCHES), LengthUnit.YARDS);
        showAdditionWithTarget(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(-2.0, LengthUnit.FEET), LengthUnit.INCHES);

        System.out.println("\n=== UC9: Weight Measurement ===");
        System.out.println("Equality checks:");
        System.out.println(new QuantityWeight(1.0, WeightUnit.KILOGRAM) + " equals " + new QuantityWeight(1000.0, WeightUnit.GRAM) + " -> " + new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
        System.out.println(new QuantityWeight(2.20462, WeightUnit.POUND) + " equals " + new QuantityWeight(1.0, WeightUnit.KILOGRAM) + " -> " + new QuantityWeight(2.20462, WeightUnit.POUND).equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
        
        System.out.println("\n=== Unit Conversion (Weight) ===");
        show(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);
        show(2.0, WeightUnit.POUND, WeightUnit.KILOGRAM);
        show(500.0, WeightUnit.GRAM, WeightUnit.POUND);
        
        QuantityWeight w = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
        show(w, WeightUnit.GRAM);
        show(w, WeightUnit.POUND);
        
        System.out.println("\n=== Addition Operations (Weight) ===");
        showAddition(new QuantityWeight(1.0, WeightUnit.KILOGRAM), new QuantityWeight(2.0, WeightUnit.KILOGRAM));
        showAddition(new QuantityWeight(1.0, WeightUnit.KILOGRAM), new QuantityWeight(1000.0, WeightUnit.GRAM));
        showAddition(new QuantityWeight(500.0, WeightUnit.GRAM), new QuantityWeight(0.5, WeightUnit.KILOGRAM));
        
        System.out.println("\n=== UC9: Addition with Target Unit (Weight) ===");
        showAdditionWithTarget(new QuantityWeight(1.0, WeightUnit.KILOGRAM), new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        showAdditionWithTarget(new QuantityWeight(1.0, WeightUnit.POUND), new QuantityWeight(453.592, WeightUnit.GRAM), WeightUnit.POUND);
        showAdditionWithTarget(new QuantityWeight(2.0, WeightUnit.KILOGRAM), new QuantityWeight(4.0, WeightUnit.POUND), WeightUnit.KILOGRAM);

        System.out.println("\n=== UC9: Category Incompatibility ===");
        System.out.println("Weight vs Length: " + new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }
}
