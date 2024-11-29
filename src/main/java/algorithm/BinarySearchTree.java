package com.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lombok.ToString;

public class BinarySearchTree {

	Node root;
	Node currentNode;

	@ToString
	public static class Node{

		int val;
		Node leftNode ;
		Node rightNode;

		Node(int val){
			this.val = val;
		}
	}

	public void insert(Node node){

		if (root == null) {
			root = node;
			currentNode = root;
		} else {
			if(node.val > currentNode.val){
				if(currentNode.rightNode == null) {
					currentNode.rightNode = node;
					currentNode = root;
				} else if(currentNode.rightNode != null){
					currentNode = currentNode.rightNode;
					insert(node);
				}
			} else {
				if (currentNode.leftNode == null) {
					currentNode.leftNode = node;
					currentNode = root;
				} else if (currentNode.leftNode != null) {
					currentNode = currentNode.leftNode;
					insert(node);
				}
			}
		}
	}

	public boolean lookup(int number){
		return searchTree(root, number);
	}

	public boolean searchTree(Node node, int number){

		if(node == null)
			return false;

		if(number == node.val){
			return true;
		} else if(number > node.val){
			return searchTree(node.rightNode, number);
		} else {
			return searchTree(node.leftNode, number);
		}
	}

	public List<Integer> getBFS(){
		List<Integer> list = new LinkedList<>();
		Queue<Node> queue = new LinkedList<>();

		if(root != null){
			queue.add(root);

			while(queue.size() > 0){
				Node node = queue.poll();
				list.add(node.val);

				if(node.leftNode != null){
					queue.add(node.leftNode);
				}

				if(node.rightNode != null){
					queue.add(node.rightNode);
				}
			}
		}

		return list;
	}

	public static void main(String[] args)
	{

		// Node node1 = new Node(9);
		// Node node4 = new Node(20);
		// Node node6 = new Node(15);
		// Node node2 = new Node(4);
		// Node node3 = new Node(6);
		// Node node5 = new Node(170);
		//
		// Node node7 = new Node(1);

		Node node1 = new Node(0);
		Node node2 = new Node(1);
		Node node3 = new Node(2);
		Node node4 = new Node(3);
		Node node5 = new Node(4);
		Node node6 = new Node(5);

		// Node node7 = new Node(1);

		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insert(node1);
		binarySearchTree.insert(node2);
		binarySearchTree.insert(node3);
		binarySearchTree.insert(node4);
		binarySearchTree.insert(node5);
		binarySearchTree.insert(node6);
		// binarySearchTree.insert(node7);

		System.out.println(binarySearchTree.getBFS());

		System.out.println(binarySearchTree.lookup(2));

		System.out.println(binarySearchTree.root.val);
	}
}