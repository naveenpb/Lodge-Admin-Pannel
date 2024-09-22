import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField username;
    JPasswordField password;
    JButton cancel;
    JButton login;

    Login() {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(500, 200, 600, 300);

        JLabel user = new JLabel("Username");
        user.setBounds(60, 20, 100, 30);
        add(user);

        username = new JTextField();
        username.setBounds(150, 20, 100, 30);
        add(username);

        JLabel pass = new JLabel("Password");
        pass.setBounds(60, 70, 100, 30);
        add(pass);

        password = new JPasswordField();
        password.setBounds(150, 70, 100, 30);
        add(password);

        login = new JButton("LOGIN");
        login.setBounds(40, 150, 120, 20);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 150, 120, 20);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(300, 5, 200, 200);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String un = username.getText();
            String pass = password.getText();

            try {
                Conn c = new Conn();

                String query = "select * from login where username = '" + un + "' and password = '" + pass + "'";
                // we are sending string but there is varchar so we want to add single quotes

                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new Dashboard();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username and password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
