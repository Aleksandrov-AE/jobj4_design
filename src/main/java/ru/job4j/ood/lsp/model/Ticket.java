package ru.job4j.ood.lsp.model;

import ru.job4j.ood.lsp.model.spot.Spot;
import ru.job4j.ood.lsp.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private final List<Spot> reservedSpots = new ArrayList<>();
    private final Vehicle vehicle;

    public Ticket(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    void reservSpot(Spot spot) {
        reservedSpots.add(spot);
    }
    public List<Spot> getReservedSpots() {
        return List.copyOf(reservedSpots);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
