package com.revature.presentation.UserPresentation;

import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

import java.util.Scanner;

public class RegisterNewUserMenu {

    /**
     * User Service object for service layer logic
     */
    private static UserService service = new UserServiceImpl();

    /**
     * Walks through registration process for registering a new user
     * @return true if successfully registers a user; false otherwise
     */
    public static boolean displayRegistrationMenu() {
        String username = null;
        String password = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nREGISTERING NEW USER");

        System.out.print("Enter a username: ");
        if(scanner.hasNextLine()) {
            username = scanner.nextLine();
        } else {
            //TODO Give better error feedback when more fully built out
            System.out.println("Invalid input");
            return false;
        }

        System.out.print("Enter a password: ");
        if(scanner.hasNextLine()) {
            password = scanner.nextLine();
        } else {
            //TODO Give better error feedback when more fully built out
            System.out.println("Invalid input");
            return false;
        }

        return service.createUser(username, password);
    }

}
