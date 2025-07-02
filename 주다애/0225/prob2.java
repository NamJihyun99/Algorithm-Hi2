import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 자두나무(골드 5)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int[] arr = new int[t + 1];
		// 입력
		for (int i = 1; i <= t; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[][][] dp = new int[2][t + 1][w + 1]; // 1or2번, 시간, 이동 횟수
		for (int i = 1; i <= t; i++) {
			for (int j = 0; j <= w; j++) {
				// 이동하지 않은 경우
				// 항상 1번 나무에 있음
				if (j == 0) {
					dp[0][i][0] = dp[0][i - 1][0] + (arr[i] == 1 ? 1 : 0);
					dp[1][i][0] = 0;
					continue;
				}
				if (arr[i] == 1) {
					dp[0][i][j] = Math.max(dp[0][i - 1][j], dp[1][i - 1][j - 1]) + 1;
					dp[1][i][j] = Math.max(dp[0][i - 1][j - 1], dp[1][i - 1][j]);
				}
				else {
					dp[0][i][j] = Math.max(dp[0][i - 1][j], dp[1][i - 1][j - 1]);
					dp[1][i][j] = Math.max(dp[0][i - 1][j - 1], dp[1][i - 1][j]) + 1;
				}
			}
		}
		int ans = 0;
		// 모든 이동 횟수에 대해 최댓값을 찾아야 한다.
		// 최대 w번이므로
		for (int j = 0; j <= w; j++) {
			ans = Math.max(ans, Math.max(dp[0][t][j], dp[1][t][j]));
		}
		System.out.println(ans);
	}
}
