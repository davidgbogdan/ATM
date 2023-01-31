package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;

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