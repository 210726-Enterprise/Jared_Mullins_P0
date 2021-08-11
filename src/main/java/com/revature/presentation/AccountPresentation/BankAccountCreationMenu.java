package com.revature.presentation.AccountPresentation;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.AccountServiceImpl;

import java.util.Scanner;

public class BankAccountCreationMenu {


    /**
     * Account Service object for service layer logic
     */
    private static AccountServiceImpl service = new AccountServiceImpl();


    /**
     * account creation menu.
     * Walks through options of what type of account user wants to create, and if they want to give the account a friendly name
     * @param user currently logged in user
     */
    public static void displayAccountCreationMenu(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nCREATING NEW ACCOUNT FOR " + user.getUsername());
        System.out.println("=============================");
        System.out.println("What type of account would you like to create?");
        System.out.println("1) Checking");
        System.out.println("2) Savings");
        System.out.println("0) Return to User Menu");

        if(sc.hasNextInt()) {
            String accountType;
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    accountType = "Checking";
                    String checkingAccountName = nameAccount();
                    boolean success = service.createAccount(user, accountType, checkingAccountName);
                    if(success) {
                        RevArrayList<Account> accounts = service.getAccountByUserId(user.getUserId());
                        Account newAccount = accounts.get(accounts.size() - 1);
                        System.out.println("\nSuccessfully created new account");
                        System.out.println("================================");
                        System.out.println("Account Number: " + newAccount.getAccountNumber());
                        System.out.println("Account Type: " + newAccount.getType());
                        System.out.printf("Account Balance: $%,.2f %n", newAccount.getBalance());
                        if(newAccount.getAccountName() != null) {
                            System.out.println("Account Name: " + newAccount.getAccountName());
                        }
                        System.out.println();
                        return;
                    } else {
                        System.out.println("Could not create account");
                    }
                    break;
                case 2:
                    accountType = "Savings";
                    String savingsAccountName = nameAccount();
                    boolean successful = service.createAccount(user, accountType, savingsAccountName);
                    if(successful) {
                        RevArrayList<Account> accounts = service.getAccountByUserId(user.getUserId());
                        Account newAccount = accounts.get(accounts.size() - 1);
                        System.out.println("\nSuccessfully created new account");
                        System.out.println("================================");
                        System.out.println("Account Number: " + newAccount.getAccountNumber());
                        System.out.println("Account Type: " + newAccount.getType());
                        System.out.printf("Account Balance: $%,.2f %n", newAccount.getBalance());
                        if(newAccount.getAccountName() != null) {
                            System.out.println("Account Name: " + newAccount.getAccountName());
                        }
                        return;
                    } else {
                        System.out.println("Could not create account");
                    }
                    break;
                case 3:
                    //TODO fix feedback
                    System.out.println("Invalid Input. Please enter 0, 1, or 2");
                    break;
            }
        } else {
            //TODO fix feedback
            System.out.println("Invalid Input");
        }

        displayAccountCreationMenu(user);
    }


    /**
     * displays if user chooses to give account a friendly name
     * @return name the user gives the account
     */
    private static String nameAccount() {
        String accountName = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWould you like to give your new account a custom name? y/n");

        if(sc.hasNextLine()) {
            String choice = sc.nextLine();

            switch (choice) {
                case "y":
                    System.out.print("Please name your account: ");
                    if(sc.hasNextLine()) {
                        accountName = sc.nextLine();
                        return accountName;
                    } else {
                        System.out.println("Invalid input. Returning to Account Creation Menu...");
                    }
                    break;
                case "n":
                    return accountName;
                default:
                    System.out.println("Invalid input. Please enter \"y\" or \"n\"");
                    accountName = nameAccount();
            }
        } else {
            System.out.println("Invalid input. Returning to Account Creation Menu...");
        }

        return accountName;
    }

}
