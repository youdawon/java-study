package com.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.plaf.IconUIResource;

import lombok.ToString;

@ToString
public class LinkedList {

	Node head;
	Node tail;
	int length;

	LinkedList(int value){
		this.head = new Node(value);
		this.tail = this.head;
		this.length = 1;
	}

	public void append(int value){
		Node node = new Node(value);
		this.tail.next = node;
		this.tail = node;
		length++;
	}

	public void prepend(int value){
		Node node = new Node(value);
		node.next = head;
		head = node;
		length++;
	}

	public void insert(int index, int value){

		int i=0;
		Node newNode = new Node(value);
		Node currentNode = head;
		Node prevNode = head;

		if(index >= length)
			index = length;

		while( i != index){
			prevNode = currentNode;
			currentNode = currentNode.next;
			i++;
		}

		if(index == 0){
			this.head = newNode;
			this.head.next = currentNode;
		} else if (index == length){
			this.tail.next = newNode;
			this.tail = newNode;
		} else {
			newNode.next = currentNode;
			prevNode.next = newNode;
		}
		length++;
	}

	public void remove(int index){
		if(index >= length)
			index = length - 1;

		int i=0;
		Node currentNode = head;
		Node prevNode = head;

		while(i != index){
			prevNode = currentNode;
			currentNode = currentNode.next;
			i++;
		}

		 if(index == 0){
			this.head = currentNode.next;
		 } else if (index == length - 1){
			 this.tail = prevNode;
			 this.tail.next = null;
		 } else {
			prevNode.next = currentNode.next;
		 }

		 length--;
	}

	public int[] printList(){
		Node currentNode = head;
		int[] array = new int[length];
		int i=0;

		while(currentNode != null) {
			array[i] = currentNode.value;
			currentNode = currentNode.next;
			i++;
		}
		return array;
	}

	public void reverse(){

		Node firstNode = head;
		Node secondNode = firstNode.next;

		while(secondNode != null){
			Node temp = secondNode.next;
			secondNode.next = firstNode;
			firstNode = secondNode;
			secondNode = temp;
		}
	}

	@ToString
	public class Node{
		private int value;
		private Node next;

		public Node(int value){
			this.value = value;
		}
	}

	public static void main(String[] args) {

		LinkedList list = new LinkedList(10);
		list.append(15);
		// list.append(16);
		// list.prepend(29);
		// list.insert(2, 99);
		// list.insert(0, 120);
		// list.insert(99, 8);

		System.out.println(Arrays.toString(list.printList()));
		// list.remove(3);
		// System.out.println(Arrays.toString(list.printList()));
		// list.remove(list.length-1);
		// System.out.println(Arrays.toString(list.printList()));
		// list.remove(0);
		// System.out.println(Arrays.toString(list.printList()));
	}
}
