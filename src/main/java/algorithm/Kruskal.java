package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 다음은 크루스칼 알고리즘을 구현하여 최소 신장 트리를 찾는 문제입니다.
 *
 * ### 문제: 크루스칼 알고리즘을 사용한 최소 신장 트리 (MST) 찾기
 *
 * 주어진 그래프에서 모든 노드를 연결하는 최소 비용의 간선들만 선택하여 트리를 구성하세요. 각 간선의 비용이 주어졌을 때, 이 비용을 최소화하는 방법을 찾아야 합니다.
 *
 * #### 입력
 * - 첫 번째 줄에는 노드의 개수 \(N\)과 간선의 개수 \(M\)이 주어집니다. \(N\)은 1 이상 100,000 이하의 정수이며, \(M\)은 0 이상 1,000,000 이하의 정수입니다.
 * - 두 번째 줄부터는 간선의 정보가 \(M\)줄에 걸쳐 주어집니다. 각 줄에는 세 정수 \(u\), \(v\), \(w\)가 주어지며, 이는 노드 \(u\)와 노드 \(v\) 사이에 가중치 \(w\)인 간선이 존재함을 의미합니다.
 *
 * #### 출력
 * - 최소 신장 트리를 구성하는데 필요한 최소 비용을 출력하세요.
 *
 * #### 예시 입력
 * ```
 * 4 5
 * 1 2 5
 * 1 3 4
 * 2 3 6
 * 2 4 3
 * 3 4 2
 * ```
 *
 * #### 예시 출력
 * ```
 * 10
 * ```
 *
 * ### 설명
 * - 주어진 그래프는 4개의 노드와 5개의 간선으로 구성되어 있습니다.
 * - 최소 신장 트리는 노드들을 모두 연결하면서 최소 비용을 유지해야 하므로, 크루스칼 알고리즘을 사용하여 간선들을 가중치 순으로 선택하고 사이클이 생기지 않도록 연결합니다.
 *
 * ### 풀이 가이드라인
 * 1. **크루스칼 알고리즘**은 간선 기반의 그리디 알고리즘입니다.
 * 2. 간선을 가중치 순으로 정렬한 후, 사이클을 이루지 않도록 간선을 선택해 최소 신장 트리를 구성합니다.
 * 3. 이를 위해 **유니온 파인드(Union-Find)** 자료구조를 사용해 사이클을 확인합니다.
 *
 * # 입력 처리
 * n, m = map(int, input().split())
 * edges = []
 * for _ in range(m):
 *     u, v, w = map(int, input().split())
 *     edges.append((u, v, w))
 *
 * # 크루스칼 알고리즘 실행
 * result = kruskal(n, edges)
 * print(result)
 * ```
 *
 * ### 주요 아이디어
 * - **Union-Find**: 사이클을 피하기 위해 사용되며, 간선 연결 시 각 노드의 루트를 찾고 두 루트가 다르면 연결합니다.
 * - **간선 정렬**: 가중치가 작은 순서대로 간선을 선택하여 트리를 구성합니다.
 * - **최소 신장 트리(MST)**: 크루스칼 알고리즘으로 선택된 간선들이 트리를 구성하며, 이를 통해 최소 비용을 계산합니다.
 *
 * 이 문제를 풀고 크루스칼 알고리즘을 익혀보세요!
 */
public class Kruskal {

	static class UnionFind{
		int[] parent;
		int[] rank;

		public UnionFind(int n){
			this.parent = new int[n+1];
			this.rank = new int[n+1];

			for(int i=0; i<=n; i++){
				this.parent[i] = i;
				this.rank[i] = 1;
			}
		}

		public int find(int i){
			if(parent[i] != i){
				parent[i] = find(parent[i]);
			}
			return parent[i];
		}

		public void union(int x, int y){
			int xParent = find(x);
			int yParent = find(y);

			if(xParent != yParent){
				if(rank[xParent] < rank[yParent]){
					parent[xParent] = yParent;
				} else if(rank[xParent] > rank[yParent]){
					parent[yParent] = xParent;
				} else {
					parent[yParent] = xParent;
					rank[xParent]++;
				}
			}
		}
	}

	public static void Kruskal(int vertex, List<int[]> edges){

		Collections.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

		UnionFind uf = new UnionFind(vertex);
		int minimumWeight = 0;
		int edgeCount = 0;

		for(int i=0; i< edges.size(); i++){
			int u = edges.get(i)[0];
			int v = edges.get(i)[1];
			int w = edges.get(i)[2];

			int uParent = uf.find(u);
			int vParent = uf.find(v);

			// 싸이클 미존재
			if(uParent != vParent){
				uf.union(uParent, vParent);
				minimumWeight += w;
				edgeCount++;
			}

			if(edgeCount == vertex-1){
				break;
			}
		}

		System.out.println(minimumWeight);
	}

	public static void main(String[] args){

		int edge = 5;
		int vertex = 4;

		List<int[]> edges = new ArrayList<>();
		edges.add(new int[] {1, 2, 5});
		edges.add(new int[] {1, 3, 4});
		edges.add(new int[] {2, 3, 6});
		edges.add(new int[] {2, 4, 3});
		edges.add(new int[] {3, 4, 2});

		Kruskal(vertex, edges);
	}
}
