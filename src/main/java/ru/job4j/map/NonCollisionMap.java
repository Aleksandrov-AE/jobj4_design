package ru.job4j.map;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int hash = Objects.hashCode(key);
        int index = indexFor(hash);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            return  true;
        }
        return false;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        int n = capacity;
        return (hash == 0) ? 0 : (n - 1) & hash;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry: table) {
            if (entry != null) {
                int hash = hash(Objects.hashCode(entry.key));
                int index = indexFor(hash);
                newTable[index] = entry;
            }
        }
        table = newTable;
        modCount++;
    }

    private int getIndex(K key) {
        int hash = hash(Objects.hashCode(key));
        return indexFor(hash);
    }


    @Override
    public V get(K key) {
        int index = getIndex(key);
        if (table[index] != null
                && Objects.hashCode(table[index].key) == Objects.hashCode(key)
                && Objects.equals(table[index].key, key)) {
                return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        if (table[index] != null
                && Objects.hashCode(table[index].key) == Objects.hashCode(key)
                && Objects.equals(table[index].key, key)) {
            table[index] = null;
            modCount++;
            count--;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {

        return new NonCollisionMapIterator();
    }

    private class NonCollisionMapIterator implements Iterator<K> {

        private int expectedModCount = modCount;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            while (currentIndex < capacity && table[currentIndex] == null) {
                currentIndex++;
            }
            return currentIndex < capacity;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            return table[currentIndex++].key;
        }
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}