package Bags;

public class bagDriver {
    public static void main(String[] args) {
        ResizableArrayBag<String> b1 = new ResizableArrayBag<String>();
        ResizableArrayBag<String> b2 = null;
        ResizableArrayBag<String> b3 = b1.union(b2);

    }
}
