package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceInquiry extends Transaction {
    public BalanceInquiry(int accountNumber, Screen screen, BankDatabase bankDatabase) {
        super(accountNumber, screen, bankDatabase);
    }
    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
        double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());

        screen.displayMessageLine("Balance Information:");
        screen.displayMessage("Available balance: ");
        screen.displayDollarAmount(availableBalance);
        screen.displayMessage("Total balance: ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessageLine("");
    }
}