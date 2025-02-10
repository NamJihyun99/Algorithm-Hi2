import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// RGB거리 2(골드 4)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] house = new int[n][3];
		// 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] dp = new int[n][3];
		int min = 1000001;
		for (int k = 0; k < 3; k++) {
			// 무조건 픽스된 집이 선택되도록 나머지는 큰 수를 저장해버린다.
			for (int i = 0; i < 3; i++) {
				if (i == k) dp[0][i] = house[0][i];
				else dp[0][i] = 1000001;
			}
			for (int i = 1; i < n; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + house[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + house[i][1];
				dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + house[i][2];
			}
			for (int i = 0; i < 3; i++) {
				if (i != k) {
					min = Math.min(min, dp[n - 1][i]);
				}
			}
		}
		System.out.println(min);
	}
}
