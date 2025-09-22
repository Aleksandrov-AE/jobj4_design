package ru.job4j.ood.ocp.store;

import ru.job4j.ood.ocp.model.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> foods = new ArrayList<>();
    public void addFood(Food food) {
        foods.add(food);
    }
    public Collection<Food> getAllFoods() {
        return List.copyOf(foods);
    }
    public Collection<Food> freeUp() {
        Collection<Food> result = List.copyOf(foods);
        foods.clear();
        return result;
    }

    public double spent(LocalDate expiryDatete, LocalDate createDate, LocalDate now) {
        long total = ChronoUnit.DAYS.between(createDate, expiryDatete);
        long useDay = Math.max(1, ChronoUnit.DAYS.between(createDate, now));
        return (double) useDay / total;
    }

}
