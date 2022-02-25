package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bags.LinkedBag;

public class LinkedBagTest {
    private LinkedBag<String> test;
    private LinkedBag<String> empty;

    private LinkedBag<String> setTest1;
    private LinkedBag<String> setTest2;
    private String[] setTest1Array;
    private String[] setTest2Array;
    private Set<String> setTest1Hash;
    private Set<String> setTest2Hash;

    @BeforeEach
    void init() {
        test = new LinkedBag<String>();
        test.add("first");
        test.add("second");
        test.add("third");

        empty = new LinkedBag<String>();

        setTest1 = new LinkedBag<String>();
        setTest1.add("one");
        setTest1.add("one");
        setTest1.add("two");
        setTest1.add("three");
        setTest1.add("four");
        setTest1.add("four");
        
        setTest2 = new LinkedBag<String>();
        setTest2.add("one");
        setTest2.add("three");
        setTest2.add("four");
        setTest2.add("four");
        setTest2.add("five");

        Object[] temporary1 = setTest1.toArray();
        setTest1Array = Arrays.copyOf(temporary1, temporary1.length, String[].class);

        Object[] temporary2 = setTest2.toArray();
        setTest2Array = Arrays.copyOf(temporary2, temporary2.length, String[].class);

        setTest1Hash = new HashSet<>();
        for (int i = 0; i < setTest1Array.length; i++) {
            setTest1Hash.add(setTest1Array[i]);
        }
        setTest2Hash = new HashSet<>();
        for (int i = 0; i < setTest2Array.length; i++) {
            setTest2Hash.add(setTest2Array[i]);
        }
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
        LinkedBag<String> differenceBag = setTest1.difference(setTest2);
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
        LinkedBag<String> intersectionBag = setTest1.intersection(setTest2);
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
        Set<String> union = new HashSet<String>();

        union.addAll(setTest1Hash);
        union.addAll(setTest2Hash);

        LinkedBag<String> unionBag = setTest1.union(setTest2);
        Object[] temp = unionBag.toArray();
        Set<String> unionArrayHash = new HashSet<String>();

        for (int i = 0; i < temp.length; i++) {
            unionArrayHash.add((String) temp[i]);
        }

        assertEquals(true, unionArrayHash.equals(union));
        System.out.println(Arrays.toString(unionBag.toArray()));
        System.out.println(unionBag.getCurrentSize());
        assertEquals(11, unionBag.getCurrentSize());
        assertEquals(3, unionBag.getFrequencyOf("one"));
        assertEquals(1, unionBag.getFrequencyOf("two"));
        assertEquals(2, unionBag.getFrequencyOf("three"));
        assertEquals(4, unionBag.getFrequencyOf("four"));
        assertEquals(1, unionBag.getFrequencyOf("five"));

        Object[] temporary = unionBag.toArray();
        String[] unionArray = Arrays.copyOf(temporary, temporary.length, String[].class);
        Set<String> unionHash = new HashSet<>();
        for (String var : unionArray) {
            unionHash.add(var);
        }
        assertEquals(true, unionHash.equals(unionArrayHash));

        //testing other way around
        unionBag = setTest2.union(setTest1);
        assertEquals(11, unionBag.getCurrentSize());
        assertEquals(3, unionBag.getFrequencyOf("one"));
        assertEquals(1, unionBag.getFrequencyOf("two"));
        assertEquals(2, unionBag.getFrequencyOf("three"));
        assertEquals(4, unionBag.getFrequencyOf("four"));
        assertEquals(1, unionBag.getFrequencyOf("five"));
        assertEquals(true, unionHash.equals(unionArrayHash));
    }
}
