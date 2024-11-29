package com.algorithms;

import java.util.ArrayList;
import java.util.List;

import lombok.ToString;

public class Stack {

	Node top;
	Node bottom;
	int length;

	@ToString
	public class Node{
		private int val;
		private Node next;

		Node(int val){
			this.val = val;
		}
	}

	/**
	 * get to the first node which is on the top
	 * @return
	 */
	public Node peek(){
		return top;
	}

	public void push(int value){
		Node newNode = new Node(value);
		if(length == 0){
			top = newNode;
			bottom = newNode;
		} else {
			Node pointer = top;
			top = newNode;
			top.next = top;
		}
		length++;
	}

	/**
	 * remove from the bottom
	 */
	public void pop(){
		if(top != null) {
			if (top == bottom) {
				bottom = null;
			}

			Node tempNode = top.next;
			top = top.next;

			length--;
		}
	}

	public List<Integer> getList(){

		List<Integer> list = new ArrayList<>();
		Node currentNode = top;

		while(currentNode != null){
			list.add(currentNode.val);
			currentNode = currentNode.next;
		}
		return list;
	}

	public static void main(String[] args){
		Stack stack = new Stack();
		stack.push(6);
		stack.push(10);
		stack.push(100);

		System.out.println(stack.getList());
		System.out.println(stack.peek());

		stack.pop();
		System.out.println(stack.getList());
	}
}
