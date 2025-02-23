import java.util.*;
import java.io.*;

// 백준 2240번 자두나무
class Main {
  
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] arr = new int[T+1];
        int[][][] dp = new int[T+1][W+1][3];
        arr[0] = 1;
        for (int i=1; i<=T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 초기화: 1초에 자두가 1번에서 떨어질 때와 2번에서 떨어질 때
        if (arr[1] == 1) {
            dp[1][0][1] = 1;
            dp[1][1][2] = 0;
        } else {
            dp[1][0][1] = 0;
            dp[1][1][2] = 1;
        }
        
        for (int i=2; i<=T; i++) {
            if (arr[i] == 1) {
                dp[i][0][1] = dp[i-1][0][1] + 1;
                dp[i][0][2] = dp[i-1][0][2];
                for (int j=1; j<=W; j++) {
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2]) + 1;
                    dp[i][j][2] = Math.max(dp[i-1][j-1][1], dp[i-1][j][2]);
                }
            } else {
                dp[i][0][1] = dp[i-1][0][1];
                dp[i][0][2] = dp[i-1][0][2] + 1;
                for (int j=1; j<=W; j++) {
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2]);
                    dp[i][j][2] = Math.max(dp[i-1][j-1][1], dp[i-1][j][2]) + 1;
                }
            }
        }
        
        int answer = 0;
        for (int i=0; i<=W; i++) {
            answer = Math.max(answer, Math.max(dp[T][i][1], dp[T][i][2]));
        }
        System.out.println(answer);
    }
}
