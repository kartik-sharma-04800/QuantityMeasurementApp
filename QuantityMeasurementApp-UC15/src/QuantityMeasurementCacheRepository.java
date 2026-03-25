import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
    private static QuantityMeasurementCacheRepository instance;
    private final List<QuantityMeasurementEntity> measurements;
    private static final String FILE_NAME = "quantity_measurements.ser";

    private QuantityMeasurementCacheRepository() {
        this.measurements = new ArrayList<>();
        loadFromDisk();
    }

    public static QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        measurements.add(entity);
        saveToDisk();
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(measurements);
    }

    @Override
    public void clearAll() {
        measurements.clear();
        saveToDisk();
    }

    private void saveToDisk() {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new AppendableObjectOutputStream(fos)) {
            for (QuantityMeasurementEntity entity : measurements) {
                oos.writeObject(entity);
            }
        } catch (IOException e) {
            System.err.println("Error saving measurements to disk: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromDisk() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            measurements.clear();
            while (true) {
                try {
                    QuantityMeasurementEntity entity = (QuantityMeasurementEntity) ois.readObject();
                    measurements.add(entity);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading measurements from disk: " + e.getMessage());
            measurements.clear();
        }
    }

    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(FileOutputStream fos) throws IOException {
            super(fos);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}
