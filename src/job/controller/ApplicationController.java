package job.controller;

import job.model.Application;
import job.model.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationController
{
    private Connection connection;

    public ApplicationController() 
    {
        this.connection = Database.connect();
    }

  
    public boolean addApplication(Application application) 
    {
        String query = "INSERT INTO applications (job_id, job_seeker_email, application_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) 
        {
            stmt.setInt(1, application.getJobId());
            stmt.setString(2, application.getJobSeekerEmail());
            stmt.setDate(3, new java.sql.Date(application.getApplicationDate().getTime()));
            stmt.setString(4, application.getStatus());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) 
            {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) 
                {
                    int applicationId = generatedKeys.getInt(1);
                    System.out.println("Application submitted successfully! Application ID: " + applicationId);
                }
                return true;
            }
        } 
        catch (SQLException e)
        {
            System.err.println("Error submitting application: " + e.getMessage());
        }
        return false;
    }



    public boolean applyForJob(String jobSeekerEmail, int jobId, String resume) 
    {
        Application application = new Application(0, jobId, jobSeekerEmail, new java.sql.Date(System.currentTimeMillis()), "Pending");
        return addApplication(application);  
    }

   
    public List<Application> getApplicationsByJobSeeker(String jobSeekerEmail)
    {
        List<Application> applications = new ArrayList<>();
        String query = "SELECT * FROM applications WHERE job_seeker_email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setString(1, jobSeekerEmail);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                applications.add(new Application(
                    rs.getInt("id"),
                    rs.getInt("job_id"),
                    rs.getString("job_seeker_email"),
                    rs.getDate("application_date"),
                    rs.getString("status")
                ));
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving applications: " + e.getMessage());
        }
        return applications;
    }

  
    public List<Application> getApplicationsByJob(int jobId) 
    {
        List<Application> applications = new ArrayList<>();
        String query = "SELECT * FROM applications WHERE job_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setInt(1, jobId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                applications.add(new Application(
                    rs.getInt("id"),
                    rs.getInt("job_id"),
                    rs.getString("job_seeker_email"),
                    rs.getDate("application_date"),
                    rs.getString("status")
                ));
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving applications for job: " + e.getMessage());
        }
        return applications;
    }

   
    public boolean updateApplicationStatus(int applicationId, String status) {
        String query = "UPDATE applications SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setString(1, status);
            stmt.setInt(2, applicationId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } 
        catch (SQLException e)
        {
            System.err.println("Error updating application status: " + e.getMessage());
            return false;
        }
    }

   
    public boolean deleteApplication(int applicationId)
    {
        String query = "DELETE FROM applications WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setInt(1, applicationId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } 
        catch (SQLException e) 
        {
            System.err.println("Error deleting application: " + e.getMessage());
            return false;
        }
    }
}
