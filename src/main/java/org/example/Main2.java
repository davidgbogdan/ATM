package org.example;

import com.google.gson.Gson;

public class Main2 {
    public static void main(String[] args) {
        Account account1 = new Account(1, 1111, 1000, 2000);
        Gson gson = new Gson();
        String myJson = gson.toJson(account1);
        System.out.println(myJson);
    }
}
