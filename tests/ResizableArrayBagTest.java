package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Bags.ResizableArrayBag;

public class ResizableArrayBagTest {
    @Test
    void testAdd() {
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        test.add("first");
        test.add("second");
        test.add("third");

        String[] stringArr = {"first", "second", "third"};
        assertEquals(true, Arrays.equals(stringArr, test.toArray()));
    }

    @Test
    void testClear() {
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        test.add("first");
        test.add("second");
        test.add("third");

        test.clear();
        assertEquals(true, Arrays.equals(test.toArray(), new String[0]));
    }

    @Test
    void testContains() {
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        test.add("first");
        test.add("second");
        test.add("third");

        assertEquals(true, test.contains("third"));
        assertEquals(false, test.contains("something"));
    }

    @Test
    void testDifference() {

    }

    @Test
    void testGetCurrentSize() {
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        test.add("first");
        test.add("second");
        test.add("third");
        assertEquals(3, test.getCurrentSize());
        test.remove();
        assertEquals(2, test.getCurrentSize());
    }

    @Test
    void testGetFrequencyOf() {
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        test.add("first");
        test.add("second");
        test.add("second");
        test.add("third");
        assertEquals(2, test.getFrequencyOf("second"));
        assertEquals(1, test.getFrequencyOf("first"));
    }

    @Test
    void testIntersection() {

    }

    @Test
    void testIsEmpty() {
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        assertEquals(true, test.isEmpty());
        test.add("something");
        assertEquals(false, test.isEmpty());
    }

    @Test
    void testIsFull() {
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        test.add("first");
        test.add("second");
        test.add("third");
        assertEquals(false, test.isFull());
        test.add("fourth");
        assertEquals(true, test.isFull());
    }

    @Test
    void testRemove() {
        //test item bag
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        test.add("first");
        test.add("second");
        test.add("third");

        assertEquals("third", test.remove());

        String[] stringArr = {"first", "second"};
        assertEquals(true, Arrays.equals(stringArr, test.toArray()));

        //test null bag
        ResizableArrayBag<String> test2 = new ResizableArrayBag<String>(4);
        assertEquals(null, test2.remove());
        assertEquals(true, Arrays.equals(test2.toArray(), new String[0]));
    }

    @Test
    void testRemoveSpecific() {
        //test if existing in bag
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        test.add("first");
        test.add("second");
        test.add("third");

        assertEquals(true, test.remove("second"));
        String[] stringArr = {"first", "third"};
        System.out.println(Arrays.toString(test.toArray()));
        assertEquals(true, Arrays.equals(test.toArray(), stringArr));

        //test if not existing in bag
        assertEquals(false, test.remove("notinthebag"));
        assertEquals(true, Arrays.equals(test.toArray(), stringArr));
    }

    @Test
    void testToArray() {
        ResizableArrayBag<String> test = new ResizableArrayBag<String>(4);
        test.add("first");
        test.add("second");
        test.add("third");

        String[] stringArr = {"first", "second", "third"};
        assertEquals(true, Arrays.equals(stringArr, test.toArray()));
    }

    @Test
    void testUnion() {

    }
}
