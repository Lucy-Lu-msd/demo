package com.example.demo.model;

import java.math.BigDecimal;

public class AccountFactory {
    public static Account createAccount(String name, String type, BigDecimal balance, String status) {
        return new Account(name, type, balance, status); // Accessible within the same package
    }
}
