import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 스타트링크(실버 1)
public class Main {
	static int f, s, g, d, u;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		visited = new boolean[f + 1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{s, 0});
		visited[s] = true;
		int[] dir = new int[2];
		dir[0] = u;
		dir[1] = -1 * d;

		int ans = -1;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (now[0] == g) {
				ans = now[1];
				break;
			}
			for (int i = 0; i < 2; i++) {
				int next = now[0] + dir[i];
				if (next < 1 || next > f) continue;
				if (visited[next]) continue;
				q.offer(new int[]{next, now[1] + 1});
				visited[next] = true;
			}
		}
		if (ans == -1)
			System.out.println("use the stairs");
		else {
			System.out.println(ans);
		}
	}
}
