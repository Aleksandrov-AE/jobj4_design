package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;
    private final static int START_CAPACITY = 10;


    public SimpleArrayList(int capacity) {
        if (capacity < 10) {
            capacity = START_CAPACITY;
        }
        container = (T[]) new Object[capacity];

    }


    @Override
    public void add(T value) {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T result = container[index];
        if (checkIndex(index)) {
            container[index] = newValue;
        }
        return result;
    }

    @Override
    public T remove(int index) {
        T removeElement = null;
        if (checkIndex(index)) {
            removeElement = container[index];
            System.arraycopy(container, index + 1, container, index, size - index - 1);
            modCount++;
            size--;
        }
        return removeElement;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int index;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
    boolean checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }
}