import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 택배 배송(골드 5)
public class Main {
	static List<List<Node>> graph = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= a; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < b; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Node(e, c));
			graph.get(e).add(new Node(s, c)); // 양방향?
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dir = new int[a + 1];
		Arrays.fill(dir, 50000000);
		dir[1] = 0; // 나 -> 나 거리 = 0;
		boolean[] visited = new boolean[a + 1];
		pq.offer(new Node(1, 0));
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int now = n.end;
			if(visited[now]) continue;
			visited[now] = true;

			for (Node node : graph.get(now)) {
				if(dir[node.end] > dir[now] + node.cost) {
					dir[node.end] = dir[now] + node.cost;
					pq.offer(new Node(node.end, dir[node.end]));
				}
			}
		}
		System.out.println(dir[a]);
	}

	static class Node implements Comparable<Node> {
		int end;
		int cost;

		public Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		public int compareTo(Node n) {
			return this.cost - n.cost;
		}
	}
}
