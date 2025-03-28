package com.stack;

public class Stack<T> {
	
	private Object[] arr;
	private int top = -1;
	private int size = 0;
	Stack(int capcity){
		arr = new Object[capcity];
	}
	/**
	 * 
	 * @return
	 */
	public boolean isFull() {
		return top == arr.length - 1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * 
	 * @param v
	 * @throws StackOperationException 
	 */
	public void push(T v) throws StackOperationException {
		if(isFull()) {
			throw new StackOperationException("Stack is full");
		}
		arr[++top] = v;
	}
	
	public T pop() throws StackOperationException {
		if(isEmpty()) {
			throw new StackOperationException("Stack is Empty");
		}
		T value = (T)arr[top];
		top--;
		return value;
	}
	
	public T peek() throws StackOperationException {
		if(isEmpty()) {
			throw new StackOperationException("Stack is Empty");
		}
		return (T)arr[top];
	}
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>(5);
		Stack<Double> stack1 = new Stack<Double>(5);
		try {
			stack.push(10);
			stack.push(20);
			stack.push(30);
			Integer v = stack.pop();
			System.out.println(v);
			System.out.println(stack.peek());
		} catch (StackOperationException e) {
			e.printStackTrace();
		}
		
	}

}
class StackOperationException extends Exception{
	
	public StackOperationException(String message) {
		this.message = message;
	}
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
