package ru.job4j.ood.lsp.model;

import ru.job4j.ood.lsp.model.vehicle.Vehicle;

public interface Parking {
    Ticket park(Vehicle vehicle);
    void unpark(Ticket ticket);
}
