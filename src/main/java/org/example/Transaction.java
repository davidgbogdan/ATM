package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Transaction {
    private int accountNumber;
    private BankDatabase bankDatabase = BankDatabase.getInstance();

    public abstract void execute();
}