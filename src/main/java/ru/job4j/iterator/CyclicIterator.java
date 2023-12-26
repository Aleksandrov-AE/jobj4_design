package ru.job4j.iterator;

import java.util.*;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data == null || data.isEmpty()) {
            return false;
        }
        boolean allNull = data.stream().allMatch(Objects::isNull);
        if (allNull) {
            return  false;
        }
        if (index == data.size()) {
            index = 0;
        }
        while (data.get(index) == null) {
            index++;
        }
        return true;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(index++);
    }
}