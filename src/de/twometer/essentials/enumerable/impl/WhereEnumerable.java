package de.twometer.essentials.enumerable.impl;

import de.twometer.essentials.enumerable.Enumerable;
import de.twometer.essentials.enumerable.types.EnumerableWrapper;

import java.util.function.Predicate;

public class WhereEnumerable<T> extends EnumerableWrapper<T> {

    private Predicate<T> predicate;

    public WhereEnumerable(Enumerable<T> baseEnumerable, Predicate<T> predicate) {
        super(baseEnumerable);
        this.predicate = predicate;
    }

    @Override
    public boolean hasElement(int i) {
        return super.hasElement(i) && predicate.test(super.getElement(i));
    }
}