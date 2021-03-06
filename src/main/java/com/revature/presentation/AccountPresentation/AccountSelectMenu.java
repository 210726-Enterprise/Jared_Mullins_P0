package com.revature.presentation.AccountPresentation;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.AccountService;

import java.util.Scanner;

public class AccountSelectMenu {

    /**
     * Account Service object for service layer logic
     */
    private static AccountService service = new AccountService();


    /**
     * Shows all accounts associated with user (primary and joint)
     * User can access accounts from this menu
     * @param user currently logged in user
     */
    public static void displayAllAccountsMenu(User user) {
        Scanner sc = new Scanner(System.in);
        RevArrayList<Account> accounts = service.getAccountByUserId(user.getUserId());

        if(accounts.size() == 0) {

            System.out.println("\n*****");
            System.out.println("You do not have any accounts.");
            System.out.println("Returning to User Menu...");
            System.out.println("*****");
            return;
        }
        System.out.println("\nUSER BANK ACCOUNTS MENU");
        System.out.println("=======================");
        System.out.println("Which account would you like to view?");
        for(int i = 1; i < accounts.size() + 1; i++) {
            System.out.println(i + ") " +
                    accounts.get(i - 1).getType() + " " +
                    accounts.get(i - 1).getAccountNumber() + " " +
                    (accounts.get(i - 1).getAccountName() != null ? "(" + accounts.get(i - 1).getAccountName() + ")" : ""));
        }
        System.out.println("0) Return to user menu...");

        if(sc.hasNextInt()) {
            int choice = sc.nextInt();

            if(choice == 0) {
                System.out.println("Returning to User Menu...");
                return;
            }

            for(int i = 1; i < accounts.size() + 1; i++) {
                if(choice == i) {
                    AccountMenu.displayAccountMenu(user, accounts.get(i - 1));
                }
            }
        } else {
            System.out.println("\n*****");
            System.out.println("Invalid Input");
            System.out.println("*****");
        }
        displayAllAccountsMenu(user);
    }

}
