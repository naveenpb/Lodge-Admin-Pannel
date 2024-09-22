import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CheckOut extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    private JPanel contentPane;
    private JTextField t1;
    Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CheckOut frame = new CheckOut();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void close() {
        this.dispose();
    }

    public CheckOut() throws SQLException {
        // Establish the database connection
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "Naveen@123");
            System.out.println("Database connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Connection Failed");
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 200, 800, 570);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
            Image i3 = i1.getImage().getScaledInstance(400, 225, Image.SCALE_DEFAULT);
            ImageIcon i2 = new ImageIcon(i3);
            JLabel l1 = new JLabel(i2);
            l1.setBounds(300, 0, 500, 225);
            add(l1);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Image Loading Failed");
        }

        JLabel lblCheckOut = new JLabel("Check Out ");
        lblCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCheckOut.setBounds(70, 11, 140, 35);
        contentPane.add(lblCheckOut);

        JLabel lblName = new JLabel("Number :");
        lblName.setBounds(20, 85, 80, 14);
        contentPane.add(lblName);

        c1 = new Choice();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setBounds(130, 82, 150, 20);
        contentPane.add(c1);

        try {
            ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
            Image i5 = i4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            ImageIcon i6 = new ImageIcon(i5);
            JButton l2 = new JButton(i6);
            l2.setBounds(290, 82, 20, 20);
            add(l2);

            l2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        String number = c1.getSelectedItem();
                        String query = "SELECT * FROM customer WHERE number = ?";
                        pst = conn.prepareStatement(query);
                        pst.setString(1, number);
                        ResultSet rs = pst.executeQuery();

                        if (rs.next()) {
                            t1.setText(rs.getString("room"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Button Initialization Failed");
        }

        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setBounds(20, 132, 86, 20);
        contentPane.add(lblRoomNumber);

        t1 = new JTextField();
        t1.setBounds(130, 132, 150, 20);
        contentPane.add(t1);

        JButton btnAdd = new JButton("Check");
        btnAdd.setBounds(120, 240, 90, 25);
        btnAdd.setBackground(Color.BLACK);
        btnAdd.setForeground(Color.white);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String s1 = c1.getSelectedItem();
                    String query = "SELECT * FROM customer WHERE number = ?";
                    pst = conn.prepareStatement(query);
                    pst.setString(1, s1);
                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        t1.setText(rs.getString("room"));
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });

        JButton btnCheckOut = new JButton("Check Out");
        btnCheckOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = c1.getSelectedItem();
                String roomNumber = t1.getText();
                String deleteSQL = "DELETE FROM customer WHERE number = ?";
                String updateSQL = "UPDATE room SET availability = 'Available' WHERE room_number = ?";

                try {
                    conn.setAutoCommit(false); // Start transaction

                    pst = conn.prepareStatement(deleteSQL);
                    pst.setString(1, number);
                    pst.executeUpdate();

                    pst = conn.prepareStatement(updateSQL);
                    pst.setString(1, roomNumber);
                    pst.executeUpdate();

                    conn.commit(); // Commit transaction

                    JOptionPane.showMessageDialog(null, "Check Out Successful");
                    new Reception().setVisible(true);
                    setVisible(false);
                } catch (SQLException e1) {
                    try {
                        conn.rollback(); // Rollback transaction in case of error
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    e1.printStackTrace();
                }
            }
        });

        btnCheckOut.setBounds(50, 200, 100, 25);
        btnCheckOut.setBackground(Color.BLACK);
        btnCheckOut.setForeground(Color.WHITE);
        contentPane.add(btnCheckOut);
        contentPane.add(btnAdd);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(160, 200, 100, 25);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
}
