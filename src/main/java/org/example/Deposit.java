package org.example;

import lombok.Data;

@Data
public class Deposit extends Transaction {
    private double amount;
    private Keypad keypad;
    private Screen screen;

    public Deposit(int accountNumber, BankDatabase bankDatabase, Screen screen, Keypad keypad) {
        super(accountNumber, bankDatabase);
        this.keypad = keypad;
        this.screen = screen;

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
        getScreen().displayMessageLine("Enter deposit amount: ");
        int input = keypad.getInput();
        if (input <= 0) {
            getScreen().displayMessageLine("Invalid amount. Please try again.");
            return -1;
        }
        return input;
    }
}
