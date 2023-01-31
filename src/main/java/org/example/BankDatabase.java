package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Data
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
        ObjectMapper mapper = new ObjectMapper();

        try {
            //Source for the changes: https://mkyong.com/java/java-read-a-file-from-resources-folder
            InputStream is = BankDatabase.class.getClassLoader().getResourceAsStream(DATABASE_FILE);
            //Check if "is" is null https://stackoverflow.com/questions/13571960/java-spring-how-to-use-classpath-to-specify-a-file-location
            Reader reader = new InputStreamReader(is);
            accounts = mapper.readValue(reader, new TypeReference<List<Account>>(){});
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading accounts from file: " + e.getMessage());
        }
    }

    public void saveAccounts(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileWriter writer = new FileWriter(DATABASE_FILE);
            mapper.writeValue(writer, accounts);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving accounts to file: " + e.getMessage());
        }
    }
}
