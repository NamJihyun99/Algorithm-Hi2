package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon9456 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(n --> 0) {
			int k = Integer.parseInt(br.readLine());
			StringTokenizer st;
			int[][] map = new int[2][k];
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < k; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] dp = new int[2][k + 1];
			dp[0][0] = map[0][0];
			dp[1][0] = map[1][0];
			for(int i = 1; i < k; i++) {
				dp[0][i] = Math.max(map[0][i] + dp[1][i - 1], dp[0][i - 1]);
				dp[1][i] = Math.max(map[1][i] + dp[0][i - 1], dp[1][i - 1]);
			}
			int s = Math.max(dp[0][k - 1],  dp[1][k - 1]);
			System.out.println(s);
		}
		
	}
}
