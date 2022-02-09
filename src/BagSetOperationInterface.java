public interface BagSetOperationInterface<T> {
    /**
     * Returns an instance of the union 
     * @param bag2 the second bag for the union
     * @return bag containing the contents of the union between the bag which the method was called on and the bag that is passed as a parameter.
     */
    public T union(T bag2);
    /**
     * Returns instance of the intersection
     * @param bag2 second bag for intersection
     * @return bag with elements that intersect between the bag which the method was called on and the bag passed as a parameter
     */
    public T intersection(T bag2);
    /**
     * Returns instance of the difference
     * @param bag2
     * @return bag with the difference between the bag which the method was called on and the bag passed as a parameter
     */
    public T difference(T bag2);
    
}
