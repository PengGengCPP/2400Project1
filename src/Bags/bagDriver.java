package Bags;

import java.util.Arrays;
import java.util.InputMismatchException;

public class bagDriver {

    public static void printArray(Object[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args) {
        //creating a ResizableArrayBag and LinkedBag of Strings
        ResizableArrayBag<String> bag1 = new ResizableArrayBag<>();
        LinkedBag<String> bag2 = new LinkedBag<>();

        //adding items to bag 1
        bag1.add("one");
        bag1.add("two");
        bag1.add("two");
        bag1.add("three");
        printArray(bag1.toArray());

        //adding items to bag 2
        bag2.add("one");
        bag2.add("two");
        bag2.add("three");
        bag2.add("three");
        printArray(bag2.toArray());

        //adding and removing items from bag 2
        bag2.add("oops");
        printArray(bag2.toArray());
        bag2.remove("oops");
        printArray(bag2.toArray());
        
        //copying bag 1
        ResizableArrayBag<String> bag3 = bag1.intersection(bag1);
        printArray(bag3.toArray());

        //removing items in bag3 and then clearing it
        bag3.remove();
        bag3.remove("three");
        bag3.clear();

        
        //bag set operations will throw InputMismatchException if the input is null.
        ResizableArrayBag<String> b1 = new ResizableArrayBag<String>();
        ResizableArrayBag<String> b2 = null;
        try {
            ResizableArrayBag<String> b3 = b1.union(b2);
            printArray(b3.toArray());
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        

    }
}
