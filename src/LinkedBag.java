public class LinkedBag<T> implements BagInterface<T>, BagSetOperationInterface<LinkedBag<T>> {
	private Node firstNode;
	private int numEntries;
	
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(T newEntry) {
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
		return false; // idk lol
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(T entry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getFrequencyOf(T entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(T entry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
    
	private class Node {
		//accessible within the linkedbag class, not accessible to the client.
		//TODO inner class for node
		private T data;
		private Node nextNode;

		Node() {
			this.data = null;
			this.nextNode = null;
		}

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

		public void setSecondNode(Node secondNode) {
			this.nextNode = secondNode;
		}

	}
}
