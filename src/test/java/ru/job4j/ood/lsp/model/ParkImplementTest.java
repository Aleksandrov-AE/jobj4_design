package ru.job4j.ood.lsp.model;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.spot.CarSpot;
import ru.job4j.ood.lsp.model.spot.CargoSpot;
import ru.job4j.ood.lsp.model.spot.Spot;
import ru.job4j.ood.lsp.model.vehicle.Car;
import ru.job4j.ood.lsp.model.vehicle.Cargo;
import ru.job4j.ood.lsp.parkservice.ParkImplement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParkImplementExtraTest {

    @Test
    void cargoPrefersTwoCarSpotsWhenAvailable() {
        ParkImplement park = new ParkImplement(3, 2);
        Ticket t = park.park(new Cargo(1));
        assertThat(t.getReservedSpots()).hasSize(2);
        assertThat(t.getReservedSpots()).allMatch(s -> s instanceof CarSpot);
        assertThat(park.getBusySpots()).hasSize(2);
    }

    @Test
    void cargoFallsBackToOneCargoSpotWhenNoTwoCarSpots() {
        ParkImplement park = new ParkImplement(1, 2);
        Ticket t = park.park(new Cargo(10));
        assertThat(t.getReservedSpots()).hasSize(1);
        assertThat(t.getReservedSpots().get(0)).isInstanceOf(CargoSpot.class);
        assertThat(park.getBusySpots()).hasSize(1);
    }

    @Test
    void carNeverParksOnCargoSpot() {
        ParkImplement park = new ParkImplement(0, 5);
        Ticket t = park.park(new Car(7));
        assertThat(t.getReservedSpots()).isEmpty();
        assertThat(park.getBusySpots()).isEmpty();
    }

    @Test
    void unparkFreesExactlyThoseSpots() {
        ParkImplement park = new ParkImplement(3, 1);
        Ticket t = park.park(new Cargo(100));
        List<Integer> usedIds = t.getReservedSpots().stream().map(Spot::getId).toList();
        assertThat(usedIds).hasSize(2);
        park.unpark(t);
        assertThat(park.getBusySpots()).isEmpty();
        Ticket t2 = park.park(new Cargo(101));
        assertThat(t2.getReservedSpots()).hasSize(2);
    }

    @Test
    void whenNoPlaceParkReturnsEmptyTicketAndVehicle() {
        ParkImplement park = new ParkImplement(1, 1);
        park.park(new Car(1));
        Car car = new Car(2);
        Ticket t = park.park(car);
        assertThat(t.getReservedSpots()).isEmpty();
        assertThat(t.getVehicle().get()).isEqualTo(car);
        assertThat(park.getBusySpots()).hasSize(1);
    }

    @Test
    void negativeIdRejected() {
        assertThatThrownBy(() -> new Car(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Cargo(-2)).isInstanceOf(IllegalArgumentException.class);
    }
}
