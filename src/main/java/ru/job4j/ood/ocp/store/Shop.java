package ru.job4j.ood.ocp.store;

import ru.job4j.ood.ocp.model.Food;

import java.time.LocalDate;

public class Shop extends AbstractStore {
    public static final double DISCOUNT = 0.2;
    @Override
    public boolean accept(Food food, LocalDate now) {
        boolean accepted = false;
        double spent = spent(food.getExpiryDate(), food.getCreateDate(), now);
        if (spent >= 0.25 && spent <= 0.75) {
            addFood(food);
            accepted = true;
        }
        if (spent > 0.75 && spent < 1) {
           food.setDiscount(DISCOUNT);
           accepted = true;
           addFood(food);
        }
        return accepted;
    }
}
