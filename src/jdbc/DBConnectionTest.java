package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnectionTest {

    public static void main (String[] args){
        String url = "jdbc:mysql://localhost:3306/employee_management";
        String user = "root";
        String password = "Aasif@2004";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connected successfully!");
            con.close();
        }
        catch (Exception e) {
            System.out.println("❌ Connection failed");
            e.printStackTrace();
        }
    }
}

