import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + Integer.parseInt(input[j - 1]);
            }
        }

        int maxSum = 0;
        String[] input = br.readLine().split(" ");
        for (int j = 1; j <= n; j++) {
            maxSum = Math.max(maxSum, Math.max(dp[n - 1][j - 1], dp[n - 1][j]) + Integer.parseInt(input[j - 1]));
        }

        System.out.println(maxSum);
    }
}