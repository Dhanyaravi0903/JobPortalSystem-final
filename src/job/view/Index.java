package job.view;

import java.util.Scanner;

public class Index {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true)
        { 
            System.out.println("Welcome to Job Portal\n1. Sign In\n2. Sign Up\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) 
            {
                case 1:
                    new AuthenticationView().signIn();
                    break; 
                case 2:
                    new AuthenticationView().signUp();  
                    break;  
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        }
    }
}
