import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class FleetManagementSystemGUI {
    private final Fleet fleet;
    private final JFrame frame;
    private final JTextArea outputTextArea;

    public FleetManagementSystemGUI() {
        JTextArea outputTextArea1;
        fleet = new Fleet();
        frame = new JFrame("Fleet Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Load background image
        ImageIcon backgroundImageIcon = new ImageIcon("cars.jpg"); // Change "background.jpg" to your image file path
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT); // Set
                                                                                                                 // the
                                                                                                                 // desired
                                                                                                                 // width
                                                                                                                 // and
                                                                                                                 // height
        ImageIcon backgroundImageScaled = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(backgroundImageScaled);
        backgroundLabel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundLabel);

        outputTextArea1 = new JTextArea();
        outputTextArea = outputTextArea1;
        outputTextArea.setOpaque(false); // Set JTextArea to be transparent
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        backgroundLabel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5));

        JButton addVehicleButton = new JButton("Add Vehicle");
        addVehicleButton.addActionListener(e -> addVehicle());
        buttonPanel.add(addVehicleButton);

        JButton viewAllButton = new JButton("View All Vehicles");
        viewAllButton.addActionListener(e -> viewAllVehicles());
        buttonPanel.add(viewAllButton);

        JButton updateButton = new JButton("Update Vehicle");
        updateButton.addActionListener(e -> updateVehicle());
        buttonPanel.add(updateButton);

        JButton removeButton = new JButton("Remove Vehicle");
        removeButton.addActionListener(e -> removeVehicle());
        buttonPanel.add(removeButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addVehicle() {
        String id = JOptionPane.showInputDialog(frame, "Enter ID:");
        String make = JOptionPane.showInputDialog(frame, "Enter Make:");
        String model = JOptionPane.showInputDialog(frame, "Enter Model:");
        try {
            int year = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Year:"));
            Vehicle newVehicle = new Vehicle(id, make, model, year);
            fleet.addVehicle(newVehicle);
            outputTextArea.append("Vehicle added successfully!\n");
        } catch (NumberFormatException e) {
            outputTextArea.append("Invalid year format!\n");
        }
    }

    private void viewAllVehicles() {
        outputTextArea.append("All Vehicles in the Fleet:\n");
        Map<String, Vehicle> vehicleMap = fleet.getAllVehicles();
        for (Vehicle vehicle : vehicleMap.values()) {
            outputTextArea.append("ID: " + vehicle.getId() +
                    ", Make: " + vehicle.getMake() +
                    ", Model: " + vehicle.getModel() +
                    ", Year: " + vehicle.getYear() + "\n");
        }
    }

    private void updateVehicle() {
        String idToUpdate = JOptionPane.showInputDialog(frame, "Enter the ID of the vehicle to update:");
        Vehicle existingVehicle = fleet.getVehicleById(idToUpdate);
        if (existingVehicle != null) {
            String make = JOptionPane.showInputDialog(frame, "Enter Make:");
            String model = JOptionPane.showInputDialog(frame, "Enter Model:");
            try {
                int year = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Year:"));
                Vehicle updatedVehicle = new Vehicle(idToUpdate, make, model, year);
                fleet.updateVehicle(idToUpdate, updatedVehicle);
                outputTextArea.append("Vehicle updated successfully!\n");
            } catch (NumberFormatException e) {
                outputTextArea.append("Invalid year format!\n");
            }
        } else {
            outputTextArea.append("Vehicle with ID " + idToUpdate + " not found.\n");
        }
    }

    private void removeVehicle() {
        String idToRemove = JOptionPane.showInputDialog(frame, "Enter the ID of the vehicle to remove:");
        fleet.removeVehicle(idToRemove);
        outputTextArea.append("Vehicle removed successfully!\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FleetManagementSystemGUI::new);
    }
}
