public class QuantityMeasurementController {
    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performEqualityComparison(QuantityDTO quantity1, QuantityDTO quantity2) {
        QuantityMeasurementEntity result = service.compareQuantities(quantity1, quantity2);
        displayResult(result);
    }

    public void performConversion(QuantityDTO quantity, String targetUnit) {
        QuantityMeasurementEntity result = service.convertQuantity(quantity, targetUnit);
        displayResult(result);
    }

    public void performAddition(QuantityDTO quantity1, QuantityDTO quantity2) {
        QuantityMeasurementEntity result = service.addQuantities(quantity1, quantity2);
        displayResult(result);
    }

    public void performSubtraction(QuantityDTO quantity1, QuantityDTO quantity2) {
        QuantityMeasurementEntity result = service.subtractQuantities(quantity1, quantity2);
        displayResult(result);
    }

    public void performDivision(QuantityDTO quantity1, QuantityDTO quantity2) {
        QuantityMeasurementEntity result = service.divideQuantities(quantity1, quantity2);
        displayResult(result);
    }

    public void displayMeasurementHistory() {
        System.out.println("\n--- Measurement History ---");
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        repository.getAllMeasurements().forEach(System.out::println);
        System.out.println("--- End of History ---\n");
    }

    private void displayResult(QuantityMeasurementEntity entity) {
        if (entity.hasError()) {
            System.out.println("ERROR: " + entity.getErrorMessage());
        } else {
            System.out.println("SUCCESS: " + entity);
        }
    }
}
