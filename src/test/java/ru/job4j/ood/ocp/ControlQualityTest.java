package ru.job4j.ood.ocp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.ocp.model.Food;
import ru.job4j.ood.ocp.store.Shop;
import ru.job4j.ood.ocp.store.Store;
import ru.job4j.ood.ocp.store.Trash;
import ru.job4j.ood.ocp.store.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControlQualityTest {

    private List<Store> store;
    private List<Food> foods;
    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;
    private final LocalDate now = LocalDate.now();

    @BeforeEach
    void setUp() {
        store = new ArrayList<>();
        foods = new ArrayList<>();
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        store.add(warehouse);
        store.add(shop);
        store.add(trash);
    }

    @Test
    void testWarehouse() {
        Food milkSpentZero = new Food("Milk", now.plusDays(5), now, 100);
        foods.add(milkSpentZero);
        ControlQuality q = new ControlQuality(store);
        q.distribute(foods, now);
        assertEquals(warehouse.getAllFoods().size(), 1);

    }

    @Test
    void testTrash() {
        Food appleTrash = new Food("Apple", now.minusDays(3), now.minusDays(5), 100);
        foods.add(appleTrash);
        ControlQuality q = new ControlQuality(store);
        q.distribute(foods, now);
        assertEquals(trash.getAllFoods().size(), 1);

    }

    @Test
    void testShop() {
        Food cheeseSpent50 = new Food("Cheese", now.plusDays(5), now.minusDays(5), 100);
        foods.add(cheeseSpent50);
        ControlQuality q = new ControlQuality(store);
        q.distribute(foods, now);
        assertEquals(shop.getAllFoods().size(), 1);

    }

    @Test
    void testShopDiscount() {
        Food sauceSpent80 = new Food("Cheese", now.plusDays(1), now.minusDays(5), 100);
        foods.add(sauceSpent80);
        ControlQuality q = new ControlQuality(store);
        q.distribute(foods, now);
        assertEquals(shop.getAllFoods().size(), 1);
        assertEquals(sauceSpent80.currentPrice(), 80);

    }

    @Test
    void testShopDiscountAndResort() {
        Food sauceSpent80 = new Food("Cheese", now.plusDays(1), now.minusDays(5), 100);
        foods.add(sauceSpent80);
        ControlQuality q = new ControlQuality(store);
        q.distribute(foods, now);
        q.resort(now);
        assertEquals(shop.getAllFoods().size(), 1);
        assertEquals(sauceSpent80.currentPrice(), 80);

    }



}