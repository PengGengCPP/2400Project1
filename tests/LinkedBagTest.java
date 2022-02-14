package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bags.LinkedBag;

public class LinkedBagTest {
    private LinkedBag<String> test;
    private LinkedBag<String> empty;

    @BeforeEach
    void init() {
        test = new LinkedBag<String>();
        test.add("first");
        test.add("second");
        test.add("third");

        empty = new LinkedBag<String>();
    }
    
    @Test
    void testAdd() {
        assertEquals(false, empty.contains("first"));
        assertEquals(true, empty.add("first"));
        assertEquals(true, empty.contains("first"));

    }

    @Test
    void testClear() {
        test.clear();
        assertEquals(true, Arrays.equals(test.toArray(), empty.toArray()));
    }

    @Test
    void testContains() {
        assertEquals(true, test.contains("first"));
        assertEquals(false, test.contains("notinthtebag"));
        assertEquals(false, empty.contains("second"));
    }

    @Test
    void testDifference() {

    }

    @Test
    void testGetCurrentSize() {
        assertEquals(3, test.getCurrentSize());
        test.add("something");
        assertEquals(4, test.getCurrentSize());
        assertEquals(0, empty.getCurrentSize());
    }

    @Test
    void testGetFrequencyOf() {
        assertEquals(1, test.getFrequencyOf("first"));
        test.add("first");
        assertEquals(2, test.getFrequencyOf("first"));
        assertEquals(0, test.getFrequencyOf("notinthebag"));
        
    }

    @Test
    void testIntersection() {

    }

    @Test
    void testIsEmpty() {
        assertEquals(true, empty.isEmpty());
        assertEquals(false, test.isEmpty());
    }

    @Test
    void testIsFull() {
        //should always be not full because memory is dynamically located for the bag and it can be expanded - there is no limit unless...
        //todo add an upper limit to the bag?
        assertEquals(false, empty.isFull());
        assertEquals(false, test.isFull());
    }

    @Test
    void testRemove() {
        assertEquals("third", test.remove());
        assertEquals(true, Arrays.equals(new String[]{"second", "first"}, test.toArray()));
        assertEquals(null, empty.remove());
    }

    @Test
    void testRemoveSpecific() {
        assertEquals(false, test.remove("somethingelse"));
        assertEquals(true, test.remove("second"));
        assertEquals(true, Arrays.equals(new String[]{"third", "first"}, test.toArray()));
    }

    @Test
    void testToArray() {
        //items retrieved in reverse order to how they were put in
        String[] stringArr = {"third", "second", "first"};
        System.out.println(Arrays.toString(test.toArray()));
        assertEquals(true, Arrays.equals(stringArr, test.toArray()));
    }

    @Test
    void testUnion() {

    }
}
