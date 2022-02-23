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
     * Returns an instance of the union 
     * @param bag2 the second bag for the union
     * @return bag containing the contents of the union between the bag which the method was called on and the bag that is passed as a parameter.
     */
    public BagInterface<T> union(BagInterface<T> bag2);
    /**
     * Returns instance of the intersection
     * @param bag2 second bag for intersection
     * @return bag with elements that intersect between the bag which the method was called on and the bag passed as a parameter
     */
    public BagInterface<T> intersection(BagInterface<T> bag2);
    /**
     * Returns instance of the difference
     * @param bag2
     * @return bag with the difference between the bag which the method was called on and the bag passed as a parameter
     */
    public BagInterface<T> difference(BagInterface<T> bag2);
}
