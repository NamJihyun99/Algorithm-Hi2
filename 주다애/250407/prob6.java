import java.util.Scanner;

// 테트로미노
// ㅗ는 연속적으로 움직일 수 없어서 따로 처리해야 한다.
// 완전 탐색
public class BaekJoon14500 {
	static int n, m;
	static int[][] map;
	static boolean[][] used;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int answer;
	public static void main(String[] args) {
		answer = 0;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		used = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				used[i][j] = true;
				run(i, j, 1, map[i][j]);
				used[i][j] = false;
				checkRemain(i, j);
			}
		}
		System.out.println(answer);
	}

	// ㅗ ㅏ ㅓ ㅜ
	private static void checkRemain(int x, int y) {
		if (y + 2 < m && x - 1 >= 0) {
			int sum = map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x - 1][y + 1];
			answer = Math.max(answer, sum);
		}
		if (x + 2 < n && y + 1 < m) {
			int sum = map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 1][y + 1];
			answer = Math.max(answer, sum);
		}
		if (x - 1 >= 0 && x + 1 < n && y + 1 < m) {
			int sum = map[x][y] + map[x][y + 1] + map[x - 1][y + 1] + map[x + 1][y + 1];
			answer = Math.max(answer, sum);
		}
		if (y + 2 < m && x + 1 < n) {
			int sum = map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x + 1][y + 1];
			answer = Math.max(answer, sum);
		}
	}

	private static void run(int x, int y, int level, int sum) {
		if (level == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if (used[nx][ny]) continue;
			used[nx][ny] = true;
			run(nx, ny, level + 1, sum + map[nx][ny]);
			used[nx][ny] = false;
		}
	}
}
