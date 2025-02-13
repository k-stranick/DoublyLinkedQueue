package dtcc.itn262;

/*
Based on java.util.Deque interface
https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
 */
public interface Deque<E> {
	/**
	 * Inserts the specified element at the front of this deque unless it would
	 * violate capacity restrictions.  When using a capacity-restricted deque,
	 *
	 * @param e the element to add
	 * @return {@code true} if the element was added to this deque, else
	 *         {@code false}
	 */
	boolean offerFirst(E e);

	/**
	 * Inserts the specified element at the end of this deque unless it would
	 * violate capacity restrictions.  When using a capacity-restricted deque,
	 *
	 * @param e the element to add
	 * @return {@code true} if the element was added to this deque, else
	 *         {@code false}
	 */
	boolean offerLast(E e);

	/**
	 * Retrieves and removes the first element of this deque,
	 * or returns {@code null} if this deque is empty.
	 *
	 * @return the head of this deque, or {@code null} if this deque is empty
	 */
	E pollFirst();

	/**
	 * Retrieves and removes the last element of this deque,
	 * or returns {@code null} if this deque is empty.
	 *
	 * @return the tail of this deque, or {@code null} if this deque is empty
	 */
	E pollLast();

	/**
	 * Retrieves, but does not remove, the first element of this deque,
	 * or returns {@code null} if this deque is empty.
	 *
	 * @return the head of this deque, or {@code null} if this deque is empty
	 */
	E peekFirst();

	/**
	 * Retrieves, but does not remove, the last element of this deque,
	 * or returns {@code null} if this deque is empty.
	 *
	 * @return the tail of this deque, or {@code null} if this deque is empty
	 */
	E peekLast();

	/**
	 * Returns the number of elements in this deque.
	 *
	 * @return the number of elements in this deque
	 */
	int size();
}
