package ru.job4j.ood.example;

public class ExampleOcp {
    //при добавлении новых операций придется каждый раз менять Calculator
    class Calculator {
        public int calculate(int a, int b, String op) {
            if ("+".equals(op)) {
                return a + b;
            } else if ("-".equals(op)) {
                return a - b;
            } else if ("*".equals(op)) {
                return a * b;
            }
            return 0;
        }
    }
    // при добавлении новых вариантов печати придется менять класс Report
    class Report {
        public String print(String type) {
            if ("PDF".equals(type)) {
                return "Печать в формате PDF";
            } else if ("HTML".equals(type)) {
                return "<html>Печать в HTML</html>";
            }
            return "Формат не поддерживается";
        }
    }
    // для каждого нового животного придется изменять класс и добавлять if
    class Animal {
        public void makeSound(String type) {
            if ("dog".equals(type)) {
                System.out.println("Woof!");
            } else if ("cat".equals(type)) {
                System.out.println("Meow!");
            } else if ("cow".equals(type)) {
                System.out.println("Moo!");
            }
        }
    }



}
