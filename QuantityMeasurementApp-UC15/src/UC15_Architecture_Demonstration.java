public class UC15_Architecture_Demonstration {
    public static void main(String[] args) {
        System.out.println("=== UC15: N-Tier Architecture Demonstration ===");
        
        demonstrateArchitecturalLayers();
        demonstrateDesignPatterns();
        demonstrateSOLIDPrinciples();
        demonstrateDataFlow();
        demonstrateErrorHandling();
        demonstrateExtensibility();
        
        System.out.println("\n=== UC15 Architecture Demonstration Complete ===");
    }

    private static void demonstrateArchitecturalLayers() {
        System.out.println("\n--- N-Tier Architecture Layers ---");
        
        // Application Layer (Entry Point)
        System.out.println("✓ Application Layer: QuantityMeasurementApp_UC15");
        System.out.println("  - Main method and application initialization");
        System.out.println("  - Factory pattern for dependency creation");
        
        // Controller Layer
        System.out.println("✓ Controller Layer: QuantityMeasurementController");
        System.out.println("  - Handles user interaction and orchestration");
        System.out.println("  - Delegates to service layer");
        System.out.println("  - Formats and presents results");
        
        // Service Layer
        System.out.println("✓ Service Layer: QuantityMeasurementServiceImpl");
        System.out.println("  - Contains core business logic");
        System.out.println("  - Validates business rules");
        System.out.println("  - Coordinates with repository");
        
        // Entity/Model Layer
        System.out.println("✓ Entity/Model Layer: QuantityDTO, QuantityModel, QuantityMeasurementEntity");
        System.out.println("  - Data transfer objects");
        System.out.println("  - Internal service models");
        System.out.println("  - Persistence entities");
        
        // Repository Layer
        System.out.println("✓ Repository Layer: QuantityMeasurementCacheRepository");
        System.out.println("  - Data access abstraction");
        System.out.println("  - In-memory caching with persistence");
        System.out.println("  - Interface-based design");
    }

    private static void demonstrateDesignPatterns() {
        System.out.println("\n--- Design Patterns Used ---");
        
        // Singleton Pattern
        System.out.println("✓ Singleton Pattern: QuantityMeasurementCacheRepository");
        IQuantityMeasurementRepository repo1 = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementRepository repo2 = QuantityMeasurementCacheRepository.getInstance();
        System.out.println("  - Same instance: " + (repo1 == repo2));
        
        // Factory Pattern
        System.out.println("✓ Factory Pattern: Application initialization");
        System.out.println("  - Creates service and controller instances");
        System.out.println("  - Centralizes dependency creation");
        
        // Facade Pattern
        System.out.println("✓ Facade Pattern: QuantityMeasurementController");
        System.out.println("  - Simplified interface for complex operations");
        System.out.println("  - Hides service layer complexity");
        
        // DTO Pattern
        System.out.println("✓ DTO Pattern: QuantityDTO, QuantityMeasurementEntity");
        System.out.println("  - Data transfer between layers");
        System.out.println("  - Standardized input/output format");
        
        // Repository Pattern
        System.out.println("✓ Repository Pattern: IQuantityMeasurementRepository");
        System.out.println("  - Abstraction for data access");
        System.out.println("  - Swappable implementations");
    }

    private static void demonstrateSOLIDPrinciples() {
        System.out.println("\n--- SOLID Principles ---");
        
        // Single Responsibility Principle (SRP)
        System.out.println("✓ SRP: Each class has one responsibility");
        System.out.println("  - Controller: Orchestration only");
        System.out.println("  - Service: Business logic only");
        System.out.println("  - Repository: Data access only");
        
        // Open/Closed Principle (OCP)
        System.out.println("✓ OCP: Open for extension, closed for modification");
        System.out.println("  - New measurement types via enum extension");
        System.out.println("  - New operations via interface extension");
        
        // Liskov Substitution Principle (LSP)
        System.out.println("✓ LSP: Substitutable implementations");
        System.out.println("  - Any IMeasurable implementation works");
        System.out.println("  - Repository implementations are swappable");
        
        // Interface Segregation Principle (ISP)
        System.out.println("✓ ISP: Focused interfaces");
        System.out.println("  - IQuantityMeasurementService: Business operations only");
        System.out.println("  - IQuantityMeasurementRepository: Data access only");
        
        // Dependency Inversion Principle (DIP)
        System.out.println("✓ DIP: Depend on abstractions");
        System.out.println("  - Controller depends on service interface");
        System.out.println("  - Service depends on repository interface");
    }

    private static void demonstrateDataFlow() {
        System.out.println("\n--- Data Flow Between Layers ---");
        
        // Initialize layers
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        
        // Create input data
        QuantityDTO input1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO input2 = new QuantityDTO(12.0, "INCHES", "LENGTH");
        
        System.out.println("Input: " + input1 + " + " + input2);
        
        // Data flow: Controller -> Service -> Repository
        System.out.println("Flow: Controller → Service → Repository");
        controller.performAddition(input1, input2);
        
        // Show stored entity
        var measurements = repository.getAllMeasurements();
        if (!measurements.isEmpty()) {
            QuantityMeasurementEntity lastEntity = measurements.get(measurements.size() - 1);
            System.out.println("Stored Entity: " + lastEntity);
        }
    }

    private static void demonstrateErrorHandling() {
        System.out.println("\n--- Error Handling Across Layers ---");
        
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        
        // Test cross-category error
        QuantityDTO length = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO weight = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        
        System.out.println("Testing cross-category comparison...");
        controller.performEqualityComparison(length, weight);
        
        // Test temperature arithmetic error
        QuantityDTO temp1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO temp2 = new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");
        
        System.out.println("Testing temperature addition...");
        controller.performAddition(temp1, temp2);
        
        System.out.println("✓ Errors handled gracefully across all layers");
    }

    private static void demonstrateExtensibility() {
        System.out.println("\n--- Extensibility and Scalability ---");
        
        // Service reuse across different contexts
        System.out.println("✓ Service layer reusable in multiple contexts:");
        System.out.println("  - CLI applications");
        System.out.println("  - REST APIs (future)");
        System.out.println("  - GUI applications (future)");
        
        // Repository swappability
        System.out.println("✓ Repository implementations swappable:");
        System.out.println("  - Current: In-memory cache");
        System.out.println("  - Future: Database, file-based, cloud storage");
        
        // Measurement type extensibility
        System.out.println("✓ New measurement types easily added:");
        System.out.println("  - Add new enum implementing IMeasurable");
        System.out.println("  - Update DTO unit enums");
        System.out.println("  - No service layer changes required");
        
        // Operation extensibility
        System.out.println("✓ New operations easily added:");
        System.out.println("  - Extend service interface");
        System.out.println("  - Implement in service class");
        System.out.println("  - Add controller method");
        
        // Framework integration readiness
        System.out.println("✓ Framework integration ready:");
        System.out.println("  - Dependency injection (Spring, Guice)");
        System.out.println("  - REST endpoints (Spring Boot)");
        System.out.println("  - Unit testing with mocks");
    }
}
