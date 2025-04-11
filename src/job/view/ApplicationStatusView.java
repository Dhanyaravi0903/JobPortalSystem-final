package job.view;

import job.controller.ApplicationController;
import java.util.Scanner;

public class ApplicationStatusView 
{
    private Scanner scanner;
    private ApplicationController applicationController;
    private String userEmail;

    public ApplicationStatusView(String userEmail)
    {
        this.scanner = new Scanner(System.in);
        this.applicationController = new ApplicationController();
        this.userEmail = userEmail;
    }

    public void display() {
        while (true) { 
            System.out.println("--------------------------------------------------------");
            System.out.println("                 *** Application Status ***");
            
           
            boolean hasApplications = applicationController.viewApplicationsByUser(userEmail);
            if (!hasApplications)
            {
                System.out.println("No applications found.");
            }
            System.out.println("--------------------------------------------------------");
            System.out.println("Press Enter to go back to the previous menu or type 'exit' to leave...");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit"))
            {
                System.out.println("Exiting Application Status View...");
                break;
            } 
            else if (input.isEmpty()) 
            {
                System.out.println("Going back to the previous menu...");
                break; 
            } 
            else 
            {
                System.out.println("Invalid input! Type 'exit' to leave or press Enter to go back.");
            }
        }
    }
}
