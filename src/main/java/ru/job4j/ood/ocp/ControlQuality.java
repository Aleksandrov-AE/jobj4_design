package ru.job4j.ood.ocp;

import ru.job4j.ood.ocp.model.Food;
import ru.job4j.ood.ocp.store.Store;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;
    private final List<Food> foods;
    public ControlQuality(List<Store> stores, List<Food> foods) {
        this.stores = stores;
        this.foods = foods;
    }
    public void distribute(LocalDate now) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.accept(food, now)) {
                    break;
                }
            }
        }
    }
}
