package org.jointheleague.ecolban;

public class ListNode<T> implements ImmutableList<T> {

	private final T head;
	private final ImmutableList<T> tail;
	private final int length;

	public ListNode(T head, ImmutableList<T> tail) {
		
		if (tail == null) {
			throw new IllegalArgumentException("Tail cannot be null.");
		}

		this.head = head;
		this.tail = tail;

		length = length();

	}

	// Package private constructor only to be used for
	// a call to super() in the EmptyList constructor.
	ListNode() {
		this.head = null;
		this.tail = null;
		this.length = 0;
	}

	/**
	 * 
	 * @return true if and only iff the list is empty
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * 
	 * @return the length of the list.
	 */
	public int length() {
		return tail.length() + 1;
	}

	/**
	 * Returns a reference to a list where all occurrences of e have been removed and is otherwise equal to this. The
	 * method duplicates as few nodes as possible. For example, if this has no element equal to e, then this is
	 * returned. If this.tail() has no occurrences of e, then the returned list and this share the same tail.
	 * 
	 * @param e
	 *            the element to remove
	 * 
	 * @return a list with all occurrences of e removed
	 */
	public ListNode<T> remove(T e) {
		int c = countOccurences(e);
		return remove(e, c);
	}

	public int countOccurences(T e) {
		
		if (tail.isEmpty()) {
			
			if (e == null ? head == null : e.equals(head)) {
				return 1;
			} else {
				return 0;
			}
		}
		
		if (e == null ? head == null : e.equals(head)) {
			return ((ListNode<T>)tail).countOccurences(e) + 1;
		} else {
			return ((ListNode<T>)tail).countOccurences(e);
		}

	}

	private ListNode<T> remove(T e, int objectsLeft) {

		if (objectsLeft == 0) {
			return this;
		}

		if (e == null ? head == null : e.equals(head)) {
			return ((ListNode<T>)tail).remove(e, objectsLeft - 1);
		} else {
			return new ListNode<T>(head, ((ListNode<T>)tail).remove(e, objectsLeft));
		}
	}

	/**
	 * 
	 * @param e
	 *            the element to push onto the list
	 * @return a new list with e added to the beginning of the list.
	 */
	public ListNode<T> push(T e) {
		return new ListNode<T>(e, this);
	}

	/**
	 * 
	 * @param e
	 *            the new element to append to the list
	 * @return a new list with e added to the end of the list.
	 */
	public ListNode<T> append(T e) {

		return new ListNode<T>(this.head, tail.append(e));
	}

	/**
	 * 
	 * @return the first element of the list.
	 * @throws NoSuchElementException
	 *             if the list is empty
	 */
	public T head() {
		return head;
	}

	/**
	 * 
	 * @return the tail of the list, i.e. a list identical to this except that it does not have the first element.
	 * @throws NoSuchElementException
	 *             if the list is empty
	 */
	public ImmutableList<T> tail() {
		return tail;
	}
	
	private String getStringForm() {
		
		if (head == null) {
			
			if (!tail.isEmpty()) {
				return "null " + ((ListNode<T>)tail).getStringForm();
			} else {
				return "null";
			}
			
		}
		
		if (tail.isEmpty()) {
			return head.toString();
		}
		
		return head.toString() + " " + ((ListNode<T>)tail).getStringForm();
		
	}

	/**
	 * An empty list has a string representation: "()" whereas a non-empty list has a string representation
	 * "(e1 e2 e3 ...)", where e1, e2, e3, ... are the string representations of the elements of the list.
	 * 
	 * @return the string representation of the list
	 */

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		
		sb.append(getStringForm());

		sb.append(')');
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return 101 * (17 * head.hashCode() + tail.hashCode());
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ListNode<?>)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		ListNode<T> that = (ListNode<T>) other;
		
		if (this.length() != that.length()) {
			return false;
		}
		
		if (!(this.head == null ? that.head == null : this.head.equals(that.head))) {
			return false;
		}
		
		return this.tail.equals(that.tail);

	}

}
