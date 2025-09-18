package ru.job4j.ood.lsp.model.spot;

public class CargoSpot extends Spot {
    private final static int CARGO_SPOT_SIZE = 2;
    public CargoSpot(int id) {
        super(id, CARGO_SPOT_SIZE);
    }

}
