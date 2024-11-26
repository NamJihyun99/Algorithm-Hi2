import java.util.*;
import java.io.*;

// SWEA 11315. 오목 판정

class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            boolean[][] board = new boolean[N][N];
            int[][][] dp = new int[N][N][4];
            for (int i=0; i<N; i++) {
                String line = br.readLine();
                for (int j=0; j<N; j++) {
                    if (line.charAt(j)=='o'){
                        board[i][j] = true;
                        dp[i][j][0] = 1;
                        dp[i][j][1] = 1;
                        dp[i][j][2] = 1;
                        dp[i][j][3] = 1;
                    }
                }
            }
            String result = "NO";
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (board[i][j]) {
                        if (i-1 >= 0 && board[i-1][j]) {
                            dp[i][j][0] += dp[i-1][j][0];
                        }
                        if (j-1 >= 0 && board[i][j-1]) {
                            dp[i][j][1] += dp[i][j-1][1];
                        }
                        if (i-1 >= 0 && j-1 >= 0 && board[i-1][j-1]) {
                            dp[i][j][2] += dp[i-1][j-1][2];
                        }
                        if (i-1 >= 0 && j+1 < N && board[i-1][j+1]) {
                            dp[i][j][3] += dp[i-1][j+1][3];
                        }
                    }
                    if (dp[i][j][0] >= 5 || dp[i][j][1] >= 5 || dp[i][j][2] >= 5 || dp[i][j][3] >= 5) {
                        result = "YES";
                        break;
                    }
                }
                if (result.equals("YES")) {
                    break;
                }
            }
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
        System.out.print(sb);
    }
}
