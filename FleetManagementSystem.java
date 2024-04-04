
import java.util.Map;
import java.util.Scanner;

public class FleetManagementSystem {
    public static void main(String[] args) {
        Fleet fleet = new Fleet();
        Scanner scanner = new Scanner(System.in);

        // Menu-driven program
        while (true) {
            System.out.println("\nFleet Management System Menu:");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. Update Vehicle Details");
            System.out.println("4. Remove Vehicle");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addVehicle(scanner, fleet);
                    break;
                case 2:
                    viewAllVehicles(fleet);
                    break;
                case 3:
                    updateVehicle(scanner, fleet);
                    break;
                case 4:
                    removeVehicle(scanner, fleet);
                    break;
                case 5:
                    System.out.println("Exiting Fleet Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addVehicle(Scanner scanner, Fleet fleet) {
        System.out.println("Enter details for the new vehicle:");
        System.out.print("ID: ");
        String id = scanner.next();

        System.out.print("Make: ");
        String make = scanner.next();

        System.out.print("Model: ");
        String model = scanner.next();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        Vehicle newVehicle = new Vehicle(id, make, model, year);
        fleet.addVehicle(newVehicle);
        System.out.println("Vehicle added successfully!");
    }

    private static void viewAllVehicles(Fleet fleet) {
        System.out.println("All Vehicles in the Fleet:");
        Map<String, Vehicle> vehicleMap = fleet.getAllVehicles();
        for (Vehicle vehicle : vehicleMap.values()) {
            System.out.println("ID: " + vehicle.getId() +
                    ", Make: " + vehicle.getMake() +
                    ", Model: " + vehicle.getModel() +
                    ", Year: " + vehicle.getYear());
        }
    }

    private static void updateVehicle(Scanner scanner, Fleet fleet) {
        System.out.print("Enter the ID of the vehicle to update: ");
        String idToUpdate = scanner.next();

        Vehicle existingVehicle = fleet.getVehicleById(idToUpdate);
        if (existingVehicle != null) {
            System.out.println("Enter the updated details:");
            System.out.print("Make: ");
            String make = scanner.next();

            System.out.print("Model: ");
            String model = scanner.next();

            System.out.print("Year: ");
            int year = scanner.nextInt();

            Vehicle updatedVehicle = new Vehicle(idToUpdate, make, model, year);
            fleet.updateVehicle(idToUpdate, updatedVehicle);
            System.out.println("Vehicle updated successfully!");
        } else {
            System.out.println("Vehicle with ID " + idToUpdate + " not found.");
        }
    }

    private static void removeVehicle(Scanner scanner, Fleet fleet) {
        System.out.print("Enter the ID of the vehicle to remove: ");
        String idToRemove = scanner.next();

        fleet.removeVehicle(idToRemove);
        System.out.println("Vehicle removed successfully!");
    }
}