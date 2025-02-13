package dtcc.itn262;

public class Node<E> {
	E data;
	Node prev;
	Node next;

	public Node(E data) {
		this.data = data;
	}
}
