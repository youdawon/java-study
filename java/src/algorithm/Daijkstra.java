package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Daijkstra {

	static class Node {
		private int vertex;
		private int cost;

		public Node(int vertex, int cost){
			this.vertex = vertex;
			this.cost = cost;
		}

		public int getVertex() {
			return this.vertex;
		}

		public int getCost() {
			return this.cost;
		}
	}

	public static int[] dijkstra(int vertices, int edges, int[] from, int[] to, int[] weight, int start){

		List<List<Node>> graph = new ArrayList<>();

		for(int i = 0; i < vertices; i++){
			graph.add(new ArrayList<>());
		}

		for(int i = 0; i < from.length; i++){
			graph.get(from[i]-1).add(new Node(to[i]-1, weight[i]));
		}

		int[] distance = new int[vertices];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start-1] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.getCost(), b.getCost()));
		pq.add(new Node(start-1, 0));

		while(!pq.isEmpty()){
			Node currentNode = pq.poll();
			int currentDistance = currentNode.getCost();
			int currentVertex = currentNode.getVertex();

			if(distance[currentVertex] < currentDistance){
				continue;
			}

			for(Node neighbor : graph.get(currentVertex)){
				int newDistance = neighbor.getCost() + currentDistance;
				if(newDistance < distance[neighbor.getVertex()]){
					distance[neighbor.getVertex()] = newDistance;
					pq.offer(new Node(neighbor.getVertex(), newDistance));
				}
			}
		}

		return distance;
	}

	public static void main(String[] args) {

		int vertices = 5;
		int edges = 6;
		int[] from = {1, 1, 2, 2, 3, 4};
		int[] to = {2, 3, 3, 4, 4, 5};
		int[] weight = {2, 3, 4, 5, 6, 1};
		int start = 1;


		int[] distance = dijkstra(vertices, edges, from, to, weight, start);

		for(int i=0; i<distance.length; i++){
			System.out.println(distance[i]);
		}
	}
}
