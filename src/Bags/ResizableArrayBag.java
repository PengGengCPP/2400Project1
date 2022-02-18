package Bags;
import java.util.Arrays;

/**
 * Resizable Array Bag implementation.
 * Creates a resizable array bag that contains set operations of intersection, union, and difference.
 * Interacting with this object uses the bag interface.
 */
public class ResizableArrayBag<T> implements BagInterface<T> {

	private T[] bag; //array that will store the references for objects in the bag
	private int numItems = 0;
	private final int MAX_CAPACITY = 100000;
	private final int DEFAULT_SIZE = 1;
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

	private void checkIntegrity() {
		if (!integrityOk) {
			throw new SecurityException("ResizableArrayBag is corrupt.");
		}
	}

	private void doubleArrayCapacity() {
		checkCapacity(this.bag.length*2);
		this.bag = Arrays.copyOf(this.bag, this.bag.length*2);
	}

	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Size of created bag exceeds the maximum allowed size of the bag of " + MAX_CAPACITY);
		}
	}

	@Override
	public ResizableArrayBag<T> union(ResizableArrayBag<T> bag2) {
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

	@Override
	public LinkedBag<T> union(LinkedBag<T> bag2) {
		return null;
	}

	@Override
	public ResizableArrayBag<T> intersection(ResizableArrayBag<T> bag2) {
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

	@Override
	public LinkedBag<T> intersection(LinkedBag<T> bag2) {
		return null;
	}

	@Override
	public ResizableArrayBag<T> difference(ResizableArrayBag<T> bag2) {
		ResizableArrayBag<T> ret = new ResizableArrayBag<T>();

		//copy current bag to return bag
		T[] tempArray = this.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			ret.add(tempArray[i]);
		}

		//replace tempArray data with bag2 data and remove bag2 data from current bag
		tempArray = bag2.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			ret.remove(tempArray[i]);
		}

		return ret;
	}

	@Override
	public LinkedBag<T> difference(LinkedBag<T> bag2) {
		return null;
	}

	@Override
	public int getCurrentSize() {
		return this.numItems;
	}

	@Override
	public boolean isEmpty() {
		if (this.getCurrentSize() == 0) {
			return true;
		}
		return false;
	}

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

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] ret = (T[])new Object[numItems];

		for (int i = 0; i < ret.length; i++) {
			ret[i] = this.bag[i];
		}

		return ret;
	}

	@Override
	public boolean isFull() {
		if (this.bag.length == this.numItems) {
			return true;
		}
		return false;
	}
    
}
