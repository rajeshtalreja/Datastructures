package com.linkedlist;

/**
 * This represents the linked list
 * 
 * @author DELL
 */
public class LinkedList<T> {

	private LinkedListNode<T> head;

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

}
