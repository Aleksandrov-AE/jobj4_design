package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<E> implements Iterable<E> {
    private int size;
    private int modCount;
    private Node<E> head;

    public void add(E value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(value, null);
        }
        modCount++;
        size++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public E deleteFirst() {
        Node<E> delete = head;
        E result;
        if (head == null) {
           throw new NoSuchElementException();
        }
        result = head.item;
        head.item = null;
        head = delete.next;
        delete.next = null;
        modCount++;
        size--;
        return result;
    }
    public void addFirst(E value) {
        head = new Node<>(value, head);
        modCount++;
        size++;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Node<E> cursor = head;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = cursor.item;
                cursor = cursor.next;
                return result;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}