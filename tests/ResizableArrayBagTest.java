package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bags.ResizableArrayBag;

public class ResizableArrayBagTest {
    private ResizableArrayBag<String> setTest1;
    private ResizableArrayBag<String> setTest2;
    private ResizableArrayBag<String> empty;
    
    @BeforeEach
    void init() {
        setTest1 = new ResizableArrayBag<String>();
        setTest1.add("one");
        setTest1.add("one");
        setTest1.add("two");
        setTest1.add("three");
        setTest1.add("four");
        setTest1.add("four");

        setTest2 = new ResizableArrayBag<>();
        setTest2.add("one");
        setTest2.add("three");
        setTest2.add("four");
        setTest2.add("four");
        setTest2.add("five");

        empty = new ResizableArrayBag<>();
    }
    
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
        ResizableArrayBag<String> differenceBag = setTest1.difference(setTest2);
        System.out.println(Arrays.toString(differenceBag.toArray()));
        assertEquals(1, differenceBag.getFrequencyOf("one"));
        assertEquals(1, differenceBag.getFrequencyOf("two"));
        assertEquals(0, differenceBag.getFrequencyOf("three"));
        assertEquals(0, differenceBag.getFrequencyOf("four"));
        assertEquals(0, differenceBag.getFrequencyOf("five"));

        //testing the other way around
        differenceBag = setTest2.difference(setTest1);
        System.out.println(Arrays.toString(differenceBag.toArray()));
        assertEquals(0, differenceBag.getFrequencyOf("one"));
        assertEquals(0, differenceBag.getFrequencyOf("two"));
        assertEquals(0, differenceBag.getFrequencyOf("three"));
        assertEquals(0, differenceBag.getFrequencyOf("four"));
        assertEquals(1, differenceBag.getFrequencyOf("five"));

        
        //testing if bag1 is empty and bag2 has objects

        differenceBag = empty.difference(setTest2);
        System.out.println(Arrays.toString(differenceBag.toArray()));
        assertEquals(0, differenceBag.getFrequencyOf("one"));
        assertEquals(0, differenceBag.getFrequencyOf("two"));
        assertEquals(0, differenceBag.getFrequencyOf("three"));
        assertEquals(0, differenceBag.getFrequencyOf("four"));
        assertEquals(0, differenceBag.getFrequencyOf("five"));

        //testing if bag1 has objects and bag2 is empty

        differenceBag = setTest1.difference(empty);
        System.out.println(Arrays.toString(differenceBag.toArray()));
        assertEquals(2, differenceBag.getFrequencyOf("one"));
        assertEquals(1, differenceBag.getFrequencyOf("two"));
        assertEquals(1, differenceBag.getFrequencyOf("three"));
        assertEquals(2, differenceBag.getFrequencyOf("four"));
        assertEquals(0, differenceBag.getFrequencyOf("five"));

        //testing if both bags are empty

        differenceBag = empty.difference(empty);
        System.out.println(Arrays.toString(differenceBag.toArray()));
        assertEquals(0, differenceBag.getFrequencyOf("one"));
        assertEquals(0, differenceBag.getFrequencyOf("two"));
        assertEquals(0, differenceBag.getFrequencyOf("three"));
        assertEquals(0, differenceBag.getFrequencyOf("four"));
        assertEquals(0, differenceBag.getFrequencyOf("five"));

        //bag1 and bag2 are same but not empty
        
        differenceBag = setTest1.difference(setTest1);
        System.out.println(Arrays.toString(differenceBag.toArray()));
        assertEquals(0, differenceBag.getFrequencyOf("one"));
        assertEquals(0, differenceBag.getFrequencyOf("two"));
        assertEquals(0, differenceBag.getFrequencyOf("three"));
        assertEquals(0, differenceBag.getFrequencyOf("four"));
        assertEquals(0, differenceBag.getFrequencyOf("five"));

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
        ResizableArrayBag<String> intersectionBag = setTest1.intersection(setTest2);
        System.out.println(Arrays.toString(intersectionBag.toArray()));
        assertEquals(1, intersectionBag.getFrequencyOf("one"));
        assertEquals(0, intersectionBag.getFrequencyOf("two"));
        assertEquals(1, intersectionBag.getFrequencyOf("three"));
        assertEquals(2, intersectionBag.getFrequencyOf("four"));
        assertEquals(0, intersectionBag.getFrequencyOf("five"));

        //testing the reverse
        intersectionBag = setTest2.intersection(setTest1);
        System.out.println(Arrays.toString(intersectionBag.toArray()));
        assertEquals(1, intersectionBag.getFrequencyOf("one"));
        assertEquals(0, intersectionBag.getFrequencyOf("two"));
        assertEquals(1, intersectionBag.getFrequencyOf("three"));
        assertEquals(2, intersectionBag.getFrequencyOf("four"));
        assertEquals(0, intersectionBag.getFrequencyOf("five"));
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
        ResizableArrayBag<String> unionBag = setTest1.union(setTest2);

        System.out.println(Arrays.toString(unionBag.toArray()));
        System.out.println(unionBag.getCurrentSize());
        assertEquals(11, unionBag.getCurrentSize());
        assertEquals(3, unionBag.getFrequencyOf("one"));
        assertEquals(1, unionBag.getFrequencyOf("two"));
        assertEquals(2, unionBag.getFrequencyOf("three"));
        assertEquals(4, unionBag.getFrequencyOf("four"));
        assertEquals(1, unionBag.getFrequencyOf("five"));

        //test other way around 
        unionBag = setTest2.union(setTest1);
        assertEquals(11, unionBag.getCurrentSize());
        assertEquals(3, unionBag.getFrequencyOf("one"));
        assertEquals(1, unionBag.getFrequencyOf("two"));
        assertEquals(2, unionBag.getFrequencyOf("three"));
        assertEquals(4, unionBag.getFrequencyOf("four"));
        assertEquals(1, unionBag.getFrequencyOf("five"));
    }
}
