package job.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database 
{
    private static final String URL = "jdbc:mysql://localhost:3306/job_portal";
    private static final String USER = "root";
    private static final String PASSWORD = "Dhanya@2005";
    
    public static Connection connect() 
    {
        try 
        {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch (SQLException e)
        {
            System.err.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
