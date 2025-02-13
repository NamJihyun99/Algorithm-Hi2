import java.util.*;
import java.io.*;

// 백준 17404번 RGB거리 2
class Main {

    private static final int MAX_COST = 1_000_002;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] costs = new int[N][3];
        int[][] dp = new int[N][3];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int answer = MAX_COST;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (i==j) dp[0][j] = costs[0][j];
                else dp[0][j] = MAX_COST;
            }
            for (int j=1; j<N; j++) {
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + costs[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + costs[j][1];
                dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + costs[j][2];
            }
            for (int j=0; j<3; j++) {
                if (i==j) continue;
                answer = Math.min(answer, dp[N-1][j]);
            }
        }
        System.out.println(answer);
    }
}
