import java.util.Arrays;
import java.util.Scanner;

// 쉬운 계단 수(실버 1)
public class BaekJoon10844 {
	static final long MOD_NUM = 1000000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[][] dp = new long[n + 1][10]; // 자릿수, 자릿값
		// 한 자리수는 1
		for (int i = 1; i < 10; i++) {
			dp[1][i] = 1;
		}
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < 10; j++) {
				// 0 옆에는 1만 가능
				if(j == 0) {
					dp[i][j] = dp[i - 1][1] % MOD_NUM;
					continue;
				}
				// 9 옆에는 8만 가능
				if(j == 9) {
					dp[i][j] = dp[i - 1][8] % MOD_NUM;
					continue;
				}
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD_NUM;
			}
		}
		System.out.println(Arrays.stream(dp[n]).sum() % MOD_NUM);
	}
}
