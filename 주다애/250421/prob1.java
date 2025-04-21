import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 사회망 서비스(SNS) (골드 3)
public class BaekJoon2533 {
	static List<List<Integer>> graph;
	static int n;
	static boolean[] visited;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		visited = new boolean[n + 1];
		dp = new int[2][n + 1];
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		// root는 1번부터 시작
		dfs(1);
		System.out.println(Math.min(dp[0][1], dp[1][1]));
	}

	private static void dfs(int x) {
		visited[x] = true;
		dp[0][x] = 1;
		for (int c : graph.get(x)) {
			if (visited[c]) continue;
			// 일단 다 돌아야 함
			dfs(c);
			dp[1][x] += dp[0][c];
			dp[0][x] += Math.min(dp[1][c], dp[0][c]);
		}
	}
}
