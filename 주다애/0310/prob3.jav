import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 문제집(골드 2)
public class Main {
	static int[] parent;
	static int[] depth;
	static int n;
	static List<List<Integer>> graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		depth = new int[n + 1];
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(p).add(c);
			depth[c] += 1;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i < n + 1; i++) {
			if (depth[i] == 0) pq.add(i);
		}
		StringBuilder sb = new StringBuilder();

		while (!pq.isEmpty()) {
			int now = pq.poll();
			if (depth[now] == 0) sb.append(now).append(" ");
			for (int c : graph.get(now)) {
				depth[c] -= 1;
				if (depth[c] == 0) pq.offer(c);
			}
		}
		System.out.println(sb);
	}
}
