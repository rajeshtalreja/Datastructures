package com.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Heap {
	int[] arr;
	private final int DEFAULT_MIN_CAPACITY = 11;
	private final int DEFAULT_CAPACITY = Integer.MAX_VALUE;
	private int capacity = DEFAULT_CAPACITY;
	private int currPos = -1;
	public Heap() {
		arr = new int[DEFAULT_MIN_CAPACITY];
	}
	public Heap(int capacity) {
		this.capacity = capacity;
		arr = new int[capacity];
	}
	public void percolateDown(int index) {
		while(index < currPos) {
			int leftChildIndex = getLeftChildIndex(index);
			int rightChildIndex = getRightChildIndex(index);
			int maxIndex = index;
			if(leftChildIndex != -1 && arr[leftChildIndex] < arr[maxIndex]) {
				maxIndex = leftChildIndex;
			}
			if(rightChildIndex != -1 && arr[rightChildIndex] < arr[maxIndex]) {
				maxIndex = rightChildIndex;
			}
			if(maxIndex != index) {
				// swap maxIndex and Index
				int t = arr[maxIndex];
				arr[maxIndex] = arr[index];
				arr[index] = t;
			}else {
				break;
			}
			index = maxIndex;
		}
	}
	private int getRightChildIndex(int index) {
		int rightIndex = 2 * index + 2;
		if(rightIndex <= currPos) {
			return rightIndex;
		}
		return -1;
	}
	private int getLeftChildIndex(int index) {
		int leftIndex = 2 * index + 1;
		if(leftIndex <= currPos) {
			return leftIndex;
		}
		return -1;
	}
	public void percolateUp(int index) {
		while(index > 0) {
			int parent = (index - 1)/2;
			if(arr[parent] > arr[index]) {
				//swap parent and index;
				int t = arr[parent];
				arr[parent] = arr[index];
				arr[index] = t;
			}
			index = parent;
		}
	}
	public void insert(int element) {
		if(capacity == DEFAULT_CAPACITY) {
			arr[++currPos] = element;
			percolateUp(currPos);
		}
	}
	public int remove() {
		if(currPos < 0) {
			return -1;
		}
		if(currPos == 0) {
			return arr[currPos--];
		}
		int toRemove = arr[0];
		int t = arr[0];
		arr[0] = arr[currPos];
		arr[currPos] = t;
		currPos--;
		percolateDown(0);
		return toRemove;
	}
	private void resize() {
		int temp[] = new int[capacity * 2];
		System.arraycopy(arr, 0, temp, 0, DEFAULT_CAPACITY);
	}
	public void heapify(int []arr) {
		System.arraycopy(arr, 0, this.arr, 0, arr.length);
		int lastIndex = arr.length - 1;
		for(int i = (lastIndex - 1) /2 ;i>=0;i--) {
			percolateDown(i);
		}
	}
	public static void main(String[] args) {
		Heap heap = new Heap();
		heap.insert(20);
		heap.insert(10);
		heap.insert(9);
		heap.insert(7);
		heap.insert(15);
		heap.insert(5);
		System.out.println(heap.remove());
		System.out.println(heap);
		heap.heapify(new int[] {5,10,6,9,8,2});
		System.out.println(heap.arr);
		
//		
//		Map<Character, Integer> map = new HashMap<>();
//        for(int i =0;i<s.length();i++){
//            Character c = s.charAt(i);
//            Integer v = map.getOrDefault(c, 0);
//            v = v + 1;
//            map.put(c, v);
//        }
//        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(map.entrySet());
//        Collections.sort(new ArrayList<Map.Entry<Character, Integer>>(entries), new Comparator<Map.Entry<Character,Integer>>(){
//            public int compare(Map.Entry<Character,Integer> e1, Map.Entry<Character,Integer> e2){
//                return e1.getValue() - e2.getValue();
//            }
//        });
	}
}
