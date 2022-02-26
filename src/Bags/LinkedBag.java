package Bags;

import java.util.InputMismatchException;

/**
 * Linked Bag implementation.
 * Creates a linked bag that contains set operations of intersection, union, and difference.
 * Interacting with this object uses the bag interface.
 */
public class LinkedBag<T> implements BagInterface<T> {
	private Node firstNode;
	private int numEntries;

	public LinkedBag() {
		this.firstNode = null;
		this.numEntries = 0;
	}

	
	/** 
	 * @param bag2
	 * @return LinkedBag<T>
	 */
	@Override
	public LinkedBag<T> union(BagInterface<T> bag2) {
		if (bag2 == null) {
			throw new InputMismatchException("input bag must not be null.");
		}
		
		LinkedBag<T> ret = new LinkedBag<T>();

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
	 * @return LinkedBag<T>
	 */
	@Override
	public LinkedBag<T> intersection(BagInterface<T> bag2) {
		if (bag2 == null) {
			throw new InputMismatchException("input bag must not be null.");
		}

		LinkedBag<T> ret = new LinkedBag<T>();

		//copy of bag2
		LinkedBag<T> bag2Copy = new LinkedBag<>();
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
	 * @return LinkedBag<T>
	 */
	@Override
	public LinkedBag<T> difference(BagInterface<T> bag2) {
		if (bag2 == null) {
			throw new InputMismatchException("input bag must not be null.");
		}
		
		LinkedBag<T> ret = new LinkedBag<T>();

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

	
	/** 
	 * @return int
	 */
	@Override
	public int getCurrentSize() {
		return this.numEntries;
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean isFull() {
		//will always return false because linked bag is dynamically resized. You can always add.
		return false;
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean isEmpty() {
		if (this.numEntries == 0) {
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
		try {
			if (numEntries == 0) {
				Node tempNode = new Node(newEntry, null);
				this.firstNode = tempNode;
				this.numEntries++;
				return true;
			} else {
				firstNode = new Node(newEntry, firstNode);
				this.numEntries++;
				return true;
			}
		} catch (Exception e) {
			return false; // idk lol
		}
	}

	/**
	 * Removes the first item that was put into the bag.
	 * @return the item removed.
	 */
	@Override
	public T remove() {
		//removing the first item in the chain
		T ret = null;
		if (this.firstNode != null) {
			ret = this.firstNode.getData();
			this.firstNode = firstNode.getNextNode();
			this.numEntries--;
		}

		return ret;
	}

	
	/** 
	 * @param entry
	 * @return boolean
	 */
	@Override
	public boolean remove(T entry) {
		//replace given node with first node, and then remove first node.
		Node removal = getReferenceTo(entry);

		if (removal != null) {
			removal.setData(this.firstNode.getData());
			remove();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the Node at which the entry is located in.
	 * @param entry
	 * @return Node
	 */
	private Node getReferenceTo(T entry) {
		boolean found = false;
		Node currentNode = this.firstNode;

		while (!found && (currentNode != null)) {
			if (entry.equals(currentNode.getData())) {
				return currentNode;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return null;
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
		Node currentNode = this.firstNode;
		int count = 0;
		while (currentNode != null) {
			if (entry.equals(currentNode.getData())) {
				count++;
			}
			currentNode = currentNode.getNextNode();
		}
		return count;
	}

	
	/** 
	 * @param entry
	 * @return boolean
	 */
	@Override
	public boolean contains(T entry) {
		Node currentNode = this.firstNode;

		while (currentNode != null) {
			if (entry.equals(currentNode.getData())) {
				return true;
			}
			currentNode = currentNode.getNextNode();
		}
		return false;
	}

	
	/** 
	 * @return T[]
	 */
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] ret = (T[]) new Object[this.numEntries];

		Node currentNode = this.firstNode;
		int index = 0;

		while (this.numEntries != 0 && currentNode != null) {
			ret[index] = currentNode.getData();
			index++;
			currentNode = currentNode.getNextNode();
		}
		return ret;
	}

	/**
	 * Converts the LinkedBag to a Resizable Array Bag.
	 * @return ResizableArrayBag with the contents of the bag which this method was called on.
	 */
	public ResizableArrayBag<T> toResizableArrayBag() {
		ResizableArrayBag<T> ret = new ResizableArrayBag<T>();

		T[] tempArray = this.toArray();
		for (int i = 0; i < tempArray.length; i++) {
			ret.add(tempArray[i]);
		}

		return ret;
	}
    
	/**
	 * Class that defines the Node object used for the implementation of the linked bag.
	 */
	private class Node {
		//accessible within the linkedbag class, not accessible to the client.
		private T data;
		private Node nextNode;

		@SuppressWarnings("unused")
		Node() {
			this.data = null;
			this.nextNode = null;
		}

		@SuppressWarnings("unused")
		Node(T item) {
			this.data = item;
			this.nextNode = null;
		}

		Node (T item, Node nextNode) {
			this.data = item;
			this.nextNode = nextNode;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNextNode() {
			return nextNode;
		}

		@SuppressWarnings("unused")
		public void setSecondNode(Node secondNode) {
			this.nextNode = secondNode;
		}

	}
}
