package com.revature.presentation.UserPresentation;

import com.revature.model.User;
import com.revature.presentation.AccountPresentation.AccountSelectMenu;
import com.revature.presentation.AccountPresentation.BankAccountCreationMenu;
import com.revature.service.UserService;

import java.util.Scanner;

public class UserMenu {

    /**
     * User Service object for service layer logic
     */
    private static UserService service = new UserService();

    /**
     * Walks through options for a logged in user
     * @param user currently logged in and verified user
     */
    public static void displayUserMenu(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nUSER MENU");
        System.out.println("==========");
        System.out.println("What would you like to do?");
        System.out.println("1) View Accounts");
        System.out.println("2) Open a new Bank Account");
        System.out.println("3) Delete User Profile");
        System.out.println("0) Logout");

        if(sc.hasNextInt()) {
            int choice = sc.nextInt();
            switch(choice){
                case 0:
                    return;
                case 1:
                    AccountSelectMenu.displayAllAccountsMenu(user);
                    break;
                case 2:
                    BankAccountCreationMenu.displayAccountCreationMenu(user);
                    break;
                case 3:
                    boolean success = displayDeleteUserPath(user);
                    if(success) {
                        System.out.println("\n*****");
                        System.out.println("Successfully deleted User");
                        System.out.println(user.getUsername() + "'s profile has been deleted and all accounts closed");
                        System.out.println("*****");
                        return;
                    }
                    break;
                default:
                    System.out.println("\n*****");
                    System.out.println("Invalid input");
                    System.out.println("*****");
                    break;
            }
        } else {
            System.out.println("\n*****");
            System.out.println("Invalid input");
            System.out.println("*****");
        }
        displayUserMenu(user);
    }

    /**
     * Walks user through the deletion process with plenty of exits
     * @param user currently logged in user
     * @return true if user is deleted successfully; false otherwise
     */
    private static boolean displayDeleteUserPath(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n***WARNING: Deleting your User Profile will permanently delete all your accounts and transaction history.");
        System.out.println("\nDo you still wish to proceed? y/n");

        if(sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch (choice) {

                case "y":
                    System.out.print("\nEnter your username to permanently delete your profile: ");
                    if(sc.hasNextLine()) {
                        String username = sc.nextLine();
                        if(username.equals(user.getUsername())) {
                            service.deleteUser(user);
                            return true;
                        } else {
                            System.out.println("\n*****");
                            System.out.println("Could not delete user");
                            System.out.println("*****");
                        }
                    } else {
                        System.out.println("\n*****");
                        System.out.println("Invalid input");
                        System.out.println("*****");
                    }
                    break;

                case "n":
                    System.out.println("Returning to User Menu...");
                    return false;

                default:
                    System.out.println("\n*****");
                    System.out.println("Invalid input");
                    System.out.println("*****");
                    break;
            }
        } else {
            System.out.println("\n*****");
            System.out.println("Invalid input");
            System.out.println("*****");
        }
        return false;
    }
}
