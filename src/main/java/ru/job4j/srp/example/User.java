package ru.job4j.srp.example;

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    // Нарушение SRP: класс и хранит данные, и сам умеет сохраняться в файл
    public void saveToFile() {
        System.out.println("Сохраняем пользователя " + name + " в файл...");
    }
}

