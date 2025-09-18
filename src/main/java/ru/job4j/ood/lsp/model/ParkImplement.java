package ru.job4j.ood.lsp.model;

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

    ParkImplement(int carSpotsCount, int cargoSpotsCount) {
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

    public List<Spot> getAllSpots() {
        return List.copyOf(spots);
    }

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
    public boolean park(Vehicle vehicle) {
        boolean accept = false;
        Spot reservedPreviosSpot = null;
        Iterator<Spot> spotIterator = spots.iterator();
        while (spotIterator.hasNext()) {
            Spot spot = spotIterator.next();
            if (spot.isBusy()) {
                continue;
            }
            if (vehicle.canPark(spot)) {
                spot.accept(vehicle);
                accept = true;
                break;
            } else {
                if (reservedPreviosSpot == null) {
                    reservedPreviosSpot = spot;
                } else {
                    reservedPreviosSpot.accept(vehicle);
                    spot.accept(vehicle);
                    accept = true;
                    break;
                }
            }
        }
        return accept;

        }

    @Override
    public boolean unpark(Vehicle vehicle) {
        boolean release = false;
        for (Spot spot : spots) {
            if (spot.getVehicle().equals(vehicle)) {
                spot.release();
                release = true;
            }
        }
        return release;
    }

}
