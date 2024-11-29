package com.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Queue {

	Node first;
	Node last;
	int length;

	public class Node{
		String value;
		Node next;

		public Node(String value){
			this.value = value;
		}
	}

	public Node peek(){
		return first;
	}

	public void enqueue(String value){

		Node newNode = new Node(value);

		if(first == null) {
			first = newNode;
			last = newNode;
		} else {
			last.next = newNode;
			last = newNode;
		}
		length++;
	}

	public void dequeue(){

		if(first != null){
			if(first == last){
				last = null;
			}
			first = first.next;
		}


	}

	public List<String> getList() {

		List<String> list = new ArrayList<>();
		Node currentNode = first;

		while (currentNode != null) {
			list.add(currentNode.value);
			currentNode = currentNode.next;
		}
		return list;
	}

	public static void main(String[] args){
		Queue queue = new Queue();

		queue.enqueue("Joy");
		queue.enqueue("Matt");
		queue.enqueue("Pavel");
		queue.enqueue("Samir");

		System.out.println(queue.getList());
		System.out.println(queue.peek());

		queue.dequeue();

		System.out.println(queue.getList());
	}
}
