class QuantityMeasurementApp_UC10 {
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

    public static void main(String[] args) {
        System.out.println("=== UC10: Generic Quantity Demo ===");

        System.out.println("\n--- Length Operations ---");
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
        demonstrateEquality(l1, l2);
        demonstrateConversion(l1, LengthUnit.INCHES);
        demonstrateAddition(l1, l2);
        demonstrateAdditionWithTarget(l1, l2, LengthUnit.YARDS);

        System.out.println("\n--- Weight Operations ---");
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        demonstrateEquality(w1, w2);
        demonstrateConversion(w1, WeightUnit.GRAM);
        demonstrateAddition(w1, w2);
        demonstrateAdditionWithTarget(w1, w2, WeightUnit.POUND);

        System.out.println("\n--- Cross-Category Prevention ---");
        System.out.println("Length vs Weight: " + l1.equals(w1));
    }
}
