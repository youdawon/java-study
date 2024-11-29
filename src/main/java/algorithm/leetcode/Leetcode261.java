package algorithm.leetcode;
/*
261. Graph Valid Tree
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0,1] is the same as [1,0] and thus will not appear together in edges.
*/

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Leetcode261 {

	public static boolean isGraphValidTree(int n, int[][] edges){

		//인접 리스트 생성, 무방향 그래프
		List<List<Integer>> graph = new ArrayList<>();

		for(int i=0; i<n; i++){
			graph.add(new ArrayList<>());
		}

		for(int[] edge : edges){
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}

		//cycle 존재 여부 확인
		boolean[] visited = new boolean[n];

		if(!dfs(graph, visited, 0, -1)){
			return false;
		}

		//방문하지 않은 vertex 존재
		for(boolean hasVisited: visited){
			if(hasVisited == false){
				return false;
			}
		}

		return true;
	}

	public static boolean dfs(List<List<Integer>> graph, boolean[] visited, int num, int parent){

		visited[num] = true;

		for(int neighbor : graph.get(num)) {
			if(neighbor == parent)
				continue;
			if(visited[neighbor])
				return false;
			if(!dfs(graph, visited, neighbor, num)){
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args){
		int n = 5;
		int[][] edges = new int[][]{{0,1}, {0,2}, {0,3}, {1,4}};
		// int[][] edges = new int[][]{{0,1}, {1,2}, {2,3}, {1, 3}, {1,4}};

		System.out.println(isGraphValidTree(n, edges));
	}
}

