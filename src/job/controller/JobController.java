package job.controller;

import job.model.Database;
import job.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobController 
{
    private Connection connection;

    public JobController() {
        this.connection = Database.connect(); 
    }

    
    public boolean addJob(Job job) 
    {
        String query = "INSERT INTO jobs (title, description, location, company, salary, recruiter_email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) 
        {
            stmt.setString(1, job.getTitle());
            stmt.setString(2, job.getDescription());
            stmt.setString(3, job.getLocation());
            stmt.setString(4, job.getCompany());
            stmt.setDouble(5, job.getSalary());
            stmt.setString(6, job.getRecruiterEmail());
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0)
            {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) 
                {
                    int jobId = generatedKeys.getInt(1);
                    System.out.println("Job posted successfully! Job ID: " + jobId);
                }
                return true;
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error posting job: " + e.getMessage());
        }
        return false;
    }

   
    public List<Job> getAllJobs() 
    {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                jobs.add(new Job(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("company"),
                    rs.getDouble("salary"),
                    rs.getString("recruiter_email")
                ));
            }
        }
        catch (SQLException e) 
        {
            System.err.println("Error retrieving jobs: " + e.getMessage());
        }
        return jobs;
    }

   
    public boolean updateJob(Job job) 
    {
        String query = "UPDATE jobs SET title = ?, description = ?, location = ?, company = ?, salary = ? WHERE id = ? AND recruiter_email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setString(1, job.getTitle());
            stmt.setString(2, job.getDescription());
            stmt.setString(3, job.getLocation());
            stmt.setString(4, job.getCompany());
            stmt.setDouble(5, job.getSalary());
            stmt.setInt(6, job.getId());
            stmt.setString(7, job.getRecruiterEmail());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } 
        catch (SQLException e) 
        {
            System.err.println("Error updating job: " + e.getMessage());
            return false;
        }
    }

    public Job getJobById(int jobId) 
    {
        Job job = null;
        String query = "SELECT * FROM jobs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setInt(1, jobId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) 
            {
                job = new Job(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("company"),
                    rs.getDouble("salary"),
                    rs.getString("recruiter_email")
                );
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error retrieving job: " + e.getMessage());
        }
        return job; 
    }

   
    public boolean deleteJob(int jobId, String recruiterEmail) 
    {
        String query = "DELETE FROM jobs WHERE id = ? AND recruiter_email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setInt(1, jobId);
            stmt.setString(2, recruiterEmail);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e) 
        {
            System.err.println("Error deleting job: " + e.getMessage());
            return false;
        }
    }
    
    public List<Job> getJobsByRecruiter(String recruiterEmail) 
    {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs WHERE recruiter_email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) 
        {
            stmt.setString(1, recruiterEmail);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                jobs.add(new Job(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("company"),
                    rs.getDouble("salary"),
                    rs.getString("recruiter_email")
                ));
            }
        } 
        catch (SQLException e) {
            System.err.println("Error retrieving jobs: " + e.getMessage());
        }
        return jobs;
    }

  
    public List<Job> searchJobs(String keyword)
    {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs WHERE title LIKE ? OR description LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query))
        {
            String searchKeyword = "%" + keyword + "%"; 
            stmt.setString(1, searchKeyword);
            stmt.setString(2, searchKeyword);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                jobs.add(new Job(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("company"),
                    rs.getDouble("salary"),
                    rs.getString("recruiter_email")
                ));
            }
        } 
        catch (SQLException e)
        {
            System.err.println("Error searching for jobs: " + e.getMessage());
        }
        return jobs;
    }
}
