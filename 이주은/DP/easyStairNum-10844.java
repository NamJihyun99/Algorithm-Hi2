//백준 10844번 쉬운 계단 수

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static final int MOD = 1_000_000_000;
	static int[][] dp = new int[101][10]; // dp[i][j] : i자리 수의 끝자리수가 j인 수의 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i < 10; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {

			dp[i][0] = dp[i - 1][1];
			dp[i][9] = dp[i - 1][8];

			for (int j = 1; j < 9; j++) {
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
			}
		}

		int sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += dp[N][i];
			sum%=MOD;
		}

		System.out.println(sum);

	}
}
