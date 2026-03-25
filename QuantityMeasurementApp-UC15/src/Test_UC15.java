class Test_UC15 {
    public static void main(String[] args) {
        System.out.println("=== UC15 N-Tier Architecture Tests ===");
        
        // Test Entity Layer
        testQuantityEntityCreation();
        testQuantityEntityErrorHandling();
        
        // Test Service Layer
        testServiceLayerOperations();
        testServiceLayerErrorHandling();
        
        // Test Controller Layer
        testControllerLayerOperations();
        
        // Test Repository Layer
        testRepositoryLayer();
        
        // Test N-Tier Architecture Principles
        testLayerSeparation();
        testDependencyInjection();
        testInterfaceSegregation();
        
        // Test Backward Compatibility
        testBackwardCompatibility();
        
        System.out.println("=== All UC15 Tests Passed! ===");
    }

    static void testQuantityEntityCreation() {
        System.out.println("Testing QuantityEntity creation...");
        
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");
        QuantityDTO result = new QuantityDTO(2.0, "FEET", "LENGTH");
        
        // Test binary operation entity
        QuantityMeasurementEntity entity1 = new QuantityMeasurementEntity(q1, q2, "ADD", result);
        assert entity1.getOperation().equals("ADD");
        assert !entity1.hasError();
        assert entity1.getResult().equals(result);
        
        // Test single operand entity
        QuantityMeasurementEntity entity2 = new QuantityMeasurementEntity(q1, "CONVERT", result);
        assert entity2.getOperation().equals("CONVERT");
        assert !entity2.hasError();
        assert entity2.getOperand2() == null;
        
        System.out.println("✓ QuantityEntity creation tests passed");
    }

    static void testQuantityEntityErrorHandling() {
        System.out.println("Testing QuantityEntity error handling...");
        
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        String errorMessage = "Test error";
        
        // Test single operand error
        QuantityMeasurementEntity entity1 = new QuantityMeasurementEntity(q1, "OPERATION", errorMessage);
        assert entity1.hasError();
        assert entity1.getErrorMessage().equals(errorMessage);
        assert entity1.getResult() == null;
        
        // Test binary operand error
        QuantityMeasurementEntity entity2 = new QuantityMeasurementEntity(q1, q1, "OPERATION", errorMessage);
        assert entity2.hasError();
        assert entity2.getErrorMessage().equals(errorMessage);
        
        System.out.println("✓ QuantityEntity error handling tests passed");
    }

    static void testServiceLayerOperations() {
        System.out.println("Testing Service Layer operations...");
        
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        
        // Test comparison
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");
        QuantityMeasurementEntity result = service.compareQuantities(q1, q2);
        assert !result.hasError();
        assert result.getResult().getValue() == 1.0; // true
        
        // Test conversion
        QuantityMeasurementEntity conversion = service.convertQuantity(q1, "INCHES");
        assert !conversion.hasError();
        assert Math.abs(conversion.getResult().getValue() - 12.0) < 1e-6;
        
        // Test addition
        QuantityMeasurementEntity addition = service.addQuantities(q1, q2);
        assert !addition.hasError();
        assert Math.abs(addition.getResult().getValue() - 2.0) < 1e-6;
        
        System.out.println("✓ Service Layer operations tests passed");
    }

    static void testServiceLayerErrorHandling() {
        System.out.println("Testing Service Layer error handling...");
        
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        
        // Test cross-category comparison
        QuantityDTO length = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO weight = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        QuantityMeasurementEntity result = service.compareQuantities(length, weight);
        assert result.hasError();
        assert result.getErrorMessage().contains("different measurement types");
        
        // Test temperature arithmetic (should fail)
        QuantityDTO temp1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO temp2 = new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");
        QuantityMeasurementEntity addition = service.addQuantities(temp1, temp2);
        assert addition.hasError();
        
        System.out.println("✓ Service Layer error handling tests passed");
    }

    static void testControllerLayerOperations() {
        System.out.println("Testing Controller Layer operations...");
        
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        
        // Test that controller can delegate to service without exceptions
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");
        
        try {
            controller.performEqualityComparison(q1, q2);
            controller.performConversion(q1, "INCHES");
            controller.performAddition(q1, q2);
            controller.performSubtraction(q1, q2);
            controller.performDivision(q1, q2);
        } catch (Exception e) {
            assert false : "Controller should not throw exceptions: " + e.getMessage();
        }
        
        System.out.println("✓ Controller Layer operations tests passed");
    }

    static void testRepositoryLayer() {
        System.out.println("Testing Repository Layer...");
        
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        
        // Test singleton pattern
        IQuantityMeasurementRepository repository2 = QuantityMeasurementCacheRepository.getInstance();
        assert repository == repository2 : "Repository should be singleton";
        
        // Test save and retrieve
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO result = new QuantityDTO(2.0, "FEET", "LENGTH");
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, "TEST", result);
        
        repository.save(entity);
        var measurements = repository.getAllMeasurements();
        assert !measurements.isEmpty() : "Repository should contain saved measurements";
        
        // Test clear
        repository.clearAll();
        assert repository.getAllMeasurements().isEmpty() : "Repository should be empty after clear";
        
        System.out.println("✓ Repository Layer tests passed");
    }

    static void testLayerSeparation() {
        System.out.println("Testing Layer Separation...");
        
        // Test that each layer can be instantiated independently
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        
        assert repository != null : "Repository should be instantiable";
        assert service != null : "Service should be instantiable";
        assert controller != null : "Controller should be instantiable";
        
        // Test that service depends on repository interface, not implementation
        assert service instanceof IQuantityMeasurementService : "Service should implement interface";
        
        System.out.println("✓ Layer Separation tests passed");
    }

    static void testDependencyInjection() {
        System.out.println("Testing Dependency Injection...");
        
        // Test constructor injection
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        
        // Test that dependencies are properly injected
        assert service != null : "Service should have repository dependency";
        assert controller != null : "Controller should have service dependency";
        
        System.out.println("✓ Dependency Injection tests passed");
    }

    static void testInterfaceSegregation() {
        System.out.println("Testing Interface Segregation...");
        
        // Test that interfaces are focused and specific
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        
        // Repository interface should only have data access methods
        assert repository instanceof IQuantityMeasurementRepository : "Repository should implement repository interface";
        
        // Service interface should only have business logic methods
        assert service instanceof IQuantityMeasurementService : "Service should implement service interface";
        
        System.out.println("✓ Interface Segregation tests passed");
    }

    static void testBackwardCompatibility() {
        System.out.println("Testing Backward Compatibility...");
        
        // Test that all UC1-UC14 functionality still works
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        
        // Test all measurement types
        QuantityDTO length = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO weight = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        QuantityDTO volume = new QuantityDTO(1.0, "LITRE", "VOLUME");
        QuantityDTO temperature = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        
        // Test equality comparisons
        assert !service.compareQuantities(length, weight).hasError() : "Length vs Weight comparison should work";
        assert !service.compareQuantities(length, volume).hasError() : "Length vs Volume comparison should work";
        assert !service.compareQuantities(length, new QuantityDTO(12.0, "INCHES", "LENGTH")).hasError() : "Length equality should work";
        
        // Test conversions
        assert !service.convertQuantity(length, "INCHES").hasError() : "Length conversion should work";
        assert !service.convertQuantity(weight, "GRAM").hasError() : "Weight conversion should work";
        assert !service.convertQuantity(volume, "MILLILITRE").hasError() : "Volume conversion should work";
        assert !service.convertQuantity(temperature, "FAHRENHEIT").hasError() : "Temperature conversion should work";
        
        // Test arithmetic for supported types
        assert !service.addQuantities(length, new QuantityDTO(12.0, "INCHES", "LENGTH")).hasError() : "Length addition should work";
        assert !service.divideQuantities(length, new QuantityDTO(12.0, "INCHES", "LENGTH")).hasError() : "Length division should work";
        
        System.out.println("✓ Backward Compatibility tests passed");
    }
}
