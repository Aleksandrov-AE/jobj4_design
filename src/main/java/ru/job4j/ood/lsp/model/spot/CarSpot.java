package ru.job4j.ood.lsp.model.spot;

public class CarSpot extends Spot {
    private final static int CAR_SPOT_SIZE = 1;
    public CarSpot(int id) {
        super(id, CAR_SPOT_SIZE);
    }
}
