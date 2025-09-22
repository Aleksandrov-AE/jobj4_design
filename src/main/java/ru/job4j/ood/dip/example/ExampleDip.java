package ru.job4j.ood.dip.example;

public class ExampleDip {
    public static class Example {

        static class Keyboard {
            void pressKey() {
                System.out.println("press Key");
            }
        }
        static class Computer {
            private final Keyboard keyboard = new Keyboard();
            void work() {
                keyboard.pressKey();
            }
        }
        // Нарушение DIP: Computer зависит от конкретного устройства, нельзя подменить.


        static class Logger {
            void log(String msg) {
                System.out.println(msg);
            }
        }
        // Нарушение DIP: Logger завязан на конкретный способ вывода (System.out),
        // заменить на файл/сеть без переписывания нельзя.




        static class FileStorage {
            void save(String data) {
                System.out.println("Сохранил: " + data);
            }
        }
        static class DataService {
            void saveData(String data) {
                FileStorage storage = new FileStorage(); // создаём прямо тут
                storage.save(data);
            }
        }
        // Нарушение DIP: DataService зависит от конкретной реализации хранения,
        // подменить хранилище (БД, облако) без правок кода невозможно.
    }

}
