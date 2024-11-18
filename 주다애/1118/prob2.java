package test;

// 0/1 Knapsack
import java.util.*;

public class Swea3282 {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();
			int k = sc.nextInt();
			int[] volume = new int[n + 1];
			int[] cost = new int[n + 1];
			for(int i = 1; i <= n; i++) {
				volume[i] = sc.nextInt();
				cost[i] = sc.nextInt();
			}
			int[][] dp = new int[n + 1][k + 1];
			dp[0][0] = 0;
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= k; j++) {
					int v = volume[i];
					int c = cost[i];
					// 내가 들어갈 수 있음
					if(j >= v) {
						dp[i][j] = Math.max(dp[i - 1][j - v] + c, dp[i - 1][j]);
					}
					// 내가 못 들어감
					else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
			System.out.println("#" + test_case + " " + dp[n][k]);
		}
	}
}
