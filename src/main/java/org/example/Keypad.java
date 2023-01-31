package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;
@Data
@AllArgsConstructor
public class Keypad {
    private Scanner input;
    private Screen screen;

    public Keypad(Screen screen) {
        this.screen = screen;
        input = new Scanner(System.in);
    }

    public int getInput() {
        return input.nextInt();
    }

    public double getDoubleInput() {
        return input.nextDouble();
    }

    public void displayMessage(String message) {
        screen.displayMessage(message);
    }

    public void displayMessageLine(String message) {
        screen.displayMessageLine(message);
    }
}
