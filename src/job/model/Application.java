package job.model;
import java.sql.Date;

public class Application {
    private int id;
    private int jobId;
    private String jobSeekerEmail;
    private Date applicationDate;
    private String status;

  
    public Application(int id, int jobId, String jobSeekerEmail, Date applicationDate, String status) {
        this.id = id;
        this.jobId = jobId;
        this.jobSeekerEmail = jobSeekerEmail;
        this.applicationDate = applicationDate;
        this.status = status;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobSeekerEmail() {
        return jobSeekerEmail;
    }

    public void setJobSeekerEmail(String jobSeekerEmail) {
        this.jobSeekerEmail = jobSeekerEmail;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  
    @Override
    public String toString() {
        return "Application ID: " + id + "\n" +
               "Job ID: " + jobId + "\n" +
               "Job Seeker Email: " + jobSeekerEmail + "\n" +
               "Application Date: " + applicationDate + "\n" +
               "Status: " + status;
    }


    public static void main(String[] args) 
    {
        Date currentDate = new Date(System.currentTimeMillis());
        Application application = new Application(1, 101, "jobseeker@example.com", currentDate, "Pending");
        System.out.println(application.toString());
    }
}
