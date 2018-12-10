package examples;

import de.twometer.essentials.enumerable.Enumerable;

import java.util.ArrayList;
import java.util.List;

public class EnumerableExample {

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        ints.add(5);
        ints.add(3);
        ints.add(9);
        ints.add(42);
        Enumerable.fromList(ints).select(String::valueOf).where(i -> i.length() == 2).forEach(s -> System.out.println("Value = " + s));

        Object[] objs = new Object[]{
                "Test",
                5,
                new EnumerableExample()
        };

        Enumerable.fromArray(objs).ofType(String.class).forEach(s -> System.out.println("Found string " + s));
    }

}
