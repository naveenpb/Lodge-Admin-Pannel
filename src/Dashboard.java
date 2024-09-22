import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Dashboard extends JFrame implements ActionListener {
    public Dashboard() {
        setLayout(null);
        setBounds(0, 0, 1550, 1000);

        // adding image for the page
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 1000);
        add(image);

        JLabel text = new JLabel("NEW LODGE");
        text.setBounds(600, 80, 1000, 50);
        image.add(text);
        text.setFont(new Font("Tahoma", Font.BOLD, 46)); // Corrected the font name
        text.setForeground(Color.white);

        JMenuBar ab = new JMenuBar();
        ab.setBounds(0, 0, 1550, 30);
        image.add(ab);

        JMenu hotel = new JMenu("Lodge Management");
        hotel.setForeground(Color.red);
        ab.add(hotel);

        JMenuItem reception = new JMenuItem("Reception");
        hotel.add(reception);
        reception.addActionListener(this);

        JMenu admin = new JMenu("Admin");
        admin.setForeground(Color.blue);
        ab.add(admin);

        JMenuItem addemployee = new JMenuItem("Add Employee");
        admin.add(addemployee);
        addemployee.addActionListener(this);

        JMenuItem addrooms = new JMenuItem("Add Rooms");
        admin.add(addrooms);
        addrooms.addActionListener(this);

        

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command.equals("Add Employee")) {
            new AddEmployee();
        } else if (command.equals("Add Rooms")) {
            new AddRooms();
        }
        else if (command.equals("Reception")) {
            new Reception();
        } 
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
