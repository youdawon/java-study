package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {

	public static int[] dijkstra(int vertices, int edges, int[] from, int[] to, int[] weight, int start){

		int[] distances = new int[vertices+1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[start] = 0;

		List<List<int[]>> graph = new ArrayList<>();
		for(int i=0; i<=vertices; i++){
			graph.add(new ArrayList<>());
		}

		for(int i=0; i<from.length; i++){
			graph.get(from[i]).add(new int[]{to[i], weight[i]});
		}

		PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		priorityQueue.offer(new int[]{start, 0});

		while(!priorityQueue.isEmpty()){
			int[] current = priorityQueue.poll();
			int currentVertex = current[0];
			int currentDistance = current[1];

			for(int[] neighbor : graph.get(currentVertex)){
				int neighborVertex = neighbor[0];
				int newDistance = currentDistance + neighbor[1];

				if(distances[neighborVertex] > newDistance){
					distances[neighborVertex] = newDistance;
					priorityQueue.offer(new int[]{neighborVertex, newDistance});
				}
			}
		}

		return distances;
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
