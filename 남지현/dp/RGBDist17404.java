import java.util.*;
import java.io.*;

// 백준 17404번 RGB거리 2
class Main {

    private static final int MAX_COST = 1_000_002;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] costs = new int[N][3];
        // 첫번째 집을 i로 칠한 경우, j번째 집을 k로 칠할 수 있는 최소 비용
        int[][][] dp = new int[3][N][3];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[j][i], MAX_COST);
            }
        }
        int answer = MAX_COST;
        for (int i=0; i<3; i++) {
            dp[i][0][i] = costs[0][i];
            for (int j=1; j<N-1; j++) {
                for (int k=0; k<3; k++) {
                    for (int l=0; l<3; l++) {
                        if (k==l || dp[i][j-1][l]==MAX_COST) continue;
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][j-1][l]);
                    }
                    dp[i][j][k] += costs[j][k];
                }
            }
            for (int j=0; j<3; j++) {
                if (i==j) continue;
                for (int k=0; k<3; k++) {
                    if (j==k || dp[i][N-2][k]==MAX_COST) continue;
                    dp[i][N-1][j] = Math.min(dp[i][N-1][j], dp[i][N-2][k]);
                }
                dp[i][N-1][j] += costs[N-1][j];
                answer = Math.min(answer, dp[i][N-1][j]);
            }
        }
        System.out.println(answer);
    }
}
