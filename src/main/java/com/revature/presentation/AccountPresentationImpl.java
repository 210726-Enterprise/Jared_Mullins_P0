package com.revature.presentation;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.service.AccountService;
import com.revature.service.AccountServiceImpl;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

import java.util.Scanner;

public class AccountPresentationImpl implements AccountPresentation{

    private AccountService service;

    public AccountPresentationImpl() {
        service = new AccountServiceImpl();
    }

    public void displayAccountCreationMenu(User user) {
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
                    boolean success = service.createAccount(user, accountType);
                    if(success) {
                       RevArrayList<Account> accounts = service.getAccountByUserId(user.getUserId());
                       Account newAccount = accounts.get(accounts.size() - 1);
                        System.out.println("\nSuccessfully created new account");
                        System.out.println("================================");
                        System.out.println("Account Number: " + newAccount.getAccountNumber());
                        System.out.println("Account Type: " + newAccount.getType());
                        System.out.printf("Account Balance: $%,.2f %n", newAccount.getBalance());
                        return;
                    } else {
                        System.out.println("Could not create account");
                    }
                    break;
                case 2:
                    accountType = "Savings";
                    boolean successful = service.createAccount(user, accountType);
                    if(successful) {
                        RevArrayList<Account> accounts = service.getAccountByUserId(user.getUserId());
                        Account newAccount = accounts.get(accounts.size() - 1);
                        System.out.println("\nSuccessfully created new account");
                        System.out.println("================================");
                        System.out.println("Account Number: " + newAccount.getAccountNumber());
                        System.out.println("Account Type: " + newAccount.getType());
                        System.out.printf("Account Balance: $%,.2f %n", newAccount.getBalance());
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

    @Override
    public void displayAllAccountsMenu(User user) {
        Scanner sc = new Scanner(System.in);
        RevArrayList<Account> accounts = service.getAccountByUserId(user.getUserId());

        if(accounts.size() == 0) {
            System.out.println("You do not have any accounts.");
            System.out.println("Returning to User Menu...");
            return;
        }
        System.out.println("\nUSER BANK ACCOUNTS MENU");
        System.out.println("=======================");
        System.out.println("Which account would you like to view?");
        for(int i = 1; i < accounts.size() + 1; i++) {
            System.out.println(i + ") " + accounts.get(i - 1).getType() + " " + accounts.get(i - 1).getAccountNumber());
        }
        System.out.println("0) Return to user menu");

        if(sc.hasNextInt()) {
            int choice = sc.nextInt();

            if(choice == 0) {
                System.out.println("Returning to User Menu");
                return;
            }

            for(int i = 1; i < accounts.size() + 1; i++) {
                if(choice == i) {
                    displayAccountMenu(user, accounts.get(i - 1));
                }
            }
        } else {
            //TODO Clean up feedback
            System.out.println("Invalid Input");
        }
        displayAllAccountsMenu(user);
    }

    public void displayAccountMenu(User user, Account account) {
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
                    displayTransferFundsMenu(user, account.getAccountNumber());
                    break;
                case 6:
                    if(user.getUserId() == account.getAccountPrimary()) {
                        boolean success = displayAddJointUserMenu(account);
                        if (success) {
                            System.out.println("Successfully added joint user!");
                        }
                    }
                    break;
                case 7:
                    if(user.getUserId() == account.getAccountPrimary()) {
                        displayAccountDeletePath(account);
                    } else {
                        System.out.println("Invalid account menu choice");
                    }
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } else {
            //TODO clean up feedback
            System.out.println("Invalid account menu choice");
        }

        displayAccountMenu(user, account);
    }

    private boolean displayTransferFundsMenu(User user, int accountNumber) {
        Scanner sc = new Scanner(System.in);
        RevArrayList<Account> accounts = service.getAccountByUserId(user.getUserId());
        System.out.println(accounts);
        System.out.println("FUNDS TRANSFERAL MENU");
        System.out.println("====================");
        System.out.println("Where would you like to transfer the money?");
        for (int i = 1; i < accounts.size() + 1; i++) {
            System.out.println(i + ") " + accounts.get(i - 1).getType() + " " + accounts.get(i - 1).getAccountNumber());
        }
        System.out.println(accounts.size() + 1 + ") Transfer to external account");

        if (sc.hasNextInt()) {
            int choice = sc.nextInt();

            for (int i = 1; i <= accounts.size() + 1; i++) {
                if (choice == i) {
                    return displayInternalAccountTransferMenu(accountNumber, accounts.get(i - 1).getAccountNumber());
                } else if (choice == accounts.size() + 1) {
                    return displayExternalAccountTransferMenu(accountNumber);
                }
            }
        }
        return false;
    }

    public boolean displayAccountDeletePath(Account account) {
        UserServiceImpl userS = new UserServiceImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("***Warning closing your account will permanently delete all records associated with account #" + account.getAccountNumber());
        System.out.println("\nWould you like to proceed? y/n");

        if(sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch(choice){

                case"y":
                    System.out.println("Enter your username to withdraw remaining balance and permanently close account #" + account.getAccountNumber());
                    if(sc.hasNextLine()) {
                        String username = sc.nextLine();
                        User user = userS.getUserByUsername(username);
                        if(user != null && user.getUserId() == account.getAccountPrimary()) {
                            if(service.deleteAccount(account.getAccountNumber())) {
                                System.out.println("Successfully deleted account #" + account.getAccountNumber());
                                return true;
                            }
                        } else {
                            System.out.println("Incorrect username. Returning to Account Menu...");
                        }
                    } else {
                        System.out.println("Invalid input");
                    }
                    break;

                case "n":
                    System.out.println("Returning to Account Menu...");
                    break;
            }
        } return false;
    }

    public void displayTransactions(int accountNumber) {
        RevArrayList<Transaction> transactions = service.getTransactionsByAccountNumber(accountNumber);
        for(int i = 0; i < transactions.size(); i++) {
            System.out.printf(transactions.get(i).getTransactionDate() + "\t\t| "
                    + "$%,.2f\t\t| "
                    + transactions.get(i).getType() + "%n", transactions.get(i).getTransactionAmount());
        }
    }

    public boolean displayAddJointUserMenu(Account account) {
        UserService userS = new UserServiceImpl();
        String username = "";
        Scanner sc = new Scanner(System.in);
        System.out.printf("\nPlease enter the username of the user you wish to add to your account:");
        if(sc.hasNextLine()) {
            username = sc.nextLine();
            return service.addJointUser(username, account.getAccountNumber());
        } else {
            System.out.println("Invalid input");
        }
        return false;
    }

    public boolean displayInternalAccountTransferMenu(int transferFromAccountNumber, int transferToAccountNumber) {
        double transferAmount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nHow much would you like to transfer?: ");

        if(sc.hasNextDouble()) {
            transferAmount = sc.nextDouble();
        }
        return service.transferFunds(transferAmount, transferFromAccountNumber, transferToAccountNumber);
    }

    private boolean displayExternalAccountTransferMenu(int transferFromAccountNumber) {
        int transferToAccountNumber = 0;
        double transferAmount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the account number you wish to transfer funds to: ");

        if(sc.hasNextInt()) {
            //TODO Validate that account number exists in db
            transferToAccountNumber = sc.nextInt();
        } else {
            System.out.println("Invalid input");
        }

        System.out.println("How much would you like to transfer?");
        if(sc.hasNextDouble()) {
            transferAmount = sc.nextDouble();
        }

        return service.transferFunds(transferAmount, transferFromAccountNumber, transferToAccountNumber);

    }

}
