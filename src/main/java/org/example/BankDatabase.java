package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BankDatabase {
    private List<Account> accounts;
    private final String DATABASE_FILE = "bankdatabase.json";

    public BankDatabase() {
        accounts = new ArrayList<>();
        loadAccounts();
    }

    public boolean authenticateUser(int accountNumber, int pin) {
        Account userAccount = getAccount(accountNumber);
        if (userAccount != null && userAccount.validatePin(pin)) {
            return true;
        } else {
            return false;
        }
    }

    public double getAvailableBalance(int accountNumber) {
        return getAccount(accountNumber).getAvailableBalance();
    }

    public double getTotalBalance(int accountNumber) {
        return getAccount(accountNumber).getTotalBalance();
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

    public void loadAccounts(){
        Gson gson = new Gson();

        try {
            InputStream is = BankDatabase.class.getResourceAsStream(DATABASE_FILE);
            //Check if "is" is null https://stackoverflow.com/questions/13571960/java-spring-how-to-use-classpath-to-specify-a-file-location
            Reader reader = new InputStreamReader(is);



            Type listType = new TypeToken<List<Account>>() {}.getType();
            accounts = gson.fromJson(reader, listType);
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading accounts from file: " + e.getMessage());
        }
    }

    public void saveAccounts(){
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter(DATABASE_FILE);
            gson.toJson(accounts, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving accounts to file: " + e.getMessage());
        }
    }
}
