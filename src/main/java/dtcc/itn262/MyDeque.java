package dtcc.itn262;

// MyDeque.java
public class MyDeque<E> implements Deque<E> {

	private class Node {
		E data;
		Node prev;
		Node next;

		public Node(E data) {
			this.data = data;
		}
	}

	private Node head; // Reference to the first node
	private Node tail; // Reference to the last node
	private int size;  // Number of elements in the deque

	public MyDeque() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean offerFirst(E e) {
		if (e == null) return false;
		Node newNode = new Node(e);
		if (head == null) { // Deque is empty
			head = tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean offerLast(E e) {
		if (e == null) return false;
		Node newNode = new Node(e);
		if (tail == null) { // Deque is empty
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
		return true;
	}

	@Override
	public E pollFirst() {
		if (head == null) return null; // Deque is empty
		E data = head.data;
		if (head == tail) { // Only one element
			head = tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		size--;
		return data;
	}

	@Override
	public E pollLast() {
		if (tail == null) return null; // Deque is empty
		E data = tail.data;
		if (head == tail) { // Only one element
			head = tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return data;
	}

	@Override
	public E peekFirst() {
		return (head != null) ? head.data : null;
	}

	@Override
	public E peekLast() {
		return (tail != null) ? tail.data : null;
	}

	@Override
	public int size() {
		return size;
	}

	// Additional method to get all elements as a comma-separated string
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = head;
		while (current != null) {
			sb.append(current.data);
			if (current.next != null) sb.append(", ");
			current = current.next;
		}
		return sb.toString();
	}
}
