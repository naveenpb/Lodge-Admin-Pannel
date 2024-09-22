import javax.swing.*;
// import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Employeeinfo extends JFrame implements ActionListener {
    JTable table ;
    JButton back;
    Employeeinfo(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(300, 200, 1000, 600);

        // ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        // Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        // ImageIcon i3 = new ImageIcon(i2);
        // JLabel image = new JLabel(i3);
        // image.setBounds(500, 5, 600, 600);
        // add(image);

        JLabel l1 = new JLabel("NAME");
        l1.setBounds(40,10,100,20);
        add(l1); 
            
        JLabel l2 = new JLabel("AGE");
        l2.setBounds(170,10,100,20);
        add(l2);
        
        JLabel l3 = new JLabel("GENDER");
        l3.setBounds(290,10,100,20);
        add(l3);
        
        JLabel l4 = new JLabel("JOB");
        l4.setBounds(400,10,100,20);
        add(l4);
        
        JLabel l5 = new JLabel("SALARY");
        l5.setBounds(540,10,100,20);
        add(l5);

        JLabel l6 = new JLabel("PHONE");
        l6.setBounds(670,10,100,20);
        add(l6);

        JLabel l7 = new JLabel("E-MAIL");
        l7.setBounds(790,10,100,20);
        add(l7);

        JLabel l8 = new JLabel("AADHAR");
        l8.setBounds(910,10,100,20);
        add(l8);

        

        table = new JTable();
        table.setBounds(0 ,40,1000,400);
        add(table);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        setBounds(350, 200, 800, 570);

        back = new JButton("Back");
        back.setBounds(420, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);


        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Reception();
        
    }

public static void main(String[] args) {
    new Employeeinfo();
}
}
