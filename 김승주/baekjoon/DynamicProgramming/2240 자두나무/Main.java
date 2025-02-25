import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] dp = new int[T + 1][W + 1]; // dp[i][j]: i초까지 j번 움직여 받을 수 있는 자두의 최대 개수
        int[] tree = new int[T + 1];
        tree[0] = 1;
        for (int i = 1; i <= T; i++) {
            tree[i] = Integer.parseInt(br.readLine());
            dp[i][0] = (tree[i] == 1)? dp[i - 1][0] + 1 : dp[i - 1][0];
        }
        
        for (int sec = 1; sec < T; sec++) {
            for (int move = 1; move <= sec && move <= W; move++) {
                dp[sec][move] = Math.max(dp[sec - 1][move], dp[sec - 1][move - 1]);
                if (move % 2 == 0) {
                    dp[sec][move] += (tree[sec] == 1)? 1 : 0;
                } else {
                    dp[sec][move] += (tree[sec] == 2)? 1 : 0;
                }
            }
        }

        int result = dp[T][0];
        for (int move = 1; move <= T && move <= W; move++) {
            dp[T][move] = Math.max(dp[T - 1][move], dp[T - 1][move - 1]);
                if (move % 2 == 0) {
                    dp[T][move] += (tree[T] == 1)? 1 : 0;
                } else {
                    dp[T][move] += (tree[T] == 2)? 1 : 0;
                }
            result = Math.max(result, dp[T][move]);
        }

        System.out.println(result);
    }
}