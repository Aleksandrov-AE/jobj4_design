package ru.job4j.ood.tdd;

import java.util.*;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    private List<Session> sessions = new ArrayList<Session>();
   Map<Integer, Integer> seats = new HashMap<>();

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return new ArrayList<>(sessions);
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (row <= 0 || column <= 0) {
            throw new IllegalArgumentException();
        }
        if (seats.containsKey(row) && seats.get(row) == column) {
            throw new IllegalStateException();
        }
        seats.put(row, column);
        return new Ticket3D(account, row, column, date);
    }

    @Override
    public void add(Session session) {
        sessions.add(session);
    }
}
