import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HotelManagement extends JFrame implements ActionListener{
    
    HotelManagement(){
        // setSize(1366,565);
        
        // setLocation(40,100);
        // insted of using above statements we caan also use 
        setBounds(40 , 100, 1336, 565);
        setLayout(null);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1600, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(300, 5, 200, 200);
        add(image);



        JLabel text = new JLabel("LODGE ADMIN PANNEL");
        text.setBounds(200,430,600,100);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("serif" , Font.PLAIN ,40));
        image.add(text);//to show text above image
        image.setBounds(0,0,1336,565);

        JButton next = new  JButton("Next");
        next.setBounds(1150, 450, 150, 50);
        next.setBackground(Color.pink);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("serif" , Font.BOLD,20));
        image.add(next);
        next.addActionListener(this);


        setVisible(true);

        while (true) {
            text.setVisible(false);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
            text.setVisible(true);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }
    }

    public  void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        new HotelManagement();
    }
}
