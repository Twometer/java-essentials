package de.twometer.essentials.enumerable.types;

public interface Selector<I, O> {

    O select(I i);

}
