package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (index < data.length)  {
            if (data[index] % 2 != 0) {
                index++;
            } else {
                result = true;
                break;
            }
        }
        return result;

    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

}