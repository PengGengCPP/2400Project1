package Bags;
public interface BagInterface<T> {
    
    /**
     * Gets the current size of the bag.
     * @return size of the bag as an integer
     */
    public int getCurrentSize();

    /**
     * Returns true if the bag is full.
     * @return boolean, true if bag is full.
     */
    public boolean isFull();

    /**
     * 
     * Returns true if the bag is empty.
     * @return boolean, true if the bag is empty.
     */
    public boolean isEmpty();
    /**
     * Adds an object to the bag.
     * @param newEntry the object to be added
     * @return boolean, true if the object was successfully added.
     */
    public boolean add(T newEntry);
    /**
     * Removes the first object from the bag.
     * @return the object that was removed.
     */
    public T remove();
    /**
     * Removes one instance of the specified object from the bag.
     * @param entry the object to be removed
     * @return the object that was removed.
     */
    public boolean remove(T entry);
     /**
     * Empties the bag of all entries.
     */
    public void clear();
    /**
     * Returns the number of instances of the object in the bag.
     * @param entry the object to look for
     * @return the number of occurences of the object
     */
    public Integer getFrequencyOf(T entry);
    /**
     * Checks if the specified object is contained in the bag.
     * @param entry the object to be looked for
     * @return boolean, true if the object is contained in the bag.
     */
    public boolean contains(T entry);
    /**
     * Returns an array of the items in the bag.
     * @return array of references or primitive data types of the items contained in the bag.
     */
    public T[] toArray();
    /**
     * Combines the contents of two bags into a new bag.
     * @param bag2 The bag whose contents will be combined with those of the bag that called this method
     * @return A new bag containing the contents of the two previous bags
     */
    public ResizableArrayBag<T> union(ResizableArrayBag<T> bag2);
    /**
     * Finds entries occurring in two bags simultaneously.
     * @param bag2 The bag whose contents will be searched for entries occurring in both bags
     * @return A new bag with entries found to occur in both of the previous bags
     */
    public ResizableArrayBag<T> intersection(ResizableArrayBag<T> bag2);
    /**
     * Finds entries occurring in the first bag that do not occur in the second bag
     * @param bag2 The bag whose contents will be searched for entries occurring in both bags
     * @return A new bag containing the entries found in the first bag but not bag2
     */
    public ResizableArrayBag<T> difference(ResizableArrayBag<T> bag2);
    /**
     * Combines the contents of two bags into a new bag.
     * @param bag2 The bag whose contents will be combined with those of the bag that called this method
     * @return A new bag containing the contents of the two previous bags
     */
    public LinkedBag<T> union(LinkedBag<T> bag2);
    /**
     * Finds entries occurring in two bags simultaneously.
     * @param bag2 The bag whose contents will be searched for entries occurring in both bags
     * @return A new bag with entries found to occur in both of the previous bags
     */
    public LinkedBag<T> intersection(LinkedBag<T> bag2);
    /**
     * Finds entries occurring in the first bag that do not occur in the second bag
     * @param bag2 The bag whose contents will be searched for entries occurring in both bags
     * @return A new bag containing the entries found in the first bag but not bag2
     */
    public LinkedBag<T> difference(LinkedBag<T> bag2);
}
