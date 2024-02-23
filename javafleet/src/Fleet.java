import java.util.HashMap;
import java.util.Map;

public class Fleet {
    private Map<String, Vehicle> vehicleMap;

    public Fleet() {
        this.vehicleMap = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleMap.put(vehicle.getId(), vehicle);
    }

    public Vehicle getVehicleById(String id) {
        return vehicleMap.get(id);
    }

    public Map<String, Vehicle> getAllVehicles() {
        return vehicleMap;
    }

    public void updateVehicle(String id, Vehicle updatedVehicle) {
        if (vehicleMap.containsKey(id)) {
            vehicleMap.put(id, updatedVehicle);
        } else {
            System.out.println("Vehicle with ID " + id + " not found.");
        }
    }

    public void removeVehicle(String id) {
        if (vehicleMap.containsKey(id)) {
            vehicleMap.remove(id);
        } else {
            System.out.println("Vehicle with ID " + id + " not found.");
        }
    }
}
