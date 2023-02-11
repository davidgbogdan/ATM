    package org.example;

    import lombok.AllArgsConstructor;
    import lombok.Data;

    import java.util.Scanner;

    @Data
    @AllArgsConstructor
    public class Keypad {
        private Scanner input;

        public Keypad() {
            input = new Scanner(System.in);
        }

        public int getInput() {
            return input.nextInt();
        }

    }
