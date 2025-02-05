import java.util.Scanner;

// RGB 거리(실버 1)
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] color = new int[n + 1][3];
		// 입력
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j < 3; j++) {
				color[i][j] = sc.nextInt();
			}
		}
		int[][] dp = new int[3][n + 1];
		dp[0][1] = color[1][0];
		dp[1][1] = color[1][1];
		dp[2][1] = color[1][2];
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j < 3; j++) {
				dp[j][i] = color[i][j] + Math.min(dp[(j + 1) % 3][i - 1], dp[(j + 2) % 3][i - 1]);
			}
		}
		System.out.println(Math.min(dp[0][n], Math.min(dp[1][n],  dp[2][n])));
	}
}
