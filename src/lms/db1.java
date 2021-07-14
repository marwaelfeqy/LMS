
package lms;
import java.sql.*;

public class db1 {
    db1()
    {
        try
        {
            String host = "jdbc:derby://localhost:1527/sample";
            Connection con = DriverManager.getConnection(host);
        
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next(); 
            int id= rs.getInt("ID");
            String type = rs.getString("TYPE");
            String email = rs.getString("email");
            String password = rs.getString("password");
            System.out.print(id + type +email+password);
            
        }
        catch(Exception e)
        {
           System.out.print(e.getMessage());

        }

    }
}
