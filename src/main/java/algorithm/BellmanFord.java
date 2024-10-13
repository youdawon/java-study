package main.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 여기 벨만-포드 알고리즘을 사용할 수 있는 문제를 하나 제시합니다.
 *
 * ### 문제:
 * **N개의 정점**과 **M개의 간선**이 있는 방향성 가중치 그래프가 주어집니다. 각 간선에는 음수 또는 양수의 가중치가 있을 수 있습니다.
 * 이 그래프에서 출발점으로부터 다른 모든 정점까지의 최단 경로를 구하고, **음수 사이클**이 있는지 확인하세요.
 *
 * ### 입력 형식:
 * 1. 첫 번째 줄에 정점의 수 N과 간선의 수 M이 주어집니다. (2 ≤ N ≤ 1000, 1 ≤ M ≤ 5000)
 * 2. 두 번째 줄부터 M개의 줄에 각 간선이 주어집니다. 각 줄에는 세 개의 정수 u, v, w가 주어집니다.
 * (1 ≤ u, v ≤ N, -1000 ≤ w ≤ 1000)
 *    - u는 간선의 출발 정점, v는 간선의 도착 정점, w는 간선의 가중치입니다.
 * 3. 마지막 줄에는 출발 정점 S가 주어집니다. (1 ≤ S ≤ N)
 *
 * ### 출력 형식:
 * 1. 출발 정점 S로부터 각 정점까지의 최단 거리를 출력하세요. 만약 도달할 수 없는 정점이 있다면 "INF"를 출력합니다.
 * 2. 음수 사이클이 발견되면, "NEGATIVE CYCLE DETECTED"를 출력합니다.
 *
 * ### 예시 입력 1:
 * ```
 * 5 8
 * 1 2 6
 * 1 3 7
 * 2 3 8
 * 2 4 5
 * 2 5 -4
 * 3 4 -3
 * 4 5 9
 * 5 2 2
 * 1
 * ```
 *
 * ### 예시 출력 1:
 * ```
 * 0
 * 6
 * 7
 * 4
 * 2
 * ```
 *
 * ### 예시 입력 2:
 * ```
 * 3 3
 * 1 2 4
 * 2 3 -10
 * 3 1 3
 * 1
 * ```
 *
 * ### 예시 출력 2:
 * ```
 * NEGATIVE CYCLE DETECTED
 * ```
 *
 * ### 설명:
 * - 첫 번째 예시에서 출발점 1에서 각 정점까지의 최단 거리를 구하고, 음수 사이클은 없습니다.
 * - 두 번째 예시에서는 음수 사이클이 존재하기 때문에 "NEGATIVE CYCLE DETECTED"를 출력합니다.
 *
 * 이 문제는 벨만-포드 알고리즘을 통해 음수 가중치가 있는 그래프에서 최단 경로를 찾고, 음수 사이클을 탐지하는 방법을 연습할 수 있습니다.
 */
public class BellmanFord {

	public static void bellmanFord(int vertices, int edges, int[] from , int[] to, int[] weights, int start){

		int[] distances = new int[vertices+1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[start] = 0;

		for(int i=0; i<vertices-1; i++){
			for(int j=0; j< from.length; j++){
				int u = from[j];
				int v = to[j];
				int weight = weights[j];
				if(distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]){
					distances[v] = distances[u] + weight;
				}
			}
		}

		for(int j=0; j<from.length; j++){
			int u = from[j];
			int v = to[j];
			int weight = weights[j];
			if(distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]){
				System.out.println("NEGATIVE CYCLE DETECTED");
				return;
			}
		}

		for (int i = 1; i <= vertices; i++) {
			if (distances[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(distances[i]);
			}
		}
	}

	public static void main(String[] args){
		int vertices = 5;
		int edges = 8;
		int[] from = {1, 1, 2, 2, 2, 3, 4, 5};
		int[] to = {2, 3, 3, 4, 5, 4, 5, 2};
		int[] weights = {6, 7, 8, 5, -4, -3, 9, 2};
		int start = 1;

		bellmanFord(vertices, edges, from, to, weights, start);
	}
}
