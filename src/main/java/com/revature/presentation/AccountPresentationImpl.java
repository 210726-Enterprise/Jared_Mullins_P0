package com.revature.presentation;

import com.revature.model.User;
import com.revature.service.AccountService;
import com.revature.service.AccountServiceImpl;

import java.util.Scanner;

public class AccountPresentationImpl implements AccountPresentation{

    private AccountService service;

    public AccountPresentationImpl() {
        service = new AccountServiceImpl();
    }

    public void openNewAccountMenu(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("CREATING NEW ACCOUNT FOR " + user.getUsername() + "\n");
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
                    service.createAccount(user, accountType);
                    break;
                case 2:
                    accountType = "Savings";
                    service.createAccount(user, accountType);
                    break;
                case 3:
                    //TODO fix feedback
                    System.out.println("Invalid Input. Please enter 0, 1, or 2");
                    openNewAccountMenu(user);
                    break;
            }
        } else {
            //TODO fix feedback
            System.out.println("Invalid Input");
            openNewAccountMenu(user);
        }
        System.out.println("Successfully created new account!");
    }

}
