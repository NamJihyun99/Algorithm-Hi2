import java.util.*;
import java.io.*;

// SWEA 3282번 0/1 Knapsack

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] dp = new int[N][K+1];
            int[] V = new int[N];
            int[] C = new int[N];
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                V[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());
            }
            for (int i=0; i<N; i++) {
                for (int j=0; j<=K; j++) {
                    if (i==0) {
                        if (j>=V[0]) dp[i][j] = C[0];
                    } else if (j >= V[i]) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-V[i]]+C[i]);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
            int answer = 0;
            for (int i=0; i<=K; i++) {
                answer = Math.max(answer, dp[N-1][i]);
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
