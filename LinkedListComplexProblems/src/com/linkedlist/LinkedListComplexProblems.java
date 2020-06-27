package com.linkedlist;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;


/**
 * Following problems are implemented in this class - 
 * 1. Linked List has a cycle or not
 * 2. Insert an element in a sorted list.
 * 3. Reverse the list
 * 4. Intersection of two linked list(whether they are intersecting and find intersection
 *     1 - 2 - 3
 *     
 *               4 - 5 - 6
 *       
 *       10 - 11
 * 5. Merge two sorted linked lists.
 *    list 1 = {1,2,5} list 2 = {3,6,9}
 *    list3 = {1,2,3,5,6,9}
 *    
 * 6. Merge  k sorted lists
 *    Input
 *    [
 *     [1,2,5],
 *     [6,8,10,15],
 *     [2,3,8]
 *    ]
 *    Output
 *    [1,2,2,3,5,6,8,8,10,15]
 *    
 *  7. Swap nodes in pair
 *  
 *    IP - 1 -> 2 -> 3 -> 4 -> 5 -> NULL
 *    OP - 2 -> 1 -> 4 -> 3 -> 5 -> NULL 
 *    
 *  8. Reverse list in K blocks
 *     1 -> 2 -> 3 -> 4 -> 5 -> 6 -> NULL
 *     k = 3
 *     OP - 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> NULL   
 *       
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
		
		//Insert in sorted list
		insertInSortedList(list, 18);
		list.printList();
		
		//Reverse the list
		LinkedList<Integer> reverseList = new LinkedList<Integer>();
		reverseList.createListFromArray(new Integer[]{5,1});
		reverse(reverseList);
		reverseList.printList();
		
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.createListFromArray(new Integer[]{1,5});
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.createListFromArray(new Integer[]{6});
		LinkedList<Integer> list3 = mergeTwoSortedList(list1, list2);
		list3.printList();
		LinkedListNode<Integer> head3 = mergeSortedListsRecursive(list1.getHead(), list2.getHead());
		LinkedList<Integer> list4 = new LinkedList<>();
		list4.setHead(head3);
		list4.printList();
		
		
		LinkedList<Integer> l1 = new LinkedList<>();
		l1.createListFromArray(new Integer[]{2,5,6,8});
		LinkedList<Integer> l2 = new LinkedList<>();
		l2.createListFromArray(new Integer[]{1,5,7});
		LinkedList<Integer> l3 = new LinkedList<>();
		l3.createListFromArray(new Integer[]{10,12,13});
		LinkedList<Integer> l4 = new LinkedList<>();
		l4.createListFromArray(new Integer[]{11,15,16});
		LinkedList<Integer> l5 = mergeKSortedList(new LinkedList[]{l1,l2,l3,l4});
		l5.printList();
	}
	
	/**
	 * This will return whether the list is cyclic or not
	 * @return
	 */
	public static boolean isCyclic(LinkedList<Integer> list){
		/**
		 * There are lot of ways to implement this
		 * 1. Use HashSet to add the values if the Node(not value) exists then add method will return false 
		 *    and the cycle exists.
		 * 
		 * 2. Add one flag called visited in the new node structure and iterate if we find already visited node 
		 *    then there is a cycle
		 * 3. The excellent approach is create two pointers slow and fast
		 *    slow will move one step and fast will move two steps if they meet then there is a cycle
		 * 4. Reverse a list   
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
	
	/**
	 * This depicts reversing the linked list.
	 * @param list
	 */
	public static void reverse(LinkedList<Integer> list){
		
		//Base condition
		if(list == null || list.getHead() == null){
			return;
		}
		LinkedListNode<Integer> prev = null;
		LinkedListNode<Integer> temp = list.getHead();
		while(temp != null){
			LinkedListNode<Integer> temp1 = temp.getNext();
			temp.setNext(prev);
			prev = temp;
			temp = temp1;
		}
		list.setHead(prev);
		
	}
	
	/**
	 * This will return the intersection point of two lists if exists otherwise null
	 * @param list
	 * @return
	 */
	public static LinkedListNode<Integer> intersection(LinkedList<Integer> list1, LinkedList<Integer> list2){
		if(list1 == null || list1.getHead() == null || list2.getHead() == null || list2.getHead() == null){
			return null;
		}
		/**
		 * Stack approach
		 * Use two stacks
		 * Push list1 elements into stack1 and list2 elements into stack2
		 * Start popping the elements from the stacks and if the different element is found
		 * then the element prior to this is the intersection point.
		 */
		Stack<LinkedListNode<Integer>> stack1 = new Stack<>();
		Stack<LinkedListNode<Integer>> stack2 = new Stack<>();
		
		LinkedListNode<Integer> temp1 = list1.getHead();
		LinkedListNode<Integer> temp2 = list1.getHead();
		while(temp1 != null){
			stack1.push(temp1);
			temp1 = temp1.getNext();
		}
		while(temp2 != null){
			stack2.push(temp2);
			temp2 = temp2.getNext();
		}
		LinkedListNode<Integer> prev = null;
		while(!stack1.isEmpty() && !stack2.isEmpty()){
			
			LinkedListNode<Integer> node1 = stack1.pop();
			LinkedListNode<Integer> node2 = stack2.pop();
			if(node1 != node2){
				return prev;
			}
			prev = node1;
		}
		return null;
		/**
		 * Use the length approach
		 * Find length of list1 say m
		 * Find length of list2 say n
		 * Start a loop with m-n to end and
		 * find the element which is equal in both
		 */
	}
	
	public static LinkedList<Integer> mergeTwoSortedList(LinkedList<Integer> list1, LinkedList<Integer> list2){
		if(list1 == null && list2 == null){
			return null;
		}
		if(list1 == null){
			return list2;
		}
		if(list2 == null){
			return list1;
		}
		
		/**
		 * This is simple logic of merging
		 */
		
		LinkedList<Integer> list3 = new LinkedList<>();
		LinkedListNode<Integer> head1 = list1.getHead();
		LinkedListNode<Integer> head2 = list2.getHead();
		while(head1 != null && head2 != null){
			if(head1.getData() < head2.getData()){
				list3.addElement(head1.getData());
				head1 = head1.getNext();
			}else{
				list3.addElement(head2.getData());
				head2 = head2.getNext();
			}
		}
		
		while(head1 != null){
			list3.addElement(head1.getData());
			head1 = head1.getNext();
		}
		while(head2 != null){
			list3.addElement(head2.getData());
			head2 = head2.getNext();
		}
		return list3;
	}
	
	/**
	 * Recursive way to merge  the sorted lists
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static LinkedListNode<Integer> mergeSortedListsRecursive(LinkedListNode<Integer> head1,LinkedListNode<Integer> head2){
		
		if(head1 == null && head2 == null){
			return null;
		}
		if(head1 == null){
			return head2;
		}
		if(head2 == null){
			return head1;
		}
		LinkedListNode<Integer> result = null;
		if(head1.getData() < head2.getData()){
			result = head1;
			result.setNext(mergeSortedListsRecursive(head1.getNext(), head2));
		}else{
			result = head2;
			result.setNext(mergeSortedListsRecursive(head1, head2.getNext()));
		}
		return result;
	}
	
	/**
	 * This will merge
	 * @param arr
	 * @return
	 */
    public static LinkedList<Integer> mergeKSortedList(LinkedList<Integer> arr[]){
    	if(arr == null || arr.length == 0){
    		return null;
    	}
    	LinkedListNode<Integer> head = null;
    	LinkedListNode<Integer> tail = null;
    	PriorityQueue<LinkedListNode<Integer>> queue = new PriorityQueue<>(new Comparator<LinkedListNode<Integer>>() {
			@Override
			public int compare(LinkedListNode<Integer> o1, LinkedListNode<Integer> o2) {
				return o1.getData().compareTo(o2.getData());
			}
    	});
    	for(int i=0;i<arr.length;i++){
    		queue.add(arr[i].getHead());
    	}
    	while(!queue.isEmpty()){
    		LinkedListNode<Integer> node = queue.poll();
    		if(head == null && tail == null){
    			head = node;
    			tail = node;
    		}else{
    			tail.setNext(node);
    			tail = node;
    		}
    		if(node.getNext() != null){
    			queue.add(node.getNext());
    		}
    	}
    	LinkedList<Integer> list = new LinkedList<>();
    	list.setHead(head);
    	return list;
    }
	
 
    
	
	

}
