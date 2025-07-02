import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 점수따먹기(골드 4)
// 누적합
// 부분합
public class Main {
	static int n;
	static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n + 1][m + 1];
		int[][] psum = new int[n + 1][m + 1];
		// 입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// psum[x][y] = (1,1) ~ (x,y)까지의 합
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				psum[i][j] = psum[i - 1][j] + psum[i][j - 1] - psum[i - 1][j - 1] + arr[i][j];
			}
		}
		int max = -400000000;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				for (int k = 0; k < i; k++) {
					for (int p = 0; p < j; p++) {
						int sum = psum[i][j] - psum[k][j] - psum[i][p] + psum[k][p];
						max = Math.max(max, sum);
					}
				}
			}
		}
		System.out.println(max);
	}
}
