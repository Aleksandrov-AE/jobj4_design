package ru.job4j.ood.lsp.model;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.vehicle.CargoCar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParkImplementTest {

    @Test
    void parkCargoCar1OnCarSpot3CargoSpot3() {
        ParkImplement parkImplement = new ParkImplement(3, 3);
        CargoCar cargoCar = new CargoCar(1);
        Ticket ticketCargo = parkImplement.park(cargoCar);
        assertThat(ticketCargo.getReservedSpots().size()).isEqualTo(2);
        assertThat(ticketCargo.getVehicle()).isEqualTo(cargoCar);
        assertThat(parkImplement.getBusySpots().size()).isEqualTo(2);
    }

    @Test
    void unparking() {
        ParkImplement parkImplement = new ParkImplement(3, 3);
        CargoCar cargoCar = new CargoCar(1);
        Ticket ticket = parkImplement.park(cargoCar);
        parkImplement.unpark(ticket);
        assertThat(parkImplement.getBusySpots().size()).isEqualTo(0);
    }

}