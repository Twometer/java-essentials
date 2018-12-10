package de.twometer.essentials.enumerable.impl;

import java.util.List;

public class ListEnumerable<T> extends ArrayEnumerable<T> {
    public ListEnumerable(List<T> list) {
        super((T[]) list.toArray());
    }
}
