import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T>, BagSetOperationInterface<ResizableArrayBag<T>> {

	private T[] bag; //array that will store the references for objects in the bag
	private int numItems = 0;
	private final int MAX_CAPACITY = 100000;
	private final int DEFAULT_SIZE = 10;
	private boolean integrityOk = false;
	//constructors

	ResizableArrayBag() {
		@SuppressWarnings("unchecked") //ok bc items will have to be of type T
		T[] tempBag = (T[]) new Object[DEFAULT_SIZE];
		this.bag = tempBag;
	}

	ResizableArrayBag(int size) {
		checkCapacity(size);
		if (size <= MAX_CAPACITY) {
			integrityOk= true;
		}
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

	public 

	@Override
	public ResizableArrayBag<T> union(ResizableArrayBag<T> bag2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResizableArrayBag<T> intersection(ResizableArrayBag<T> bag2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResizableArrayBag<T> difference(ResizableArrayBag<T> bag2) {
		// TODO Auto-generated method stub
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

		
	}

	@Override
	public boolean remove(T entry) {
		// TODO Auto-generated method stu
		checkIntegrity();

		return false;
	}

	@Override
	public void clear() {
		@SuppressWarnings("unchecked")
		this.bag = (T[]) new Object[DEFAULT_SIZE];
		this.numItems = 0;
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
