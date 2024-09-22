import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

    Connection c ;
    // to establish connection cerate a object on connection class 
    // next create statement to execute sql quries
    Statement s;
    Conn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c= DriverManager.getConnection("jdbc:mysql:///hotelmanagement", "root", "Naveen@123");//its a string url which always starts with "jdbc:mysql://locolhost:3306/" , which will be default and we can alsop leave it empty 
            // get connection form a database from using drivermanager class and connection string
            // create statement
            s = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
