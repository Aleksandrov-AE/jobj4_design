package ru.job4j.ood.lsp.model;

import ru.job4j.ood.lsp.model.vehicle.Vehicle;

public interface Parking {
    public boolean park(Vehicle vehicle);
    public boolean unpark(Vehicle vehicle);
}
