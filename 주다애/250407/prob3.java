import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// 숨바꼭질 3(골드 5)
public class Main {
	static int[] dir;
	static int[] dist;
	static boolean[] visited;
	static int n, k;
	static int d = 100000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		visited = new boolean[100001];
		dist = new int[100001];

		dijkstra(n);
		System.out.println(dist[k]);
	}

	private static void dijkstra(int n) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[n] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(n, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (visited[now.node]) continue;
			visited[now.node] = true;

			if (now.node + 1 <= d && now.node + 1 >= 0 && !visited[now.node + 1]) {
				if (dist[now.node + 1] > dist[now.node] + 1) {
					dist[now.node + 1] = dist[now.node] + 1;
					pq.offer(new Node(now.node + 1, dist[now.node + 1]));
				}

			}
			if (now.node - 1 <= d && now.node - 1 >= 0 && !visited[now.node - 1]) {
				if (dist[now.node - 1] > dist[now.node] + 1) {
					dist[now.node - 1] = dist[now.node] + 1;
					pq.offer(new Node(now.node - 1, dist[now.node - 1]));
				}

			}
			if (now.node * 2 <= d && now.node * 2 >= 0 && !visited[now.node * 2]) {
				if (dist[now.node * 2] > dist[now.node]) {
					dist[now.node * 2] = dist[now.node];
					pq.offer(new Node(now.node * 2, dist[now.node * 2]));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int node;
		int cost;

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
