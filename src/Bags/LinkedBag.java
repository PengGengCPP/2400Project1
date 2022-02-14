package Bags;

public class LinkedBag<T> implements BagInterface<T>, BagSetOperationInterface<LinkedBag<T>> {
	private Node firstNode;
	private int numEntries;
	
	//todo implement upper limit to the bag

	public LinkedBag() {
		this.firstNode = null;
		this.numEntries = 0;
	}

	@Override
	public LinkedBag<T> union(LinkedBag<T> bag2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedBag<T> intersection(LinkedBag<T> bag2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedBag<T> difference(LinkedBag<T> bag2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCurrentSize() {
		return this.numEntries;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		if (this.numEntries == 0) {
			return true;
		}
		return false;
	}

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
