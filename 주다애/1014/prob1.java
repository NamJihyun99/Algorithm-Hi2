import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon1446 {
	static class Node implements Comparable<Node>{
		int idx;
		int cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		public int compareTo(Node n) {
			return this.cost - n.cost;
		}
	}
	static int n;
	static int d;
	static int[] dist;
	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		dist = new int[d + 1]; // d+1로 메모리 최적화
		graph = new ArrayList<>();
		for (int i = 0; i <= d; i++) {
			graph.add(new ArrayList<>());
		}
		// 1차이 나면 거리가 1임
		for (int i = 0; i < d; i++) {
			graph.get(i).add(new Node(i + 1, 1));
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			// 지름길의 끝이 고속도로의 끝을 넘을 경우 무시
			if (e > d) continue;
			graph.get(s).add(new Node(e, cost));
		}
		System.out.println(dijkstra(0));  // 0번 지점에서 시작
	}

	private static int dijkstra(int x) {
		boolean[] visited = new boolean[d + 1];
		// 직선 거리를 기본 거리로 설정
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(x, 0));
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if(visited[now.idx]) continue;
			visited[now.idx] = true;

			for(Node next : graph.get(now.idx)) {
				if(dist[next.idx] > dist[now.idx] + next.cost) {
					dist[next.idx] = dist[now.idx] + next.cost;
					pq.offer(new Node(next.idx, dist[next.idx]));
				}
			}

			// // 직선 거리로 다음 위치까지 갱신 (최우선적으로 확인)
			// if (now.idx + 1 <= d && dist[now.idx + 1] > dist[now.idx] + 1) {
			// 	dist[now.idx + 1] = dist[now.idx] + 1;
			// 	pq.offer(new Node(now.idx + 1, dist[now.idx + 1]));
			// }
		}
		return dist[d];
	}
}
