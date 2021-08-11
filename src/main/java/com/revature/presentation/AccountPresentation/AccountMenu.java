package com.revature.presentation.AccountPresentation;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.service.AccountService;
import com.revature.service.UserService;

import java.util.Scanner;

public class AccountMenu {

    /**
     * Account Service object for service layer logic
     */
    private static AccountService service = new AccountService();

    /**
     * Displays the options available for an account. Only primary users have access to options 6 and 7.
     * @param user currently logged in user
     * @param account account currently being accessed by account holder
     */
    public static void displayAccountMenu(User user, Account account) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + account.getType().toUpperCase() + " ACCOUNT " + account.getAccountNumber() + " MENU");
        System.out.println("=========================");
        System.out.println("What would you like to do?");
        System.out.println("1) Check Balance");
        System.out.println("2) Make Deposit");
        System.out.println("3) Make Withdrawal");
        System.out.println("4) View Transactions");
        System.out.println("5) Transfer funds to another account");
        if(user.getUserId() == account.getAccountPrimary()) {
            System.out.println("6) Add Joint User");
            System.out.println("7) Close Account");
        }
        System.out.println("0) Return to all accounts");

        if(sc.hasNextInt()) {
            int choice = sc.nextInt();
            switch(choice) {
                case 0:
                    System.out.println("Returning to all accounts...");
                    return;
                case 1:
                    //TODO getting the account balance may not work correctly
                    System.out.println("\n*****");
                    System.out.printf("Current Balance: $%,.2f %n", service.getBalanceByAccountNumber(account.getAccountNumber()));
                    System.out.println("*****");
                    break;
                case 2:
                    System.out.println("\nHow much would you like to deposit?");
                    if(sc.hasNextDouble()) {
                        double depositAmount = sc.nextDouble();
                        boolean success = service.makeDeposit(account.getAccountNumber(), depositAmount);

                        if(success) {
                            System.out.println("\n*****");
                            System.out.printf("Successfully deposited $%,.2f %n", depositAmount);
                            System.out.printf("New balance: $%,.2f %n", service.getBalanceByAccountNumber(account.getAccountNumber()));
                            System.out.println("*****");
                        }

                    } else {
                        //TODO clean up error feedback
                        System.out.println("Invalid deposit amount");
                    }
                    break;

                case 3:
                    System.out.println("\nHow much would you like to withdraw");
                    if(sc.hasNextDouble()) {
                        double wAmount = sc.nextDouble();
                        boolean success = service.makeWithdrawal(account.getAccountNumber(), wAmount);

                        if(success) {
                            System.out.println("\n*****");
                            System.out.printf("Successfully withdrew $%,.2f %n", wAmount);
                            System.out.printf("New balance: $%,.2f %n", service.getBalanceByAccountNumber(account.getAccountNumber()));
                            System.out.println("*****");
                        }

                    } else {
                        //TODO clean up error feedback
                        System.out.println("Invalid withdrawal amount");
                    }
                    break;

                case 4:
                    displayTransactions(account.getAccountNumber());
                    break;
                case 5:
                    TransferFundsMenu.displayTransferFundsMenu(user, account.getAccountNumber());
                    break;
                case 6:
                    if(user.getUserId() == account.getAccountPrimary()) {
                        displayAddJointUserMenu(account);
                    } else {
                        System.out.println("Invalid account menu choice");
                    }
                    break;
                case 7:
                    if(user.getUserId() == account.getAccountPrimary()) {
                        boolean success = displayAccountDeletePath(account);
                        if(success) {
                            return;
                        }
                    } else {
                        System.out.println("Invalid account menu choice");
                    }
                    break;
                default:
                    System.out.println("Invalid account menu choice");
                    break;
            }
        } else {
            //TODO clean up feedback
            System.out.println("Invalid account menu choice");
        }

        displayAccountMenu(user, account);
    }


    /**
     * Displays the menu path for adding a joint user to a user's account. Only available to primary users
     * @param account account that is adding a joint user
     * @return true if joint user was added successfully; false otherwise
     */
    private static boolean displayAddJointUserMenu(Account account) {
        UserService userS = new UserService();
        String username = "";
        Scanner sc = new Scanner(System.in);
        System.out.printf("\nPlease enter the username of the user you wish to add to your account: ");
        if(sc.hasNextLine()) {
            username = sc.nextLine();
            boolean success = service.addJointUser(username, account.getAccountNumber());
            if (success) {
                System.out.println("\n*****");
                System.out.println("Successfully added " + username + " as a joint user!");
                System.out.println("*****");
            } else {
                System.out.println("\n*****");
                System.out.println("Could not locate user. Failed to add joint account holder.");
                System.out.println("*****");
            }
        } else {
            System.out.println("Invalid input");
        }
        return false;
    }

    /**
     * displays full transaction history for account
     * @param accountNumber account number of accounts whose transactions will be displayed
     */
    private static void displayTransactions(int accountNumber) {
        RevArrayList<Transaction> transactions = service.getTransactionsByAccountNumber(accountNumber);
        for(int i = 0; i < transactions.size(); i++) {
            System.out.printf(transactions.get(i).getTransactionDate() + "\t\t| "
                    + "$%,.2f\t\t| "
                    + transactions.get(i).getType() + "%n", transactions.get(i).getTransactionAmount());
        }
    }

    /**
     * displays delete path for account currently being accessed. Only accessible by the primary account holder.
     * @param account account currently being accessed
     * @return true if user is successfully deleted; false otherwise
     */
    private static boolean displayAccountDeletePath(Account account) {
        UserService userS = new UserService();
        Scanner sc = new Scanner(System.in);
        System.out.println("\n***WARNING: Closing your account will permanently delete all records associated with account #" + account.getAccountNumber());
        System.out.println("\nWould you like to proceed? y/n");

        if(sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch(choice){

                case"y":
                    System.out.println("\nEnter your username to withdraw remaining balance and permanently close account #" + account.getAccountNumber());
                    if(sc.hasNextLine()) {
                        String username = sc.nextLine();
                        User user = userS.getUserByUsername(username);
                        if(user != null && user.getUserId() == account.getAccountPrimary()) {
                            double remainingBalance = service.getBalanceByAccountNumber(account.getAccountNumber());
                            if(remainingBalance == 0.0) {
                                if (service.deleteAccount(account.getAccountNumber())) {
                                    System.out.println("\n*****");
                                    System.out.printf("Successfully closed account #" + account.getAccountNumber() + "%n");
                                    System.out.println("*****");
                                    return true;
                                }
                            } else if(service.makeWithdrawal(account.getAccountNumber(), service.getBalanceByAccountNumber(account.getAccountNumber())) &&
                                    service.deleteAccount(account.getAccountNumber())) {
                                System.out.println("\n*****");
                                System.out.printf("Successfully withdrew $%,.2f and closed account #" + account.getAccountNumber() + "%n", remainingBalance);
                                System.out.println("*****");
                                return true;
                            }
                        } else {
                            System.out.println("Incorrect username. Returning to Account Menu...");
                        }
                    } else {
                        System.out.println("Invalid input. Returning to Account Menu");
                    }
                    break;

                case "n":
                    System.out.println("Returning to Account Menu...");
                    break;
            }
        } return false;
    }

}
