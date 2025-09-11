package ru.job4j.srp.example;

class BankAccount {
    private double balance;

    public void deposit(double amount) {
        balance += amount;
        log("Пополнение на " + amount);
    }

    private void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

