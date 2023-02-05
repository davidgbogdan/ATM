package org.example;

import lombok.Data;

@Data
public class BalanceInquiry extends Transaction {

    public BalanceInquiry(int accountNumber, Screen screen, BankDatabase bankDatabase) {
        super(accountNumber, screen, bankDatabase);
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        double balance = bankDatabase.getBalance(getAccountNumber());

        screen.displayMessageLine("Balance Information:");
        screen.displayMessage("Total balance: ");
        screen.displayDollarAmount(balance);
        screen.displayMessageLine("");
    }
}