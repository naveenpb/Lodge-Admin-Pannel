import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener {

    JComboBox<String> comboid;
    JTextField tfnumber, tfname, tfcountry, tcheckin, tfdeposit;
    JRadioButton rbmale, rbfemale;
    Choice c1;
    JButton add, back;

    AddCustomer() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 800, 570);

        JLabel text = new JLabel("New Customer Form");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(text);

        JLabel lblid = new JLabel("ID");
        lblid.setBounds(35, 80, 300, 30);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblid);

        String options[] = {"Aadhar card", "Passport", "Voter id"};
        comboid = new JComboBox<>(options);
        comboid.setBounds(200, 80, 150, 25);
        comboid.setBackground(Color.WHITE);
        add(comboid);

        JLabel lblnumber = new JLabel("ID Number");
        lblnumber.setBounds(35, 120, 300, 30);
        lblnumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblnumber);

        tfnumber = new JTextField();
        tfnumber.setBounds(200, 120, 150, 25);
        add(tfnumber);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 160, 300, 30);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(35, 200, 120, 30);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(200, 200, 70, 30);
        rbmale.setFont(new Font("", Font.PLAIN, 14));
        rbmale.setBackground(Color.white);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(270, 200, 90, 30);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbfemale.setBackground(Color.white);
        add(rbfemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbfemale);
        bg.add(rbmale);

        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(35, 240, 300, 30);
        lblcountry.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblcountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);

        JLabel lblroomno = new JLabel("Room No");
        lblroomno.setBounds(35, 280, 300, 30);
        lblroomno.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblroomno);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room where availability = 'Available'");
            while (rs.next()) {
                c1.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(200, 280, 150, 25);
        add(c1);

        JLabel lblcheckin = new JLabel("Check-in date");
        lblcheckin.setBounds(35, 320, 300, 30);
        lblcheckin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblcheckin);

        // Get current date and format it
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = formatter.format(date);

        tcheckin = new JTextField(currentDate);
        tcheckin.setBounds(200, 320, 150, 25);
        add(tcheckin);

        JLabel lbldeposit = new JLabel("Deposit");
        lbldeposit.setBounds(35, 360, 300, 30);
        lbldeposit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lbldeposit);

        tfdeposit = new JTextField();
        tfdeposit.setBounds(200, 360, 150, 25);
        add(tfdeposit);

        add = new JButton("Add");
        add.setBounds(50, 430, 120, 30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBounds(200, 430, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String id = (String) comboid.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = rbmale.isSelected() ? "Male" : "Female";
            String country = tfcountry.getText();
            String room = c1.getSelectedItem();
            String status = tcheckin.getText();
            String deposit = tfdeposit.getText();

            try {
                Conn c = new Conn();
                String str = "INSERT INTO customer (id, number, name, gender, country, room, status, deposit) VALUES ('" + id + "', '" + number + "', '" + name + "', '" + gender + "', '" + country + "', '" + room + "', '" + status + "', '" + deposit + "')";
                String str2 = "update room set availability = 'Occupied' where room_number = '" + room + "'";
                c.s.executeUpdate(str);
                c.s.executeUpdate(str2);

                JOptionPane.showMessageDialog(null, "New Customer Added Successfully");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
