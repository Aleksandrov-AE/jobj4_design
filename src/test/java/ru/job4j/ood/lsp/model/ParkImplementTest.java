package ru.job4j.ood.lsp.model;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.vehicle.Car;
import ru.job4j.ood.lsp.model.vehicle.CargoCar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParkImplementTest {

    @Test
    void parkCar1OnCarSpot3CargoSpot3() {
        ParkImplement parkImplement = new ParkImplement(3, 3);
        boolean parkCargo = parkImplement.park(new CargoCar(1));
        assertThat(parkCargo).isTrue();
        assertThat(parkImplement.getBusySpots().size()).isEqualTo(2);
    }

    @Test
    void parkCar1Cargo2OnCarSpot3CargoSpot3() {
        ParkImplement parkImplement = new ParkImplement(3, 3);
        boolean parkCargo = parkImplement.park(new CargoCar(1));
        boolean parkCargo2 = parkImplement.park(new CargoCar(2));
        boolean parkCar = parkImplement.park(new Car(3));
        assertThat(parkCargo).isTrue();
        assertThat(parkCargo2).isTrue();
        assertThat(parkCar).isTrue();
        assertThat(parkImplement.getBusySpots().size()).isEqualTo(4);
    }

}