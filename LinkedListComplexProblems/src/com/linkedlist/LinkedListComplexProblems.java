package com.linkedlist;

import java.util.HashSet;
import java.util.Set;


/**
 * Following problems are implemented in this class - 
 * 1. Linked List has a cycle or not
 * 2. Insert an element in a sorted list.
 * 3. 
 * @author DELL
 *
 */
public class LinkedListComplexProblems {
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.createListFromArray(new Integer[]{5});
		list.printList();
		System.out.println(isCyclic(list));
		//Cyclic list
		LinkedList<Integer> cyclicList = new LinkedList<>();
		cyclicList.createListFromArray(new Integer[]{5,10,15,20,30});
		LinkedListNode<Integer> temp = cyclicList.getHead();
		LinkedListNode<Integer> tail = cyclicList.getHead().getNext().getNext().getNext().getNext();
		tail.setNext(temp.getNext());
		System.out.println(isCyclic(cyclicList));
		
		insertInSortedList(list, 18);
		list.printList();
	}
	
	/**
	 * This will return whether the list is cyclic or not
	 * @return
	 */
	public static boolean isCyclic(LinkedList<Integer> list){
		/**
		 * There are lot of ways to implement this
		 * 1. Use HashSet to add the values if the Node(not value) exists then add method will return false 
		 * and the cycle exists.
		 * 
		 * 2. Add one flag called visited in the new node structure and iterate if we find already visited node then there is a cycle
		 * 3. The execellent approach is create two pointers slow and fast
		 * slow will move one step and fast will move two steps if they meet then there is a cycle
		 */
		
		//This is HashSet method
		// The time complexity is O(n) and space complexity O(n)
		
		/* Set<LinkedListNode<Integer>> set = new HashSet<>();
		 LinkedListNode<Integer> temp = list.getHead();
		 while(temp != null){
			 if(!set.add(temp)){
				 return true;
			 }
			 temp = temp.getNext();
		 }
		 return false;
		*/
		
		/**
		 * This is slow and fast pointer.
		 */
		LinkedListNode<Integer> slow = list.getHead();
		LinkedListNode<Integer> fast = list.getHead().getNext();
		while(slow != null && fast != null && fast.getNext() != null){
			slow = slow.getNext();
			fast =  fast.getNext().getNext();
			if(slow == fast){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Insert the node in the sorted list
	 * @param list
	 */
	public static void insertInSortedList(LinkedList<Integer> list, Integer element){
		if(list == null){
			return;
		}
		LinkedListNode<Integer> temp = list.getHead();
		LinkedListNode<Integer> prev  = null;
		while(temp != null){
			if(temp.getData() > element){
				break;
			}
			prev = temp;
			temp = temp.getNext();
		}
		LinkedListNode<Integer> t = new LinkedListNode<>(element);
		prev.setNext(t);
		t.setNext(temp);
	}
	
	
	

}
