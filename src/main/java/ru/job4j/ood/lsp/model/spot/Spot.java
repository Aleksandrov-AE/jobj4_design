package ru.job4j.ood.lsp.model.spot;

import ru.job4j.ood.lsp.model.vehicle.Vehicle;

public abstract class Spot {
    private int id;
    private int size;
    private Vehicle vehicle;
    private boolean busy = false;

    public Spot(int id, int size) {
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

    public Vehicle getVehicle() {
        return vehicle;
    }
    public void release() {
        busy = false;
        vehicle = null;
    }
}
