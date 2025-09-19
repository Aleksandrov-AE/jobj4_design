package ru.job4j.ood.lsp.model.spot;

import ru.job4j.ood.lsp.model.vehicle.Vehicle;

public abstract class Spot {
    private final int id;
    private final int size;
    private Vehicle vehicle;
    private boolean busy;

    public Spot(int id, int size) {
        if (id < 0) {
            throw new IllegalArgumentException("id must be a positive integer");
        }
        this.id = id;
        this.size = size;
    }

    public boolean isBusy() {
        return busy;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void accept(Vehicle vehicle) {
        this.vehicle = vehicle;
        busy = true;
    }

    /*public Optional<Vehicle> getVehicle() {
        if (busy) {
            return Optional.of(vehicle);
        } else {
            return Optional.empty();
        }
    }*/

    public void release() {
        busy = false;
        vehicle = null;
    }
}
