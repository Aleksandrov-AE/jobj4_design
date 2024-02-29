package ru.job4j.collection;


import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean added = !this.contains(value);
        if (added) {
            set.add(value);
        }
        return added;
    }

    @Override
    public boolean contains(T value) {
        boolean found = false;
        for (T t : set) {
            if (Objects.equals(value, t)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}