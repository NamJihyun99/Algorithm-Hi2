import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 도시 분할 계획(골드 4)
// 1. dfs로 사이클 판별하면 시간 초과 O(N*M)
// 2. 유니온 파인드
public class Main {
	static int n;
	static int m;
	static List<Node> edges;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		parent = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Node(a, b, cost));
		}

		Collections.sort(edges);

		int edgeCount = 0;
		int max = 0;
		long totalCost = 0;

		for (Node edge : edges) {
			// MST 완성되면 종료
			if (edgeCount == n - 1) break;

			if (union(edge.a, edge.b)) {
				totalCost += edge.cost;
				max = Math.max(max, edge.cost);
				edgeCount++;
			}
		}
		System.out.println(totalCost - max);
	}


	static boolean union(int a, int b) {
		int ar = find(a);
		int br = find(b);

		if (ar == br) return false; // 부모 같음 == 사이클 발생

		if (ar < br) parent[br] = ar;
		else parent[ar] = br;

		return true;
	}

	static int find(int a) {
		if (parent[a] == a) return a;
		return find(parent[a]);
	}

	static class Node implements Comparable<Node> {
		int a;
		int b;
		int cost;

		public Node(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		public int compareTo(Node node) {
			return this.cost - node.cost;
		}
	}
}
