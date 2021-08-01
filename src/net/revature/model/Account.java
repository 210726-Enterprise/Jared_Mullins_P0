package net.revature.model;

import java.util.Scanner;

public class Account {
    private int accountNumber;
    private double balance = 0;

    public Account() {
        this.accountNumber = (int) Math.floor(Math.random() * (999 - 100) + 100);
        this.balance = balance;
    }

    public void makeDeposit(double depositAmount) {
        if(depositAmount > 0) {
            balance += depositAmount;
            System.out.printf("Successfully deposited $%,.2f %n", depositAmount);
            System.out.printf("Your new balance is $%,.2f %n", balance);
        } else {
            System.out.println("Deposit amounts must be greater than $0.00");
        }
    }

    public void makeWithdrawal(double withdrawalAmount) {
        if(withdrawalAmount <= 0) {
            System.out.println("Withdrawal amounts must be greater than $0.00");
            return;
        }
        if(withdrawalAmount <= balance) {
            balance -= withdrawalAmount;
            System.out.printf("Successfully withdrew $%,.2f %n", withdrawalAmount);
            System.out.printf("Your new balance is $%,.2f %n", balance);
        } else {
            System.out.println("Insufficient Funds");
            System.out.printf("Your current balance is $%,.2f %n", balance);
            System.out.println("Would you like to withdraw the remaining balance instead? y/n");
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                switch(choice) {
                    case "y":
                        makeWithdrawal(balance);
                        break;
                    case "n":
                        System.out.println("Returning to user menu");
                        break;
                }
            } else {
                System.out.println("Invalid entry. Returning to user menu.");
            }

        }
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
