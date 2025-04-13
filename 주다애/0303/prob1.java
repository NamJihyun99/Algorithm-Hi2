import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사다리 조작(골드 3)
public class Main {
	static int n, m, h;
	static int answer;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		arr = new int[h][n];
		answer = 4;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a - 1][b - 1] = 1; // ->
			arr[a - 1][b] = 2; // <-
		}

		dfs(0, 0);
		System.out.println((answer != 4) ? answer : -1);
	}

	private static void dfs(int level, int start) {
		if (answer <= level) return;
		if (check()) {
			answer = level;
			return;
		}
		if (level == 3) return;

		for (int i = start; i < h * n; i++) {
			int x = i / n;
			int y = i % n;
			if (y == n - 1) continue;
			if (arr[x][y] != 0 || arr[x][y + 1] != 0) continue;
			arr[x][y] = 1;
			arr[x][y + 1] = 2;
			dfs(level + 1, i + 1);
			arr[x][y] = 0;
			arr[x][y + 1] = 0;
		}
	}

	private static boolean check() {
		for (int i = 0; i < n; i++) {
			int cy = i;
			for (int cx = 0; cx < h; cx++) {
				if (cy < n && arr[cx][cy] == 1) cy += 1;
				else if (cy >= 0 && arr[cx][cy] == 2) cy -= 1;
			}
			if (cy != i) return false;
		}
		return true;
	}
}
