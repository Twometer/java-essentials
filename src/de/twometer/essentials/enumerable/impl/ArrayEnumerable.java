package de.twometer.essentials.enumerable.impl;

import de.twometer.essentials.enumerable.Enumerable;

public class ArrayEnumerable<T> extends Enumerable<T> {

    private T[] array;

    public ArrayEnumerable(T[] array) {
        this.array = array;
    }

    @Override
    public int getLength() {
        return array.length;
    }

    @Override
    public T getElement(int i) {
        return array[i];
    }

    @Override
    public boolean hasElement(int i) {
        return i < array.length;
    }
}
