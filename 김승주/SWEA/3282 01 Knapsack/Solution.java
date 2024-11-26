import java.io.*;

class Solution {
    static int[] dp;
    static int N;
    static int K;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            K = Integer.parseInt(input[1]);
            System.out.println("#" + test_case + " " + knapsack(br));
		}
	}
    
    private static int knapsack(BufferedReader br) throws IOException {
        dp = new int[K + 1];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int v = Integer.parseInt(input[0]); // 부피
            int c = Integer.parseInt(input[1]); // 가치
            for (int j = K; j  >= v; j--) {
                dp[j] = Math.max(dp[j] , c + dp[j - v]);
            }
        }
        return dp[K];
    }
}