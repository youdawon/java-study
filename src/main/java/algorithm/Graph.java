package com.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	public int V;

	public Graph(int v){
		this.V = v;
	}

	public static class Vertex{
		int node;
		Vertex leftNode;
		Vertex rightNode;

		Vertex(int node){
			this.node = node;
		}
	}

	public void setEdges(Vertex vertex, Vertex leftNode, Vertex rightNode){
		vertex.leftNode = leftNode;
		vertex.rightNode = rightNode;
	}

	public void BFS(Vertex vertex){

		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(vertex);

		while(!queue.isEmpty()){

			Vertex n = queue.poll();

			System.out.print(n.node + " ");

			if(n.leftNode != null)
				queue.add(n.leftNode);

			if(n.rightNode != null)
				queue.add(n.rightNode);
		}
	}

	// public void inOrder(Vertex vertex){
	//
	// 	if(vertex == null)
	// 		return;
	//
	// 	inOrder(vertex.leftNode);
	//
	// 	System.out.print(vertex.node + "->");
	//
	// 	inOrder(vertex.rightNode);
	// }

	public LinkedList<Vertex> inOrder(Vertex vertex, LinkedList<Vertex> list){

		if(vertex.leftNode != null)
			inOrder(vertex.leftNode, list);

		list.add(vertex);

		if(vertex.rightNode != null)
			inOrder(vertex.rightNode, list);

		return list;
	}

	public LinkedList<Vertex> preOrder(Vertex vertex, LinkedList<Vertex> list){

		list.add(vertex);

		if(vertex.leftNode != null)
			preOrder(vertex.leftNode, list);

		if(vertex.rightNode != null)
			preOrder(vertex.rightNode, list);

		return list;
	}

	public LinkedList<Vertex> postOrder(Vertex vertex, LinkedList<Vertex> list){

		if(vertex.leftNode != null)
			postOrder(vertex.leftNode, list);

		if(vertex.rightNode != null)
			postOrder(vertex.rightNode, list);

		list.add(vertex);


		return list;
	}

	public static void main(String[] args){

		Graph graph = new Graph(7);

		Graph.Vertex v1 = new Graph.Vertex(9);
		Graph.Vertex v2 = new Graph.Vertex(4);
		Graph.Vertex v3 = new Graph.Vertex(20);
		Graph.Vertex v4 = new Graph.Vertex(1);
		Graph.Vertex v5 = new Graph.Vertex(6);
		Graph.Vertex v6 = new Graph.Vertex(15);
		Graph.Vertex v7 = new Graph.Vertex(170);

		graph.setEdges(v1, v2, v3);
		graph.setEdges(v2, v4, v5);
		graph.setEdges(v3, v6, v7);

		graph.BFS(v1);

		LinkedList<Vertex> list = new LinkedList<>();

		System.out.println("---------------------");

		graph.inOrder(v1, list);

		for(Vertex v : list){
			System.out.print(v.node + " ");
		}

		System.out.println("---------------------");

		list.clear();

		graph.preOrder(v1, list);

		for(Vertex v : list){
			System.out.print(v.node + " ");
		}
	}
}