package org.example;

public class ATM {
    private BankDatabase bankDatabase;
    private Screen screen;
    private Keypad keypad;
    private boolean userAuthenticated;
    private int currentAccountNumber;

    public ATM() {
        bankDatabase = new BankDatabase();
        screen = new Screen();
        keypad = new Keypad(screen);
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
        screen.displayMessage("Please enter your account number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessage("Enter your PIN: ");
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
        Transaction currentTransaction = null;

        boolean userExited = false;
        while (!userExited) {
            int mainMenuSelection = displayMainMenu();

            switch (mainMenuSelection) {
                case 1:
                    currentTransaction = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                    currentTransaction.execute();
                    break;
                case 2:
                    currentTransaction = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad);
                    currentTransaction.execute();
                    break;
                case 3:
                    currentTransaction =
                            new Deposit(currentAccountNumber, screen, bankDatabase, keypad);
                    currentTransaction.execute();
                    break;
                case 4:
                    screen.displayMessageLine("Exiting the system...");
                    userExited = true;
                    break;
                default:
                    screen.displayMessageLine("You did not enter a valid selection. Try again.");
                    break;
            }
        }
    }

    private int displayMainMenu() {
        screen.displayMessageLine("Main menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput();
    }


}
