package ru.job4j.ood.lsp.parkservice;

import ru.job4j.ood.lsp.model.Ticket;
import ru.job4j.ood.lsp.model.spot.CarSpot;
import ru.job4j.ood.lsp.model.spot.CargoSpot;
import ru.job4j.ood.lsp.model.spot.Spot;
import ru.job4j.ood.lsp.model.vehicle.Vehicle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParkImplement implements Parking {
    private final List<Spot> spots;

    public ParkImplement(int carSpotsCount, int cargoSpotsCount) {
        if (carSpotsCount <= 0 && cargoSpotsCount <= 0) {
            throw new IllegalArgumentException("carSpotsCount and cargoSpotsCount must be greater than 0");
        }
        this.spots = Stream.concat(
                IntStream.rangeClosed(1, carSpotsCount)
                        .mapToObj(CarSpot::new),
                IntStream.rangeClosed(carSpotsCount + 1, carSpotsCount + cargoSpotsCount)
                        .mapToObj(CargoSpot::new)
        ).collect(Collectors.toList());

    }

    /*public List<Spot> getAllSpots() {
        return List.copyOf(spots);
    }*/

    public List<Spot> getBusySpots() {
        List<Spot> busySpots = new ArrayList<>();
        for (Spot spot : spots) {
            if (spot.isBusy()) {
                busySpots.add(spot);
            }
        }
        return busySpots;
    }

    @Override
    public Ticket park(Vehicle vehicle) {
        Ticket ticket = new Ticket(vehicle);
        Spot reservedPreviosSpot = null;
        for (Spot spot : spots) {
            if (spot.isBusy()) {
                continue;
            }
            if (vehicle.canPark(spot)) {
                spot.accept(vehicle);
                ticket.reservSpot(spot);
                break;
            } else {
                if (reservedPreviosSpot == null) {
                    reservedPreviosSpot = spot;
                } else {
                    reservedPreviosSpot.accept(vehicle);
                    spot.accept(vehicle);
                    ticket.reservSpot(reservedPreviosSpot);
                    ticket.reservSpot(spot);
                    break;
                }
            }
        }
        return ticket;

        }

    @Override
    public Ticket unpark(Ticket ticket) {
        for (Spot spot : ticket.getReservedSpots()) {
            spot.release();
        }
        return ticket;
    }

}
