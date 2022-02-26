package Bags;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Resizable Array Bag implementation.
 * Creates a resizable array bag that contains set operations of intersection, union, and difference.
 * Interacting with this object uses the bag interface.
 */
public class ResizableArrayBag<T> implements BagInterface<T> {
	
	private T[] bag; //array that will store the references for objects in the bag
	private int numItems = 0;
	private final int MAX_CAPACITY = 100000;
	private final int DEFAULT_SIZE = 25;
	private boolean integrityOk = false;
	//constructors

	public ResizableArrayBag() {
		this.integrityOk = true;
		checkIntegrity();
		@SuppressWarnings("unchecked") //ok bc items will have to be of type T
		T[] tempBag = (T[]) new Object[DEFAULT_SIZE];
		this.bag = tempBag;
	}

	public ResizableArrayBag(int size) {
		checkCapacity(size);
		if (size <= MAX_CAPACITY) {
			this.integrityOk= true;
		}
		checkIntegrity();
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[]) new Object[size];
		this.bag = tempBag;
	}

	//methods

	/**
	 * Checks the integrity of the bag.
	 * @throws SecurityException if the bag is corrupt.
	 */
	private void checkIntegrity() {
		if (!integrityOk) {
			throw new SecurityException("ResizableArrayBag is corrupt.");
		}
	}

	/**
	 * Doubles the size of the resizable array.
	 * copies the items of the current array to an array of twice the size.
	 */
	private void doubleArrayCapacity() {
		checkCapacity(this.bag.length*2);
		this.bag = Arrays.copyOf(this.bag, this.bag.length*2);
	}

	/**
	 * Checks Capacity of Resizable Array.
	 * @param capacity
	 * @throws IllegalStateException if the size of the bag is larger than allowed.
	 */
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Size of created bag exceeds the maximum allowed size of the bag of " + MAX_CAPACITY);
		}
	}

	
	/** 
	 * @param bag2
	 * @return ResizableArrayBag<T>
	 */
	@Override
	public ResizableArrayBag<T> union(BagInterface<T> bag2) {
		if (bag2 == null) {
			throw new InputMismatchException("input bag must not be null.");
		}
		
		ResizableArrayBag<T> ret = new ResizableArrayBag<T>();

		T[] tempArray = this.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			ret.add(tempArray[i]);
		}
		tempArray = bag2.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			ret.add(tempArray[i]);
		}

		return ret;
	}

	
	/** 
	 * @param bag2
	 * @return ResizableArrayBag<T>
	 */
	@Override
	public ResizableArrayBag<T> intersection(BagInterface<T> bag2) {
		if (bag2 == null) {
			throw new InputMismatchException("input bag must not be null.");
		}

		ResizableArrayBag<T> ret = new ResizableArrayBag<T>();

		//copy of bag2
		ResizableArrayBag<T> bag2Copy = new ResizableArrayBag<>();
		T[] tempArray = bag2.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			bag2Copy.add(tempArray[i]);
		}
		
		//iterating through current bag and determining matches
		tempArray = this.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			if (bag2Copy.contains(tempArray[i])) {
				ret.add(tempArray[i]);
				bag2Copy.remove(tempArray[i]);
			}
		}

		return ret;
	}

	
	/** 
	 * @param bag2
	 * @return ResizableArrayBag<T>
	 */
	@Override
	public ResizableArrayBag<T> difference(BagInterface<T> bag2) {
		if (bag2 == null) {
			throw new InputMismatchException("input bag must not be null.");
		}
		
		ResizableArrayBag<T> ret = new ResizableArrayBag<T>();

		//copy current bag to return bag
		T[] tempArray = this.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			ret.add(tempArray[i]);
		}

		//replace tempArray data with bag2 data and remove bag2 data from return bag
		tempArray = bag2.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			ret.remove(tempArray[i]);
		}

		return ret;
	}

	
	/** 
	 * @return int
	 */
	@Override
	public int getCurrentSize() {
		return this.numItems;
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean isEmpty() {
		if (this.getCurrentSize() == 0) {
			return true;
		}
		return false;
	}

	
	/** 
	 * @param newEntry
	 * @return boolean
	 */
	@Override
	public boolean add(T newEntry) {
		checkIntegrity();
		
		if (isFull()) {
			doubleArrayCapacity();
		}

		if(isFull()) {
			return false;
		} else {
			bag[numItems] = newEntry;
			numItems++;
			return true;
		}
	}

	/**
	 * Removes the most recent item that was put in the bag.
	 * @return the item that was removed
	 */
	@Override
	public T remove() {
		checkIntegrity();
		T ret = null;
		if(!isEmpty()) {
			ret = this.bag[numItems - 1];
			this.bag[numItems - 1] = null;
			this.numItems--;
		}
		return ret;
	}

	
	/** 
	 * @param entry
	 * @return boolean
	 */
	@Override
	public boolean remove(T entry) {
		checkIntegrity();

		if(!isEmpty()) {
			for (int i = 0; i < this.numItems; i++) {
				if (this.bag[i].equals(entry)) {
					this.bag[i] = this.bag[numItems - 1];
					this.bag[numItems - 1] = null;
					numItems--;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	}

	
	/** 
	 * @param entry
	 * @return Integer
	 */
	@Override
	public Integer getFrequencyOf(T entry) {
		int count = 0;
		for (int i = 0; i < numItems; i++) {
			if (entry.equals(this.bag[i])) {
				count++;
			}
		}
		return count;
	}

	
	/** 
	 * @param entry
	 * @return boolean
	 */
	@Override
	public boolean contains(T entry) {
		checkIntegrity();
		for (int i = 0; i < numItems; i++) {
			if (entry.equals(this.bag[i])) {
				return true;
			}
		}
		return false;
	}

	
	/** 
	 * @return T[]
	 */
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] ret = (T[])new Object[numItems];

		for (int i = 0; i < ret.length; i++) {
			ret[i] = this.bag[i];
		}

		return ret;
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean isFull() {
		if (this.bag.length == this.numItems) {
			return true;
		}
		return false;
	}

	/**
	 * Converts the ResizableArrayBag to a LinkedBag.
	 * @return LinkedBag with the contents of the bag which this method was called on.
	 */
	public LinkedBag<T> toLinkedBag() {
		LinkedBag<T> ret = new LinkedBag<T>();

		T[] tempArray = this.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			ret.add(tempArray[i]);
		}

		return ret;
	}
}
