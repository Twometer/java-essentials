package de.twometer.essentials.enumerable.impl;

import de.twometer.essentials.enumerable.Enumerable;
import de.twometer.essentials.enumerable.types.Selector;

public class SelectEnumerable<I, O> extends Enumerable<O> {

    private Enumerable<I> baseEnumerable;
    private Selector<I, O> selector;

    public SelectEnumerable(Enumerable<I> baseEnumerable, Selector<I, O> selector) {
        this.baseEnumerable = baseEnumerable;
        this.selector = selector;
    }

    @Override
    public int getLength() {
        return baseEnumerable.getLength();
    }

    @Override
    public O getElement(int i) {
        return selector.select(baseEnumerable.getElement(i));
    }

    @Override
    public boolean hasElement(int i) {
        return baseEnumerable.hasElement(i);
    }
}
