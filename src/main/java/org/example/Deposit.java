package org.example;

public class Deposit extends Transaction {
    private double amount;
    private Keypad keypad;

    public Deposit(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad) {
        super(accountNumber, screen, bankDatabase);
        this.keypad = keypad;
    }

    @Override
    public void execute() {
        amount = promptForDepositAmount();
        if (amount == -1) {
            return;
        }
        getBankDatabase().credit(getAccountNumber(), amount);
        getScreen().displayMessageLine("Deposit " + amount + " Successful.");
    }

    private double promptForDepositAmount() {
        getScreen().displayMessage("Enter deposit amount: ");
        int input = keypad.getInput();
        if (input <= 0) {
            getScreen().displayMessageLine("Invalid amount. Please try again.");
            return -1;
        }
        return input;
    }
}
