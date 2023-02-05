package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public enum BankDatabase {
    INSTANCE;
    private List<Account> accounts;
    private final String DATABASE_FILE = "bankdatabase.json";

    BankDatabase() {
        accounts = new ArrayList<>();
        loadAccounts();
    }

    public static BankDatabase getInstance() {
        return INSTANCE;
    }

    public boolean authenticateUser(int accountNumber, int pin) {
        Account userAccount = getAccount(accountNumber);
        if (userAccount != null && userAccount.validatePin(pin)) {
            return true;
        } else {
            return false;
        }
    }

    public double getBalance(int accountNumber) {
        return getAccount(accountNumber).getBalance();
    }

    public void credit(int accountNumber, double amount) {
        getAccount(accountNumber).credit(amount);
    }

    public void debit(int accountNumber, double amount) {
        getAccount(accountNumber).debit(amount);
    }

    private Account getAccount(int accountNumber) {
        for (Account currentAccount : accounts) {
            if (currentAccount.getAccountNumber() == accountNumber) {
                return currentAccount;
            }
        }
        return null;
    }

    public void loadAccounts() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = BankDatabase.class.getClassLoader().getResourceAsStream(DATABASE_FILE);
             Reader reader = new InputStreamReader(is)) {
            accounts = mapper.readValue(reader, new TypeReference<List<Account>>() {
            });
        } catch (IOException e) {
            System.out.println("Error loading accounts from file: " + e.getMessage());
        }
    }

    public void saveAccounts() {
        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(DATABASE_FILE)) {
            mapper.writeValue(writer, accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts to file: " + e.getMessage());
        }
    }

}
