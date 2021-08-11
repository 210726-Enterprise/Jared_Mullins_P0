package com.revature.presentation.UserPresentation;

import com.revature.model.User;
import com.revature.presentation.AccountPresentation.AccountSelectMenu;
import com.revature.presentation.AccountPresentation.BankAccountCreationMenu;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

import java.util.Scanner;

public class UserMenu {

    private static UserService service = new UserServiceImpl();

    public static void displayUserMenu(User user) {
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
                    System.out.println("Invalid input");
                    break;
            }
        } else {
            //TODO fix error feedback with default above as well
            System.out.println("Invalid input");
        }
        displayUserMenu(user);
    }

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
}
