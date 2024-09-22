import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Updateroom extends JFrame {
    private JPanel contentPane;
    private JTextField txtRoomNumber;
    private JTextField txtAvailability;
    private JTextField txtCleanStatus;
    private Choice guestIDChoice;
    private Conn connection;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Updateroom frame = new Updateroom();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Updateroom() {
        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 1000, 450);
        setTitle("Update Room Status");

        // Initialize content pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Main panel settings
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Guest ID label and choice
        JLabel lblGuestID = new JLabel("Guest ID:");
        lblGuestID.setBounds(27, 87, 90, 14);
        panel.add(lblGuestID);

        guestIDChoice = new Choice();
        guestIDChoice.setBounds(160, 84, 140, 20);
        panel.add(guestIDChoice);

        // Room Number label and text field
        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setBounds(27, 133, 100, 14);
        panel.add(lblRoomNumber);

        txtRoomNumber = new JTextField();
        txtRoomNumber.setBounds(160, 130, 140, 20);
        txtRoomNumber.setEditable(false);
        panel.add(txtRoomNumber);
        txtRoomNumber.setColumns(10);

        // Availability label and text field
        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setBounds(27, 187, 90, 14);
        panel.add(lblAvailability);

        txtAvailability = new JTextField();
        txtAvailability.setBounds(160, 184, 140, 20);
        panel.add(txtAvailability);
        txtAvailability.setColumns(10);

        // Clean Status label and text field
        JLabel lblCleanStatus = new JLabel("Clean Status:");
        lblCleanStatus.setBounds(27, 240, 90, 14);
        panel.add(lblCleanStatus);

        txtCleanStatus = new JTextField();
        txtCleanStatus.setBounds(160, 237, 140, 20);
        panel.add(txtCleanStatus);
        txtCleanStatus.setColumns(10);

        // Check button
        JButton btnCheck = new JButton("Check");
        btnCheck.addActionListener(this::checkRoomDetails);
        btnCheck.setBounds(120, 315, 89, 23);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        panel.add(btnCheck);

        // Update button
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this::updateRoomStatus);
        btnUpdate.setBounds(60, 355, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        panel.add(btnUpdate);

        // Back button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            dispose();
        });
        btnBack.setBounds(180, 355, 89, 23);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        panel.add(btnBack);

        // Image label
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(550, 250, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(400, 80, 600, 250);
        panel.add(imageLabel);

        // Fetch guest IDs and populate choice
        fetchGuestIDs();
    }

    private void fetchGuestIDs() {
        try {
            connection = new Conn();
            ResultSet rs = connection.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                guestIDChoice.add(rs.getString("number"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching data from database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void checkRoomDetails(ActionEvent e) {
        try {
            String selectedGuestID = guestIDChoice.getSelectedItem();
            ResultSet rs = connection.s.executeQuery("SELECT * FROM customer WHERE number = '" + selectedGuestID + "'");
            if (rs.next()) {
                txtRoomNumber.setText(rs.getString("room"));
            }

            ResultSet roomDetails = connection.s.executeQuery("SELECT * FROM room WHERE room_number = '" + txtRoomNumber.getText() + "'");
            if (roomDetails.next()) {
                txtAvailability.setText(roomDetails.getString("availability"));
                txtCleanStatus.setText(roomDetails.getString("cleaning_status"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error fetching room details: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void updateRoomStatus(ActionEvent e) {
        try {
            String roomNumber = txtRoomNumber.getText();
            String availability = txtAvailability.getText();
            String cleanStatus = txtCleanStatus.getText();
            String updateQuery = "UPDATE room SET availability = '" + availability + "', cleaning_status = '" + cleanStatus + "' WHERE room_number = '" + roomNumber + "'";
            connection.s.executeUpdate(updateQuery);
            JOptionPane.showMessageDialog(null, "Room status updated successfully", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            new Reception().setVisible(true);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating room status: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Inner class for database connection
    static class Conn {
        Connection c;
        Statement s;

        public Conn() {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=hotelmanagement", "root", "Naveen@123");
                s = c.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
