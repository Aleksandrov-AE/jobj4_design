package ru.job4j.ood.ocp.store;

import ru.job4j.ood.ocp.model.Food;

import java.time.LocalDate;

public interface Store {
    boolean accept(Food food, LocalDate now);
}
