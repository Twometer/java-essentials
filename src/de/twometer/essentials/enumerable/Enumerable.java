package de.twometer.essentials.enumerable;

import de.twometer.essentials.enumerable.impl.ArrayEnumerable;
import de.twometer.essentials.enumerable.impl.ListEnumerable;
import de.twometer.essentials.enumerable.impl.SelectEnumerable;
import de.twometer.essentials.enumerable.impl.WhereEnumerable;
import de.twometer.essentials.enumerable.types.Selector;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class Enumerable<T> implements Iterable<T> {

    public static <T> Enumerable<T> fromArray(T[] arr) {
        return new ArrayEnumerable<>(arr);
    }

    public static <T> Enumerable<T> fromList(List<T> list) {
        return new ListEnumerable<>(list);
    }

    public abstract int getLength();

    public abstract T getElement(int i);

    public abstract boolean hasElement(int i);

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;

            @Override
            public boolean hasNext() {
                int curIdx = index;
                while (true) {
                    if (!hasElement(curIdx)) {
                        curIdx++;
                        if (curIdx >= getLength())
                            return false;
                        continue;
                    }
                    return true;
                }
            }

            @Override
            public T next() {
                while (true) {
                    if (!hasElement(index)) {
                        advance();
                        continue;
                    }
                    T elem = getElement(index);
                    advance();
                    return elem;
                }
            }

            private void advance() {
                if (index >= getLength())
                    throw new IndexOutOfBoundsException("End of enumerable reached");
                index++;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T t : this)
            action.accept(t);
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    public <O> Enumerable<O> select(Selector<T, O> selector) {
        return new SelectEnumerable<>(this, selector);
    }

    public Enumerable<T> where(Predicate<T> predicate) {
        return new WhereEnumerable<>(this, predicate);
    }

    public <O> Enumerable<O> ofType(Class<O> type) {
        return where(t -> t.getClass() == type).select(t -> (O) t);
    }
}
