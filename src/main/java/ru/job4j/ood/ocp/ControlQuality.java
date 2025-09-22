package ru.job4j.ood.ocp;

import ru.job4j.ood.ocp.model.Food;
import ru.job4j.ood.ocp.store.Store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;
    public ControlQuality(List<Store> stores) {
        this.stores = stores;

    }
    public void distribute(List<Food> foods, LocalDate now) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.accept(food, now)) {
                    break;
                }
            }
        }
    }
    public void resort(LocalDate now) {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
           foods.addAll(store.freeUp());
        }
        distribute(foods, now);
    }
}
