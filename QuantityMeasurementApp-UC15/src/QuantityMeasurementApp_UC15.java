public class QuantityMeasurementApp_UC15 {
    private static QuantityMeasurementController controller;

    public static void main(String[] args) {
        System.out.println("=== UC15: N-Tier Architecture Quantity Measurement ===");
        
        // Initialize layers (Factory Pattern)
        initializeApplication();
        
        // Demonstrate all operations
        demonstrateLengthOperations();
        demonstrateWeightOperations();
        demonstrateVolumeOperations();
        demonstrateTemperatureOperations();
        demonstrateCrossCategoryPrevention();
        
        // Show measurement history
        controller.displayMeasurementHistory();
        
        System.out.println("=== UC15 Demonstration Complete ===");
    }

    private static void initializeApplication() {
        // Factory Pattern for creating dependencies
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController(service);
    }

    private static void demonstrateLengthOperations() {
        System.out.println("\n--- Length Operations ---");
        
        QuantityDTO length1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO length2 = new QuantityDTO(12.0, "INCHES", "LENGTH");
        
        System.out.println("Equality: " + length1 + " equals " + length2);
        controller.performEqualityComparison(length1, length2);
        
        System.out.println("Conversion: " + length1 + " to INCHES");
        controller.performConversion(length1, "INCHES");
        
        System.out.println("Addition: " + length1 + " + " + length2);
        controller.performAddition(length1, length2);
        
        System.out.println("Subtraction: " + length1 + " - " + length2);
        controller.performSubtraction(length1, length2);
        
        System.out.println("Division: " + length1 + " ÷ " + length2);
        controller.performDivision(length1, length2);
    }

    private static void demonstrateWeightOperations() {
        System.out.println("\n--- Weight Operations ---");
        
        QuantityDTO weight1 = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        QuantityDTO weight2 = new QuantityDTO(1000.0, "GRAM", "WEIGHT");
        
        System.out.println("Equality: " + weight1 + " equals " + weight2);
        controller.performEqualityComparison(weight1, weight2);
        
        System.out.println("Conversion: " + weight1 + " to GRAM");
        controller.performConversion(weight1, "GRAM");
        
        System.out.println("Addition: " + weight1 + " + " + weight2);
        controller.performAddition(weight1, weight2);
        
        System.out.println("Division: " + weight1 + " ÷ " + weight2);
        controller.performDivision(weight1, weight2);
    }

    private static void demonstrateVolumeOperations() {
        System.out.println("\n--- Volume Operations ---");
        
        QuantityDTO volume1 = new QuantityDTO(1.0, "LITRE", "VOLUME");
        QuantityDTO volume2 = new QuantityDTO(1000.0, "MILLILITRE", "VOLUME");
        
        System.out.println("Equality: " + volume1 + " equals " + volume2);
        controller.performEqualityComparison(volume1, volume2);
        
        System.out.println("Conversion: " + volume1 + " to MILLILITRE");
        controller.performConversion(volume1, "MILLILITRE");
        
        System.out.println("Addition: " + volume1 + " + " + volume2);
        controller.performAddition(volume1, volume2);
    }

    private static void demonstrateTemperatureOperations() {
        System.out.println("\n--- Temperature Operations ---");
        
        QuantityDTO temp1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO temp2 = new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");
        
        System.out.println("Equality: " + temp1 + " equals " + temp2);
        controller.performEqualityComparison(temp1, temp2);
        
        System.out.println("Conversion: " + temp1 + " to FAHRENHEIT");
        controller.performConversion(temp1, "FAHRENHEIT");
        
        System.out.println("Addition attempt: " + temp1 + " + " + temp2);
        controller.performAddition(temp1, temp2);
        
        System.out.println("Division attempt: " + temp1 + " ÷ " + temp2);
        controller.performDivision(temp1, temp2);
    }

    private static void demonstrateCrossCategoryPrevention() {
        System.out.println("\n--- Cross-Category Prevention ---");
        
        QuantityDTO length = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO weight = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        QuantityDTO volume = new QuantityDTO(1.0, "LITRE", "VOLUME");
        QuantityDTO temperature = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        
        System.out.println("Length vs Weight comparison:");
        controller.performEqualityComparison(length, weight);
        
        System.out.println("Length vs Temperature comparison:");
        controller.performEqualityComparison(length, temperature);
        
        System.out.println("Weight vs Volume comparison:");
        controller.performEqualityComparison(weight, volume);
    }
}
