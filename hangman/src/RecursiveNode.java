/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package list;

public class RecursiveNode<E>{
	
	private E value;
	private RecursiveNode<E> next;
	
	public RecursiveNode(E value){
		// TODO
		this.value = value;
		next = null;
	}
	
	public void setValue(E value){
		// TODO
		this.value = value;
	}
	
	public E getValue(){
		// TODO
		return value;
	}
	
	public void setNext(RecursiveNode<E> next){
		// TODO
		this.next = next;
	}
	
	public RecursiveNode<E> getNext(){
		// TODO
		return next;
	}
		

	public void setTail(RecursiveNode<E> newTail) {
		RecursiveNode<E> temp = next;
		
		if(temp.getNext() == null) {
			temp.setNext(newTail);
		}
		
		temp.setTail(newTail);	
	}
	
	public StringBuilder generateString(StringBuilder builder) {
		// TODO
		return null;
	}

}
