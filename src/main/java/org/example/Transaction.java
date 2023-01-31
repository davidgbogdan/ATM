package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Transaction {
    private int accountNumber;
    private Screen screen;
    private BankDatabase bankDatabase;

    public abstract void execute();
}