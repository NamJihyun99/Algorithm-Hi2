package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기2
// 왜 K를 visited 배열에서 함께 고려해주어야 하는가?
public class BaekJoon14442 {
	static int n,m,k;
	static int[][] map;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		answer = -1;
		for(int i= 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(line.charAt(j) + "");
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[n][m][k + 1];
		q.offer(new int[]{0,0,k,1});
		visited[0][0][k] = true;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int cx = now[0];
			int cy = now[1];
			int ck = now[2];
			int cd = now[3];
			if (cx == n - 1 && cy == m - 1) {
				if (map[cx][cy] == 0) {
					return answer = cd;
				}
			}
			for (int[] d : dir) {
				int nx = cx + d[0];
				int ny = cy + d[1];
				if (!canMove(nx, ny)) continue;
				if (visited[nx][ny][ck]) continue;
				if (map[nx][ny] == 0) {
					visited[nx][ny][ck] = true;
					q.offer(new int[]{nx, ny, ck, cd + 1});
				}
				else if (map[nx][ny] == 1) {
					if (ck > 0) {
						visited[nx][ny][ck] = true;
						q.offer(new int[]{nx, ny, ck - 1, cd + 1});
					}
				}
			}
		}
		return answer;
	}

	private static boolean canMove(int nx, int ny) {
		if (nx < 0 || nx >= n || ny < 0 || ny >= m) return false;
		return true;
	}
}
