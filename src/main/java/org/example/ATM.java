package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ATM {
    private BankDatabase bankDatabase;
    private Screen screen;
    private Keypad keypad;
    private boolean userAuthenticated;
    private int currentAccountNumber;

    public ATM() {
        bankDatabase = BankDatabase.INSTANCE;
        screen = new Screen();
        keypad = new Keypad();
        userAuthenticated = false;
        currentAccountNumber = 0;
    }

    public void run() {
        while (true) {
            while (!userAuthenticated) {
                screen.displayMessageLine("Welcome!");
                authenticateUser();
            }
            performTransactions();
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen.displayMessageLine("Thank you! Goodbye!");
        }
    }

    private void authenticateUser() {
        screen.displayMessageLine("Please enter your account number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessageLine("Enter your PIN: ");
        int pin = keypad.getInput();

        userAuthenticated =
                bankDatabase.authenticateUser(accountNumber, pin);

        if (userAuthenticated) {
            currentAccountNumber = accountNumber;
        } else {
            screen.displayMessageLine("Invalid account number or PIN. Please try again.");
        }
    }

    private void performTransactions() {
        Transaction currentTransaction;

        boolean userExited = false;
        while (!userExited) {
            int mainMenuSelection = displayMainMenu();

            switch (mainMenuSelection) {
                case 1 -> {
                    currentTransaction = new BalanceInquiry(currentAccountNumber, bankDatabase);
                    currentTransaction.execute();
                }
                case 2 -> {
                    currentTransaction = new Withdrawal(currentAccountNumber, bankDatabase, screen, keypad);
                    currentTransaction.execute();
                }
                case 3 -> {
                    currentTransaction =
                            new Deposit(currentAccountNumber, bankDatabase, screen, keypad);
                    currentTransaction.execute();
                }
                case 4 -> {
                    screen.displayMessageLine("Exiting the system...");
                    userExited = true;
                }
                default -> screen.displayMessageLine("You did not enter a valid selection. Try again.");
            }
        }
    }

    private int displayMainMenu() {
        screen.displayMessageLine("Main menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit");
        screen.displayMessageLine("Enter a choice: ");
        return keypad.getInput();
    }
}