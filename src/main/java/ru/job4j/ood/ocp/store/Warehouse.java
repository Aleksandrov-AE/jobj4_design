package ru.job4j.ood.ocp.store;

import ru.job4j.ood.ocp.model.Food;

import java.time.LocalDate;

public class Warehouse extends AbstractStore {

    @Override
    public boolean accept(Food food, LocalDate now) {
        boolean accepted = false;
        double spent = spent(food.getExpiryDate(), food.getCreateDate(), now);
        if (spent < 0.25) {
            addFood(food);
            accepted = true;
        }
        return accepted;
    }
}
