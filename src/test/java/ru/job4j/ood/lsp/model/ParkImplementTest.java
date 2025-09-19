package ru.job4j.ood.lsp.model;

import org.junit.jupiter.api.Test;

import ru.job4j.ood.lsp.model.vehicle.Car;
import ru.job4j.ood.lsp.model.vehicle.Cargo;
import ru.job4j.ood.lsp.parkservice.ParkImplement;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParkImplementTest {

    @Test
    void parkCargoCar1OnCarSpot3CargoSpot3() {
        ParkImplement parkImplement = new ParkImplement(3, 3);
        Cargo cargoCar = new Cargo(1);
        Ticket ticketCargo = parkImplement.park(cargoCar);
        assertThat(ticketCargo.getReservedSpots().size()).isEqualTo(2);
        assertThat(ticketCargo.getVehicle()).isEqualTo(cargoCar);
        assertThat(parkImplement.getBusySpots().size()).isEqualTo(2);
    }

    @Test
    void unparking() {
        ParkImplement parkImplement = new ParkImplement(3, 3);
        Cargo cargo = new Cargo(1);
        Car car = new Car(2);
        Ticket ticketCar = parkImplement.park(cargo);
        Ticket ticketCargo = parkImplement.park(car);
        parkImplement.unpark(ticketCar);
        parkImplement.unpark(ticketCargo);
        assertThat(parkImplement.getBusySpots().size()).isEqualTo(0);
    }

    @Test
    void checkParkCargoStrategyTwoCarSpotAndOneCargoSpot() {
        ParkImplement parkImplement = new ParkImplement(3, 3);
        Cargo cargo = new Cargo(1);
        Car car = new Car(2);
        Cargo cargo2 = new Cargo(3);
        Ticket ticketCargo1 = parkImplement.park(cargo);
        Ticket ticketCar2 = parkImplement.park(car);
        Ticket ticketCargo3 = parkImplement.park(cargo2);
        assertThat(parkImplement.getBusySpots().size()).isEqualTo(4);
        assertThat(ticketCargo1.getReservedSpots().size()).isEqualTo(2);
        assertThat(ticketCargo3.getReservedSpots().size()).isEqualTo(1);
    }

    @Test
    void checkParkBusy() {
        ParkImplement parkImplement = new ParkImplement(2, 2);
        for (int i = 1; i <= 3; i++) {
            parkImplement.park(new Cargo(i));
        }
        Ticket ticketWithoutSpot = parkImplement.park(new Car(4));
        assertThat(ticketWithoutSpot.getReservedSpots().size()).isEqualTo(0);
    }

    @Test
    void checkThatCarParkOnCargoSpotOnly() {
        ParkImplement parkImplement = new ParkImplement(1, 3);
        Ticket ticketCar1 = parkImplement.park(new Car(1));
        Ticket ticketCar2 = parkImplement.park(new Car(2));
        int size = parkImplement.getBusySpots().size();
        assertThat(size).isEqualTo(1);
        assertThat(ticketCar2.getReservedSpots().size()).isEqualTo(0);
    }


}