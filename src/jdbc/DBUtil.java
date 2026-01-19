package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static final String URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Aasif@2004";
    public static Connection getConnection()
    {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (Exception e){
            System.out.println("❌ Database connection failed");
            return null;



        }


    }
}
