package ru.job4j.ood.lsp.model.vehicle;

import ru.job4j.ood.lsp.model.spot.Spot;

import java.util.Objects;

public class Cargo implements Vehicle {
    private final int id;
    private final int size = 2;

    public Cargo(int id) {

        if (id < 0) {
            throw new IllegalArgumentException("id must be a positive integer");
        }
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
        Cargo cargoCar = (Cargo) o;
        return id == cargoCar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
