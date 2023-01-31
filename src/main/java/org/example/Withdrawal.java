package org.example;

import lombok.Data;

@Data
public class Withdrawal extends Transaction {
    private Keypad keypad;
    private final static int AMOUNT_20 = 20;
    private final static int AMOUNT_40 = 40;
    private final static int AMOUNT_60 = 60;
    private final static int AMOUNT_100 = 100;
    private final static int AMOUNT_200 = 200;

    public Withdrawal(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }

    @Override
    public void execute() {
        boolean cashDispensed = false;
        double availableBalance;

        while (!cashDispensed) {
            availableBalance = getBankDatabase().getAvailableBalance(getAccountNumber());
            getScreen().displayMessageLine("Withdrawal Menu:");
            getScreen().displayMessageLine("1 - $20");
            getScreen().displayMessageLine("2 - $40");
            getScreen().displayMessageLine("3 - $60");
            getScreen().displayMessageLine("4 - $100");
            getScreen().displayMessageLine("5 - $200");
            getScreen().displayMessageLine("6 - Cancel transaction");
            getScreen().displayMessage("Choose a withdrawal amount: ");

            int selection = keypad.getInput();

            switch (selection) {
                case 1 -> cashDispensed = doSomething(availableBalance, AMOUNT_20);
                case 2 -> cashDispensed = doSomething(availableBalance, AMOUNT_40);
                case 3 -> cashDispensed = doSomething(availableBalance, AMOUNT_60);
                case 4 -> cashDispensed = doSomething(availableBalance, AMOUNT_100);
                case 5 -> cashDispensed = doSomething(availableBalance, AMOUNT_200);
                default -> getScreen().displayMessageLine("Insufficient funds in your account");
            }
        }
    }

    private boolean doSomething(double availableBalance, double amount){
        if (availableBalance >= amount) {
            getBankDatabase().debit(getAccountNumber(), amount);
            getScreen().displayMessageLine("Please take your cash from the dispenser.");
            return true;
        } else {
            getScreen().displayMessageLine("Insufficient funds in your account." + "Please choose a smaller amount.");
            return false;
        }
    }


}

