import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 미세먼지 안녕!(골드 4)
// 코드가 마음에 들진 않지만 일단 통과
public class BaekJoon17144 {
	static int r, c, t;
	static int map[][];
	static int temp[][];
	static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		// 입력
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (t --> 0) {
			temp = new int[r][c];
			List<int[]> list = new ArrayList<>();
			// 1. 미세먼지 확산
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if(map[i][j] != 0 && map[i][j] != -1) {
						list.add(new int[]{i, j});
					}
				}
			}
			for (int[] l : list) {
				startDiffusion(l[0], l[1]);
			}
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (map[i][j] != -1) {
						map[i][j] = temp[i][j];
					}
				}
			}
			// 2. 공기청정기 청소
			for (int i = 0; i < r; i++) {
				if(map[i][0] == -1) {
					clean(i, true);
					clean(i + 1, false);
					break;
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(map[i][j] != -1) sum += map[i][j];
			}
		}
		System.out.println(sum);
	}

	private static void clean(int x, boolean up) {
		if(up) {
			for (int i = x - 1; i >= 0; i--) {
				if(map[i + 1][0] == -1) continue; // 공기청정기
				map[i + 1][0] = map[i][0];
			}
			for (int i = 0; i < c - 1; i++) {
				map[0][i] = map[0][i + 1];
			}
			for (int i = 1; i <= x; i++) {
				map[i - 1][c - 1] = map[i][c - 1];
			}
			for (int i = c - 2; i >= 1 ; i--) {
				map[x][i + 1] = map[x][i];
				if(i == 1) map[x][i] = 0; // 0으로 채우기
			}
		}
		else {
			for (int i = x + 1; i < r; i++) {
				if(map[i - 1][0] == -1) continue;
				map[i - 1][0] = map[i][0];
			}
			for (int i = 0; i < c - 1; i++) {
				map[r - 1][i] = map[r - 1][i + 1];
			}
			for (int i = r - 2; i >= x; i--) {
				map[i + 1][c - 1] = map[i][c - 1];
			}
			for (int i = c - 2; i >= 1 ; i--) {
				map[x][i + 1] = map[x][i];
				if(i == 1) map[x][i] = 0;
			}
		}
	}

	private static void startDiffusion(int x, int y) {
		int cnt = 0;
		int amount = map[x][y];
		int diff = amount / 5;
		for (int[] d : dir) {
			int nr = x + d[0];
			int nc = y + d[1];
			if(!canMove(nr, nc)) continue;
			if(map[nr][nc] == -1) continue;
			temp[nr][nc] += diff;
			cnt++;
		}
		temp[x][y] += map[x][y] - diff * cnt;
	}

	private static boolean canMove(int x, int y) {
		if(x < 0 || x >= r || y < 0 || y >= c) return false;
		return true;
	}
}
