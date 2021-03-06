package com.linkedlist;

/**
 * This represents the linked list
 * 
 * @author DELL
 */
public class LinkedList<T> {

	private LinkedListNode<T> head;
	
	private LinkedListNode<T> tail;

	public LinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(LinkedListNode<T> head) {
		this.head = head;
	}

	public void createListFromArray(T[] array) {
		LinkedListNode<T> start = null;
		LinkedListNode<T> end = null;
		LinkedListNode<T> temp = new LinkedListNode<T>(array[0]);
		start = temp;
		end = temp;
		for (int i = 1; i < array.length; i++) {
			temp = new LinkedListNode<T>(array[i]);
			end.setNext(temp);
			end = temp;
		}
		head = start;
	}

	public void printList() {
		LinkedListNode<T> temp = head;
		while(temp != null){
			System.out.print(temp);
			temp = temp.getNext();
		}
		System.out.println("NULL");
	}
	
	public void addElement(T element){
		LinkedListNode<T> node = new LinkedListNode<>(element); 
		if(head == null){
			head = node;
			tail = node;
		}else{
			tail.setNext(node);
			tail = node;
		}
	}

}

/**
 * This represents the LinkedList Node
 * 
 * @author DELL
 */
class LinkedListNode<T> {
	private T data;
	private LinkedListNode<T> next;

	public LinkedListNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public LinkedListNode<T> getNext() {
		return next;
	}

	public void setNext(LinkedListNode<T> next) {
		this.next = next;
	}
	
	@Override
	public String toString(){
		return data.toString() + " -> ";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedListNode<T> other = (LinkedListNode<T>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
	

}
