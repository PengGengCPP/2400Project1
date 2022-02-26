package Bags;

import java.util.Arrays;
import java.util.InputMismatchException;

public class bagDriver {

    
    /** 
     * @param arr
     */
    public static void printArray(Object[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    /** 
     * @param args
     */
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
        
        //copying bag 1 (note: you can still modify the original object through the copied bag)
        ResizableArrayBag<String> bag3 = bag1.intersection(bag1);
        printArray(bag3.toArray());

        //removing items in bag3 and then clearing it
        bag3.remove();
        bag3.remove("three");
        bag3.clear();

        //converting linked bag to array bag and array bag to linked bag
        LinkedBag<String> test1 = bag1.toLinkedBag();
        printArray(test1.toArray());
        ResizableArrayBag<String> test2 = bag2.toResizableArrayBag();
        printArray(test2.toArray());

        //set operation example
        ResizableArrayBag<String> union = bag1.union(bag2);
        printArray(union.toArray());

        ResizableArrayBag<String> intersection = bag1.intersection(bag2);
        printArray(intersection.toArray());

        ResizableArrayBag<String> difference = bag1.difference(bag2);
        printArray(difference.toArray());
        
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
