package job.view;

import job.controller.JobController;
import job.controller.ApplicationController;
import job.model.Job;
import job.model.User;// Make sure to import the User class
import job.model.Application;
import java.util.List;
import java.util.Scanner;

public class RecruiterDashboard {
    private final JobController jobController;
    private final ApplicationController applicationController;
    private final Scanner scanner;
    private final String recruiterEmail;

    
    public RecruiterDashboard(User user) {
        this.jobController = new JobController();
        this.applicationController = new ApplicationController();
        this.scanner = new Scanner(System.in);
        this.recruiterEmail = user.getEmail(); 
    }

    public void displayDashboard() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nWelcome to the Recruiter Dashboard!");
            System.out.println("1. Post a Job");
            System.out.println("2. View Posted Jobs");
            System.out.println("3. View Applications");
            System.out.println("4. Accept/Reject Applications");
            System.out.println("5. Manage/Edit Jobs");
            System.out.println("6. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    postJob();
                    break;
                case 2:
                    viewPostedJobs(recruiterEmail);
                    break;
                case 3:
                    viewApplications();
                    break;
                case 4:
                    acceptRejectApplications();
                    break;
                case 5:
                    updateJob();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    isRunning = false; 
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void postJob() {
        System.out.print("Enter job title: ");
        String title = scanner.nextLine();
        System.out.print("Enter job description: ");
        String description = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter company name: ");
        String company = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); 

        Job job = new Job(0, title, description, location, company, salary, recruiterEmail);
        if (jobController.addJob(job)) {
            System.out.println("Job posted successfully!");
        } else {
            System.out.println("Failed to post job.");
        }
    }


    private void viewPostedJobs(String recruiterEmail) {
        List<Job> jobs = jobController.getJobsByRecruiter(recruiterEmail);
        if (jobs.isEmpty()) {
            System.out.println("No jobs found.");
        } else {
            System.out.println("Your Posted Jobs:");
            for (Job job : jobs) {
                System.out.println(job);
            }
        }
    }



    private void viewApplications() {
        System.out.print("Enter Job ID to view applications: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); 
        
        
        List<Application> applications = applicationController.getApplicationsByJob(jobId);
        
       
        if (applications.isEmpty()) {
            System.out.println("No applications found for this job.");
        } else {
            System.out.println("Applications for Job ID " + jobId + ":");
            for (Application application : applications) {
                System.out.println(application); 
            }
        }
    }


    private void acceptRejectApplications() {
        System.out.print("Enter Job ID to accept/reject applications: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); 

       
        List<Application> applications = applicationController.getApplicationsByJob(jobId);

       
        if (applications.isEmpty()) {
            System.out.println("No applications found for Job ID " + jobId);
            return;
        }

       
        for (Application application : applications) {
            System.out.println("Application ID: " + application.getId());
            System.out.println("Job Seeker: " + application.getJobSeekerEmail());
            System.out.print("Do you want to accept or reject this application? (accept/reject): ");
            String decision = scanner.nextLine();

            
            if ("accept".equalsIgnoreCase(decision)) {
                if (applicationController.updateApplicationStatus(application.getId(), "Accepted")) {
                    System.out.println("Application accepted: " + application.getJobSeekerEmail());
                } else {
                    System.out.println("Failed to accept application: " + application.getJobSeekerEmail());
                }
            } else if ("reject".equalsIgnoreCase(decision)) {
                if (applicationController.updateApplicationStatus(application.getId(), "Rejected")) {
                    System.out.println("Application rejected: " + application.getJobSeekerEmail());
                } else {
                    System.out.println("Failed to reject application: " + application.getJobSeekerEmail());
                }
            } else {
                System.out.println("Invalid decision, skipping this application.");
            }
        }
    }


    private void updateJob() {
        System.out.print("Enter Job ID to update: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter new job title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new job description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new location: ");
        String location = scanner.nextLine();
        System.out.print("Enter new company name: ");
        String company = scanner.nextLine();
        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); 

        Job job = new Job(jobId, title, description, location, company, salary, recruiterEmail);
        if (jobController.updateJob(job)) {
            System.out.println("Job updated successfully!");
        } else {
            System.out.println("Failed to update job.");
        }
    }
}
