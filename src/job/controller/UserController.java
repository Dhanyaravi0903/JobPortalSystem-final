package job.controller;

import job.model.User;
import job.model.Database;
import util.Validator;  // Import Validator class
import java.sql.*;

public class UserController
{
    private Connection connection;
    public UserController() {
        this.connection = Database.connect();
    }

   
    public boolean registerUser(User user) 
    {
        System.out.println("Registering user with ID proof: " + user.getIdProof()); 
        if (!Validator.validateEmail(user.getEmail())) 
        {
            System.out.println("Invalid email format.");
            return false;
        }
        if (!Validator.validatePassword(user.getPassword()))
        {
            System.out.println("Password does not meet requirements.");
            return false;
        }
        if (user.getRole().equalsIgnoreCase("Recruiter") && !Validator.validateID(user.getIdProof())) 
        {
            System.out.println("Invalid ID Proof format.");
            return false;
        }
        String query = "INSERT INTO users (name, email, password, role, company, id_proof) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query))
        {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getCompany()); 
            stmt.setString(6, user.getIdProof()); 
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("User registration successful!");
                return true;
            } 
            else 
            {
                System.out.println("User registration failed.");
                return false;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

  
    public User authenticateUser(String email, String password) 
    {
        if (!Validator.validateEmail(email)) {
            System.out.println("Invalid email format.");
            return null;
        }
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) 
            {
                return new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("company"),
                        rs.getString("id_proof")
                );
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean emailExists(String email) 
    {
        String query = "SELECT 1 FROM users WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); 
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
