package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public boolean validatePin(int userPin) {
        if (userPin == pin) {
            return true;
        } else {
            return false;
        }
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        balance -= amount;
    }
}