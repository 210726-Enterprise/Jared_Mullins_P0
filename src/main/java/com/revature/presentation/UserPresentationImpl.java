package com.revature.presentation;

import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

import java.util.Scanner;

public class UserPresentationImpl implements UserPresentation{

    private UserService service;

    public UserPresentationImpl() {
        service = new UserServiceImpl();
    }

    public void displayMainMenu() {

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
                    User user = login();
                    if(user != null) {
                        System.out.println("\nWelcome " + user.getUsername() + "!");
                        displayUserMenu(user);
                    } else {
                        System.out.println("Returning to Main Menu");
                    }
                    break;
                case 2:
                    registerNewUser();
                    break;
                default:
                    //TODO Better feedback when app built out
                    System.out.println("ERROR FEEDBACK");
                    break;
            }
        } else {
            //TODO Better feedback when app built out
            System.out.println("ERROR FEEDBACK");
        }
        displayMainMenu();
    }

    public void displayUserMenu(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nUSER MENU");
        System.out.println("==========");
        System.out.println("What would you like to do?");
        System.out.println("1) View Accounts");
        System.out.println("2) Open a new Bank Account");
        System.out.println("3) Delete User Account");
        System.out.println("0) Logout");

        if(sc.hasNextInt()) {
            int choice = sc.nextInt();
            switch(choice){
                case 0:
                    return;
                case 1:
                    displayUserBankAccounts(user);
                    break;
                case 2:
                    displayCreateAccountMenu(user);
                    break;
                case 3:
                    boolean success = loadDeletePath(user);
                    if(success) {
                        System.out.println(user.getUsername() + "'s profile has been deleted and all accounts closed");
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } else {
            //TODO fix error feedback with default above as well
            System.out.println("Invalid input");
        }
        displayUserMenu(user);
    }

    public void displayCreateAccountMenu(User user) {
        AccountPresentation accountP = new AccountPresentationImpl();
        accountP.displayAccountCreationMenu(user);
    }

    public void displayUserBankAccounts(User user) {
        AccountPresentation accountP = new AccountPresentationImpl();
        accountP.displayAllAccountsMenu(user);
    }

    //TODO Create better exit options
    //TODO Move to User Service
    public User login() {
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

    //TODO Move to User Service
    //TODO NEEDS TO VERIFY USERNAME IS UNIQUE. Currently getting exception
    public boolean registerNewUser() {
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

    public boolean loadDeletePath(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n***WARNING: Deleting your User Profile will permanently delete all your accounts and transaction history.");
        System.out.println("\nDo you still wish to proceed? y/n");

        if(sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch (choice) {

                case "y":
                    System.out.print("Enter your username to permanently delete your profile: ");
                    if(sc.hasNextLine()) {
                        String username = sc.nextLine();
                        if(username.equals(user.getUsername())) {
                            service.deleteUser(user);
                            System.out.println("Successfully deleted User");
                            return true;
                        } else {
                            //TODO clean up feedback
                            System.out.println("Could not delete user");
                        }
                    } else {
                        //TODO clean up feedback
                        System.out.println("Invalid input");
                    }
                    break;

                case "n":
                    System.out.println("Returning to User Menu...");
                    return false;

                default:
                    //TODO clean up feedback
                    System.out.println("Invalid input");
                    break;
            }
        } else {
            //TODO clean up feedback
            System.out.println("Invalid input");
        }
        return false;
    }

    @Override
    public void displayUser(String username) {

    }
}
