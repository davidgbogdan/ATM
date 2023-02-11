package org.example;

import lombok.Data;

@Data
public class Screen {
    public void displayMessageLine(String message) {
        System.out.println(message);
    }

    public void displayDollarAmount(double amount) {
        System.out.println(amount);
    }
}
