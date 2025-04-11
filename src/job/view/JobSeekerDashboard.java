package job.view;

import job.controller.JobController;
import job.controller.ApplicationController;
import job.model.Job;
import job.model.User;
import job.model.Application;
import java.util.List;
import java.util.Scanner;

public class JobSeekerDashboard {
    private final JobController jobController;
    private final ApplicationController applicationController;
    private final Scanner scanner;
    private final String jobSeekerEmail;

   
    public JobSeekerDashboard(User user) {
        this.jobController = new JobController();
        this.applicationController = new ApplicationController();
        this.scanner = new Scanner(System.in);
        this.jobSeekerEmail = user.getEmail(); 
    }

    public void displayDashboard() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nWelcome to the Job Seeker Dashboard!");
            System.out.println("1. Search Jobs");
            System.out.println("2. Apply for a Job");
            System.out.println("3. View My Applications");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchJobs();
                    break;
                case 2:
                    applyForJob();
                    break;
                case 3:
                    viewMyApplications();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void searchJobs() {
        List<Job> jobs = jobController.getAllJobs();
        if (jobs.isEmpty()) {
            System.out.println("No jobs found.");
        } else {
            System.out.println("Available Jobs:");
            for (Job job : jobs) {
                System.out.println(job);
            }
        }
    }

    public void applyForJob() {
        System.out.print("Enter Job ID to apply: ");
        int jobId = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter your resume: ");
        String resume = scanner.nextLine();

       
        if (applicationController.applyForJob(jobSeekerEmail, jobId, resume)) {
            System.out.println("Application submitted successfully.");
        } else {
            System.out.println("Failed to apply for the job.");
        }
    }


    public void viewMyApplications() {
        List<Application> myApplications = applicationController.getApplicationsByJobSeeker(jobSeekerEmail);
        
        if (myApplications.isEmpty()) 
        {
            System.out.println("You have not applied for any jobs yet.");
        }
        else
        {
            System.out.println("Your Applications:");
            
            for (Application application : myApplications) 
            {
                Job job = jobController.getJobById(application.getJobId());
                System.out.println("Job Title: " + job.getTitle() + "\nStatus: " + application.getStatus());
            }
        }
    }
}
