

import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CustomerInfo extends JFrame implements ActionListener {
    JTable table;
    JButton back, loadData;

    CustomerInfo() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 800, 570);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(31, 11, 46, 14);
        add(lblId);

        JLabel l1 = new JLabel("Number");
        l1.setBounds(150, 11, 46, 14);
        add(l1);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(270, 11, 65, 14);
        add(lblNewLabel);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(360, 11, 46, 14);
        add(lblGender);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(480, 11, 46, 14);
        add(lblCountry);

        JLabel lblRoom = new JLabel("Room");
        lblRoom.setBounds(600, 11, 46, 14);
        add(lblRoom);

        JLabel lblStatus = new JLabel("Check-in Status");
        lblStatus.setBounds(680, 11, 100, 14);
        add(lblStatus);

        JLabel lblNewLabel_1 = new JLabel("Deposit");
        lblNewLabel_1.setBounds(800, 11, 100, 14);
        add(lblNewLabel_1);

        table = new JTable();
        table.setBounds(0, 40, 900, 450);
        add(table);

        loadData = new JButton("Load Data");
        loadData.setBounds(300, 510, 120, 30);
        loadData.setBackground(Color.BLACK);
        loadData.setForeground(Color.WHITE);
        loadData.addActionListener(this);
        add(loadData);

        back = new JButton("Back");
        back.setBounds(450, 510, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loadData) {
            try {
                Conn c = new Conn();
                String displayCustomersql = "select * from Customer";
                ResultSet rs = c.s.executeQuery(displayCustomersql);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            new Reception().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CustomerInfo();
    }
}
