package com.revature.presentation.UserPresentation;

import com.revature.model.User;
import com.revature.service.UserService;

import java.util.Scanner;

public class MainMenu {

    /**
     * User Service object for service layer logic
     */
    private static UserService service = new UserService();

    /**
     * LAUNCHES APPLICATION
     * Walks through initial options (Log in, register, or close app)
     */
    public static void displayMainMenu() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nMAIN MENU");
        System.out.println("==========");
        System.out.println("What would you like to do?");
        System.out.println("1) Login");
        System.out.println("2) Register User Account");
        System.out.println("0) Close App");

        if(scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Goodbye");
                    return;
                case 1:
                    User user = LoginMenu.displayLoginMenu();
                    if(user != null) {
                        System.out.println("\nWelcome " + user.getUsername() + "!");
                        UserMenu.displayUserMenu(user);
                    } else {
                        System.out.println("Returning to Main Menu...");
                    }
                    break;
                case 2:
                    RegisterNewUserMenu.displayRegistrationMenu();
                    break;
                default:
                    System.out.println("\n*****");
                    System.out.println("Invalid input. Please select a valid menu option");
                    System.out.println("*****");
                    break;
            }
        } else {
            System.out.println("\n*****");
            System.out.println("Invalid input. Please select a valid menu option");
            System.out.println("*****");
        }
        displayMainMenu();
    }
}
