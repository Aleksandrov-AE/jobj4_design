package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.Objects;

public class Ticket3D implements Ticket {
    private Account account;
    private int row;
    private int column;
    private Calendar date;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket3D ticket3D = (Ticket3D) o;
        return row == ticket3D.row && column == ticket3D.column && Objects.equals(account, ticket3D.account) && Objects.equals(date, ticket3D.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, row, column, date);
    }

    public Ticket3D(Account account, int row, int column, Calendar date) {
        this.account = account;
        this.row = row;
        this.column = column;
        this.date = date;
    }
}
