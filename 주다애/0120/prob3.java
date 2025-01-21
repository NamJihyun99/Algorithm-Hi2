import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 연구소(골드 4)
public class Main {
	static int n;
	static int m;
	static int[][] virus;
	static int[][] temp;
	static int max = 0;
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		virus = new int[n][m];
		temp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				virus[i][j] = sc.nextInt();
			}
		}
		dfs(0);
		System.out.println(max);
	}

	// 벽 세울 공간 3곳 탐색
	private static void dfs(int level) {
		if (level == 3) {
			bfs();
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (virus[i][j] == 0) {
					virus[i][j] = 1;
					dfs(level + 1);
					virus[i][j] = 0;
				}
			}
		}
	}

	// 안전 영역 크기 탐색
	private static void bfs() {
		int safeSpace = 0;
		for (int i = 0; i < n; i++) {
			temp[i] = virus[i].clone();
		}
		boolean[][] visited = new boolean[n][m];
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (virus[i][j] == 2) {
					q.offer(new int[]{i, j});
					visited[i][j] = true;
				}
			}
		}

		while (!q.isEmpty()) {
			int[] now = q.poll();
			int cx = now[0];
			int cy = now[1];
			for (int[] d : dir) {
				int nx = cx + d[0];
				int ny = cy + d[1];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (temp[nx][ny] == 0 && !visited[nx][ny]) {
					temp[nx][ny] = 2;
					q.offer(new int[]{nx, ny});
					visited[nx][ny] = true;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (temp[i][j] == 0) {
					safeSpace += 1;
				}
			}
		}
		max = Math.max(max, safeSpace);
	}
}
