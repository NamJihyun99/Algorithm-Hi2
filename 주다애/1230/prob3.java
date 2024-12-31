import java.util.Scanner;

// 점프(실버 1)
// 경로는 long 타입!
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		long[][] dp = new long[n + 1][n + 1];
		dp[1][1] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int now = arr[i][j]; // 현재 숫자
				if (now == 0) break; // 없으면 도착해서 해당 dp 값 * n 이 더해짐
				if (now + i <= n) dp[i + now][j] += dp[i][j];
				if (now + j <= n) dp[i][j + now] += dp[i][j];
			}
		}
		System.out.println(dp[n][n]);
	}
}
