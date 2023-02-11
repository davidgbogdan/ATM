package org.example;

import lombok.Data;

@Data
public class BalanceInquiry extends Transaction {

    public BalanceInquiry(int accountNumber, BankDatabase bankDatabase) {
        super(accountNumber, bankDatabase);
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = new Screen();

        double balance = bankDatabase.getBalance(getAccountNumber());

        screen.displayMessageLine("Balance Information:");
        screen.displayMessageLine("Total balance: ");
        screen.displayDollarAmount(balance);
        screen.displayMessageLine("");
    }
}