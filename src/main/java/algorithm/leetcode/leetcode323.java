/*
323. Number of Connected Components in an Undirected Graph
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

package algorithm.leetcode;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class leetcode323{
	public static int getConnectedComponents(int[][] edges, int n){

		int count = 0;

		List<List<Integer>> graph = new ArrayList<>();
		for(int i=0; i<n; i++){
			graph.add(new ArrayList<Integer>());
		}

		for(int i=0; i<edges.length; i++){
			graph.get(edges[i][0]).add(edges[i][1]);
			graph.get(edges[i][1]).add(edges[i][0]);
		}

		boolean[] visited = new boolean[n];
		Arrays.fill(visited, false);

		for(int i=0; i<n; i++){
			if(!visited[i]){
				dfs(graph, visited, i);
				count++;
			}
		}


		return count;
	}

	private static void dfs(List<List<Integer>> graph, boolean[] visited, int currentNode){

		if(visited[currentNode]){
			return;
		}

		visited[currentNode] = true;

		for(int neighbor : graph.get(currentNode)){
			dfs(graph, visited, neighbor);
		}
	}

	public static void main(String[] args){

		int n = 5;
		int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
		// int[][] edges = {{0, 1}, {1, 2},{2, 3}, {3, 4}};
		System.out.println(getConnectedComponents(edges, n));
	}
}
