import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 내려가기(골드 5)
// prob3
public class BaekJoon2096 {
	static int[][] dp;
	static int[][] dp2;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n + 1][3];
		dp2 = new int[n + 1][3];
		map = new int[n + 1][3];
		// 입력
		StringTokenizer st;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 3; i++) {
			dp[1][i] = map[1][i];
			dp2[1][i] = map[1][i];
		}
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < 3; j++) {
				if(j == 0) {
					dp[i][j] = Math.max(dp[i - 1][0] , dp[i - 1][1]);
					dp2[i][j] = Math.min(dp2[i - 1][0] , dp2[i - 1][1]);
				}
				if(j == 1) {
					dp[i][j] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] , dp[i - 1][2]));
					dp2[i][j] = Math.min(dp2[i - 1][0], Math.min(dp2[i - 1][1] , dp2[i - 1][2]));
				}
				if(j == 2) {
					dp[i][j] = Math.max(dp[i - 1][1] , dp[i - 1][2]);
					dp2[i][j] = Math.min(dp2[i - 1][1] , dp2[i - 1][2]);
				}
				dp[i][j] += map[i][j];
				dp2[i][j] += map[i][j];
			}
		}
		int max = Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
		int min = Math.min(dp2[n][0], Math.min(dp2[n][1], dp2[n][2]));
		System.out.println(max + " " + min);
	}
}
