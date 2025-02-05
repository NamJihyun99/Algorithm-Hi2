import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 중량제한(G 3)
// 1. 21%에서 틀림
public class Main {
	static int n;
	static int m;
	static int temp = 0;
	static List<List<Island>> graph = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		int left = 0;
		int right = 0;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Island(b, c));
			graph.get(b).add(new Island(a, c));
			right = Math.max(right, c);
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		boolean[] visited;
		int ans = 0;

		/* 이진 탐색 */
		while (left <= right) {
			int mid = (left + right) / 2;
			// System.out.println(mid);
			visited = new boolean[n + 1];
			/* 견딜 수 있는지 dfs 탐색 */
			dfs(s, e, mid, visited);
			if (temp == 1) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
			temp = 0;
		}
		System.out.println(right);
	}

	private static void dfs(int s, int e, int mid, boolean[] visited) {
		visited[s] = true;
		if (s == e) {
			temp = 1;
			return;
		}
		for (Island island : graph.get(s)) {
			if (!visited[island.dest] && island.cost >= mid) {
				dfs(island.dest, e, mid, visited);
			}
		}
	}

	static class Island {
		int dest;
		int cost;

		public Island(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
}
