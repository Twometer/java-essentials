package de.twometer.essentials.enumerable.types;

import de.twometer.essentials.enumerable.Enumerable;

public class EnumerableWrapper<T> extends Enumerable<T> {

    private Enumerable<T> baseEnumerable;

    public EnumerableWrapper(Enumerable<T> baseEnumerable) {
        this.baseEnumerable = baseEnumerable;
    }

    @Override
    public int getLength() {
        return baseEnumerable.getLength();
    }

    @Override
    public T getElement(int i) {
        return baseEnumerable.getElement(i);
    }

    @Override
    public boolean hasElement(int i) {
        return baseEnumerable.hasElement(i);
    }
}
