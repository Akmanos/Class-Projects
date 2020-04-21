/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package list;

public class RecursiveLinkedList<E> {
	
	private RecursiveNode<E> head;
	int size;
	
	public RecursiveLinkedList() {
		this.head = null;
		size=0;
	}
	
	/**
	 * 
	 * @return the number of elements in this list
	 */
	public int size() {
		// TODO	
		return size;
	}
	
	
	
	/**
	 * 
	 * @param e the element search for
	 * @return true iff the list contains an element of whose value equals that of e
	 */
	public boolean contains(E e) {
		// TODO
		RecursiveNode<E> curr = head;
		
		return contains(e, curr);
	}
	
	private boolean contains(E e, RecursiveNode<E> curr) {
		if(curr != null) {
			if(curr.getValue().equals(e)) {
				return true;
			}
			
			if(curr.getNext() != null) {
				if(curr.getValue().equals(e)) {
					return true;
				}
				
				return contains(e, curr.getNext());
			}
		}
		return false;
	}
	
	/**
	 * Appends the element e to the end of the list.
	 * 
	 * @param e the value to append
	 */
	public void append(E e) {
		// TODO
		size++;
		RecursiveNode<E> temp = head;
		
		append(e, temp);
	}
	
	
	private void append(E e, RecursiveNode<E> temp) {
		// TODO Auto-generated method stub
		if(temp == null) {
			head = new RecursiveNode<E>(e);
		}
		else {
			if(temp.getNext() == null) {
				temp.setNext(new RecursiveNode<E>(e));
			}
			
			else {
				temp = temp.getNext();
				append(e, temp);
			}
		}
		
	}

	@Override
	public String toString() {
		// TODO
		RecursiveNode<E> temp = head;
		
		return toString(temp);
	}
	
	private String toString(RecursiveNode<E> temp) {
		if( temp != null) {
			return temp.getValue()+" " + toString(temp.getNext());		
		} 
		else {
			return "";
		}
	}
}
