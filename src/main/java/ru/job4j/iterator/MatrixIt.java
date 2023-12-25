package ru.job4j.iterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
        row = 0;
        column = 0;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (row < data.length) {
            if (data[row].length == 0 || column == data[row].length) {
                row++;
                column = 0;
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
        return data[row][column++];
    }

}