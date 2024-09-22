import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

public class AddRooms extends JFrame implements ActionListener {

    JButton add, cancel;
    JTextField tfroom, tfprice;
    JComboBox<String> typecombo, availablecombo, cleancombo;

    AddRooms() {
        setLayout(null);
        setBounds(350, 200, 800, 570);
        getContentPane().setBackground(Color.white);

        JLabel heading = new JLabel("ADD ROOMS");
        heading.setFont(new Font("Tahoma", Font.BOLD, 16));
        heading.setBounds(150, 20, 200, 20);
        add(heading);

        JLabel roomnumber = new JLabel("ROOM NUMBER");
        roomnumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        roomnumber.setBounds(60, 80, 120, 30);
        add(roomnumber);

        tfroom = new JTextField();
        tfroom.setBounds(200, 80, 150, 30);
        add(tfroom);

        JLabel lblavailable = new JLabel("AVAILABLE");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblavailable.setBounds(60, 130, 120, 30);
        add(lblavailable);

        String availableOptions[] = {"Available", "Not Available"};
        availablecombo = new JComboBox<>(availableOptions);
        availablecombo.setBounds(200, 130, 150, 30);
        availablecombo.setBackground(Color.white);
        add(availablecombo);

        JLabel lblClean = new JLabel("CLEANING STATUS");
        lblClean.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblClean.setBounds(60, 180, 150, 30);
        add(lblClean);

        String cleaningOptions[] = {"Clean", "Dirty"};
        cleancombo = new JComboBox<>(cleaningOptions);
        cleancombo.setBounds(200, 180, 150, 30);
        cleancombo.setBackground(Color.white);
        add(cleancombo);

        JLabel lblprice = new JLabel("PRICE");
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblprice.setBounds(60, 230, 120, 30);
        add(lblprice);

        tfprice = new JTextField();
        tfprice.setBounds(200, 230, 150, 30);
        add(tfprice);

        JLabel lbltype = new JLabel("ROOM TYPE");
        lbltype.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbltype.setBounds(60, 280, 120, 30);
        add(lbltype);

        String typeOptions[] = {"Single Bed", "Double Bed"};
        typecombo = new JComboBox<>(typeOptions);
        typecombo.setBounds(200, 280, 150, 30);
        typecombo.setBackground(Color.white);
        add(typecombo);

        add = new JButton("ADD");
        add.setBounds(60, 350, 130, 30);
        add.setForeground(Color.BLACK);
        add.setBackground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        cancel = new JButton("CANCEL");
        cancel.setBounds(220, 350, 130, 30);
        cancel.setForeground(Color.BLACK);
        cancel.setBackground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 30, 500, 300);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String room = tfroom.getText();
            String available = (String) availablecombo.getSelectedItem();
            String status = (String) cleancombo.getSelectedItem();
            String price = tfprice.getText();
            String type = (String) typecombo.getSelectedItem();

            try {
                Conn c = new Conn();

                String str = "INSERT INTO room values('" + room + "', '" + available + "', '" + status + "', '" + price + "', '" + type + "')";
                c.s.executeUpdate(str);

                JOptionPane.showMessageDialog(null, "NEW ROOM UPDATED SUCCESSFULLY");

                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddRooms();
    }
}
