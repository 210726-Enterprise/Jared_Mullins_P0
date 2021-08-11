package com.revature.presentation.AccountPresentation;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.AccountService;

import java.util.Scanner;

public class TransferFundsMenu {

    /**
     * Account Service object for service layer logic
     */
    private static AccountService service = new AccountService();


    /**
     * Menu for transferring funds. Can transfer to an account owned by the user, or an external one if an account number is provided
     * @param user currently logged in user
     * @param accountNumber account number of account that money is being transferred out of
     * @return true if transfer is successful; false otherwise
     */
    public static boolean displayTransferFundsMenu(User user, int accountNumber) {
        Scanner sc = new Scanner(System.in);
        //TODO get my accounts to display properly (do not include account being transferred from
        RevArrayList<Account> accounts = service.getAccountByUserId(user.getUserId());
        for(int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getAccountNumber() == accountNumber) {
                accounts.remove(i);
            }
        }
        System.out.println("\nFUNDS TRANSFERAL MENU");
        System.out.println("====================");
        System.out.println("Transferring from account #" + accountNumber);
        System.out.println("\nWhere would you like to transfer the money?");
        for (int i = 1; i < accounts.size() + 1; i++) {
            System.out.println(i + ") " + accounts.get(i - 1).getType() + " " + accounts.get(i - 1).getAccountNumber() + " " +
                    (accounts.get(i - 1).getAccountName() != null ? "(" + accounts.get(i - 1).getAccountName() + ")" : ""));
        }
        System.out.println(accounts.size() + 1 + ") Transfer to external account");
        System.out.println("0) Return to Account Menu");

        if (sc.hasNextInt()) {
            int choice = sc.nextInt();

            if(choice == 0) {
                System.out.println("Returning to Account Menu...");
            }

            if(accounts.size() == 0 && choice == 1) {
                return displayExternalAccountTransferMenu(accountNumber);
            } else {
                for (int i = 1; i <= accounts.size() + 1; i++) {
                    if (choice == i) {
                        return displayInternalAccountTransferMenu(accountNumber, accounts.get(i - 1).getAccountNumber());
                    } else if (choice == accounts.size() + 1) {
                        return displayExternalAccountTransferMenu(accountNumber);
                    }
                }
            }
        } else {
            System.out.println("Invalid input. Returning to Account Menu...");
        }
        return false;
    }


    /**
     * Walks through options for an internal transfer
     * @param transferFromAccountNumber account number being transferred from
     * @param transferToAccountNumber account number receiving the funds
     * @return true if transaction is successful; false otherwise
     */
    private static boolean displayInternalAccountTransferMenu(int transferFromAccountNumber, int transferToAccountNumber) {
        double transferAmount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nHow much would you like to transfer?: ");

        if(sc.hasNextDouble()) {
            transferAmount = sc.nextDouble();
        }
        return service.transferFunds(transferAmount, transferFromAccountNumber, transferToAccountNumber);
    }



    /**
     * Walks through options for an external transfer
     * @param transferFromAccountNumber account number being transferred from
     * @return true if transaction is successful; false otherwise
     */
    private static boolean displayExternalAccountTransferMenu(int transferFromAccountNumber) {
        int transferToAccountNumber = 0;
        double transferAmount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the account number you wish to transfer funds to: ");

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
