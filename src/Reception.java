import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Reception extends JFrame implements ActionListener {

    JButton newcustomer, rooms, allEmployee, customerinfo, managerinfo, checkout, logout, updatestatus, searchrooms;

    Reception() {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(350, 200, 800, 570);

        newcustomer = new JButton("New Customer Form");
        newcustomer.setBounds(10, 30, 200, 30);
        newcustomer.setBackground(Color.BLACK);
        newcustomer.setForeground(Color.WHITE);
        newcustomer.addActionListener(this);
        add(newcustomer);

        rooms = new JButton("ROOMS");
        rooms.setBounds(10, 70, 200, 30);
        rooms.setBackground(Color.BLACK);
        rooms.setForeground(Color.WHITE);
        rooms.addActionListener(this);
        add(rooms);

        allEmployee = new JButton("All Employee");
        allEmployee.setBounds(10, 110, 200, 30);
        allEmployee.setBackground(Color.BLACK);
        allEmployee.setForeground(Color.WHITE);
        allEmployee.addActionListener(this);
        add(allEmployee);

        customerinfo = new JButton("Customer Info");
        customerinfo.setBounds(10, 150, 200, 30);
        customerinfo.setBackground(Color.BLACK);
        customerinfo.setForeground(Color.WHITE);
        customerinfo.addActionListener(this);
        add(customerinfo);

        managerinfo = new JButton("Manager Info");
        managerinfo.setBounds(10, 190, 200, 30);
        managerinfo.setBackground(Color.BLACK);
        managerinfo.setForeground(Color.WHITE);
        managerinfo.addActionListener(this);
        add(managerinfo);

        checkout = new JButton("Customer Checkout");
        checkout.setBounds(10, 230, 200, 30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.addActionListener(this);
        add(checkout);

        updatestatus = new JButton("Update Status");
        updatestatus.setBounds(10, 270, 200, 30);
        updatestatus.setBackground(Color.BLACK);
        updatestatus.setForeground(Color.WHITE);
        updatestatus.addActionListener(this);
        add(updatestatus);

        // searchrooms = new JButton("SEARCH ROOMS");
        // searchrooms.setBounds(10, 310, 200, 30);
        // searchrooms.setBackground(Color.BLACK);
        // searchrooms.setForeground(Color.WHITE);
        // searchrooms.addActionListener(this);
        // add(searchrooms);

        logout = new JButton("LOG-OUT");
        logout.setBounds(10, 350, 200, 30);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        add(logout);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250, 10, 500, 470);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == newcustomer) {
            new AddCustomer();
        } else if (ae.getSource() == rooms) {
            new Room();
        } else if (ae.getSource() == allEmployee) {
            new Employeeinfo();
        } else if (ae.getSource() == customerinfo) {
            new CustomerInfo();
        } else if (ae.getSource() == managerinfo) {
            new ManagerInfo();
        } else if (ae.getSource() == checkout) {
            try {
                new CheckOut();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: Failed to open Checkout. Please check database connection.");
            }
        } else if (ae.getSource() == updatestatus) {
            EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateCheck frame = new UpdateCheck();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        }  else if (ae.getSource() == logout) {
            // Logout action
            dispose();
            setVisible(false);
            new Login().setVisible(true); // Replace with your login frame or main frame
        }
    }

    public static void main(String[] args) {
        new Reception();
    }
}
