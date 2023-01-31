package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;

    public Account(int accountNumber, int pin, double availableBalance, double totalBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.availableBalance = availableBalance;
        this.totalBalance = totalBalance;
    }

    public boolean validatePin(int userPin) {
        if (userPin == pin) {
            return true;
        } else {
            return false;
        }
    }

    public void credit(double amount) {
        totalBalance += amount;
    }

    public void debit(double amount) {
        availableBalance -= amount;
        totalBalance -= amount;
    }
}