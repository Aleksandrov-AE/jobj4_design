package ru.job4j.ood.lsp.model.vehicle;

import ru.job4j.ood.lsp.model.spot.Spot;

public interface Vehicle {
    public int getFit();
    public boolean canPark(Spot spot);
    public int getId();
}
