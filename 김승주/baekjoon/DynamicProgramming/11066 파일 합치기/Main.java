import java.io.*;
import java.util.*;

class Main {
    static int[] files;
    static int[][] dp;
    static int[] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            files = new int[K + 1];
            dp = new int[K + 1][K + 1];
            sum = new int[K + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int k = 1; k <= K; k++) {
                files[k] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[k], Integer.MAX_VALUE);
                sum[k] = sum[k - 1] + files[k];
            }
            sb.append(mergeFiles(1, K)).append('\n');
        }
        System.out.print(sb.toString());
    }

    private static int mergeFiles(int start, int end) {
        if (dp[start][end] == Integer.MAX_VALUE) {
            if (start == end) {
                dp[start][end] = 0;
            } else if (start + 1 == end) {
                dp[start][end] = files[start] + files[end];
            } else {
                int subSum = sum[end] - sum[start - 1];
                dp[start][end] = mergeFiles(start + 1, end) + subSum;
                for (int i = start + 1; i < end; i++) {
                    dp[start][end] = Math.min(dp[start][end], mergeFiles(start, i) + mergeFiles(i + 1, end) + subSum);
                }
            }
        }
        return dp[start][end];
    }
}