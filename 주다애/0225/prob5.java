import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 치즈(골드 3)
// 가장자리는 빈 공간이다.
public class BaekJoon2638 {
	static int n;
	static int m;
	static int[][] arr;
	static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static boolean[][] visited;
	static int[][] airCheese;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		while (true) {
			visited = new boolean[n][m];
			airCheese = new int[n][m];
			outside();
			boolean flag = false;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					// 치즈이고, 외부 공기과 2회 이상 접촉하면
					if (arr[i][j] == 1 && airCheese[i][j] >= 2) {
						arr[i][j] = 0; // 녹은 상태로 변경
						flag = true;
					}
				}
			}
			if (!flag) break; // 더이상 녹은 치즈가 없으면 while문 탈출
			ans += 1;
		}
		System.out.println(ans);
	}

	private static void outside() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{0,0});
		visited[0][0] = true;

		while(!q.isEmpty()) {
			int[] now = q.poll();
			int cx = now[0];
			int cy = now[1];
			for (int[] d : dir) {
				int nx = cx + d[0];
				int ny = cy + d[1];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				// 빈 공간이면
				if (arr[nx][ny] == 0 && !visited[nx][ny]) {
					q.offer(new int[]{nx, ny});
					visited[nx][ny] = true;
				}
				if (arr[nx][ny] == 1) {
					airCheese[nx][ny] += 1; // 치즈
				}
			}
		}
	}
}
