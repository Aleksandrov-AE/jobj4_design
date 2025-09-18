package ru.job4j.ood.lsp.model.vehicle;

import ru.job4j.ood.lsp.model.spot.Spot;

import java.util.Objects;

public class Car implements Vehicle {
    private final int size = 1;
    private int id;

    public Car(int id) {
        this.id = id;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getFit() {
        return size;
    }

    @Override
    public boolean canPark(Spot spot) {
        return size == spot.getSize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
