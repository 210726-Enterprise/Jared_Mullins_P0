package com.revature.presentation.UserPresentation;

import com.revature.model.User;
import com.revature.service.UserService;

import java.util.Scanner;

public class LoginMenu {

    /**
     * User Service object for service layer logic
     */
    private static UserService service = new UserService();

    /**
     * Walks through the login process
     * @return returns a User object if the account is verified; false otherwise
     */
    public static User displayLoginMenu() {
        User user = null;
        String username = null;
        String password = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("\nPLEASE ENTER YOUR CREDENTIALS OR ENTER '0' TO RETURN TO MAIN MENU");

        System.out.print("\nEnter your username: ");

        if(sc.hasNextInt()) {
            if(sc.nextInt() == 0) return null;
            //TODO clean up feedback
            System.out.println("Invalid input");
        }

        if(sc.hasNextLine()) {
            username = sc.nextLine();
        } else {
            //TODO cleanup error feedback
            System.out.println("Invalid input");
        }

        System.out.print("Enter your password: ");
        if(sc.hasNextLine()) {
            password = sc.nextLine();
        } else {
            //TODO cleanup error feedback
            System.out.println("Invalid input");
        }
        return service.verifyUser(username, password);
    }
}
