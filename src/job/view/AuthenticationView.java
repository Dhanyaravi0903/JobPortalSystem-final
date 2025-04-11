package job.view;

import job.controller.UserController;
import job.model.User;
import java.util.Scanner;

public class AuthenticationView {
    private Scanner scanner;
    private UserController userController;

    public AuthenticationView() {
        this.scanner = new Scanner(System.in);
        this.userController = new UserController();
    }

   
    public void signIn() {
        System.out.println("Welcome to the Job Portal Sign In!");

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

       
        User user = userController.authenticateUser(email, password);
        
        if (user != null) {
            System.out.println("Welcome, " + user.getName() + "!");
            navigateToDashboard(user); 
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

   
    public void navigateToDashboard(User user)
    {
        if ("Recruiter".equalsIgnoreCase(user.getRole())) 
        {
            RecruiterDashboard recruiterDashboard = new RecruiterDashboard(user); 
            recruiterDashboard.displayDashboard();
        } 
        else if ("JobSeeker".equalsIgnoreCase(user.getRole()))
        {
            JobSeekerDashboard jobSeekerDashboard = new JobSeekerDashboard(user); 
            jobSeekerDashboard.displayDashboard();
        } 
        else 
        {
            System.out.println("Unknown role.");
        }
    }


    public void signUp() {
        System.out.println("Enter details to create an account:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (userController.emailExists(email)) 
        {
            System.out.println("This email is already registered. Please try logging in.");
            return;  
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (Recruiter/JobSeeker): ");
        String role = scanner.nextLine();
        User user = new User(name, email, password, role, "", ""); 
        if (role.equalsIgnoreCase("Recruiter"))
        {
            System.out.print("Enter company name: ");
            user.setCompany(scanner.nextLine());
            System.out.print("Enter ID proof: ");
            user.setIdProof(scanner.nextLine());
        }
        boolean isRegistered = userController.registerUser(user);
        if (isRegistered)
        {
            System.out.println("User registered successfully! You can now sign in.");
        } 
        else
        {
            System.out.println("Registration failed. Please try again.");
        }
    }
}
