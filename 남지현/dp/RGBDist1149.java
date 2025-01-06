import java.util.*;
import java.io.*;

// 백준 1149번 - RGB거리
class Main {

    static final int MAX = 1_000_001;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // dp[i][j] : i번째 집에 j 색을 칠할 때의 최소 비용
        int[][] dp = new int[N][3];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp[0][0] = Integer.parseInt(st.nextToken());
        dp[0][1] = Integer.parseInt(st.nextToken());
        dp[0][2] = Integer.parseInt(st.nextToken());
        for (int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + Integer.parseInt(st.nextToken());
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + Integer.parseInt(st.nextToken());
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + Integer.parseInt(st.nextToken());
        }
        
        int answer = MAX;
        for (int i=0; i<3; i++) {
            answer = Math.min(answer, dp[N-1][i]);
        }
        System.out.println(answer);
    }
}
