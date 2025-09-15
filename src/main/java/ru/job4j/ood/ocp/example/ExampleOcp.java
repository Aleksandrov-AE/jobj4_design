package ru.job4j.ood.ocp.example;
import java.util.Map;
import java.util.HashMap;

public class ExampleOcp {
    // можно списывать любую неотрицательную сумму.
    class PaymentProcessor {
        public void charge(double amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("amount must be >= 0");
            }
        }
    }

    //  Усиление предусловие
    class EnterprisePaymentProcessor extends PaymentProcessor {
        @Override
        public void charge(double amount) {
            if (amount < 100) {
                throw new IllegalArgumentException("minimal enterprise charge is 100");
            }
        }
    }

    // Постусловие


    class UserRepo {
        protected final Map<Long, String> db = new HashMap<>();

        public UserRepo() {
            db.put(1L, "Alice");
        }


        // если id есть — вернуть ИМЯ (не null),
        // если нет — кинуть IllegalArgumentException.

        public String findNameById(long id) {
            if (!db.containsKey(id)) {
                throw new IllegalArgumentException("No such id");
            }
            return db.get(id);
        }
    }

    class CachingUserRepo extends UserRepo {
        @Override
        public String findNameById(long id) {
            if (!db.containsKey(id)) {
                return null; // ослабление постусловия: вместо исключения возвращаем null
            }
            return db.get(id);
        }
    }

    // Инвариант

    class Counter {
        int value;

        public Counter() {
        }

        public Counter(int start) {
            if (start < 0) {
                throw new IllegalArgumentException("value must be >= 0");
            }
            this.value = start;
        }

        public void increment() {
            value++;
        }

        public void decrement() {
            if (value == 0) {
                throw new IllegalStateException("value can't go below 0");
            }
            value--;
        }

        public int getValue() {
            return value;
        }
    }

    class BrokenCounter extends Counter {
        public BrokenCounter(int start) {
            System.out.println("BrokenCounter");
        }

        // "забыли" проверку
        @Override
        public void decrement() {
            super.value--;
        }
    }
}


