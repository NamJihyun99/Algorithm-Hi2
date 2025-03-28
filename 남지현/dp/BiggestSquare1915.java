import java.util.*;
import java.io.*;

// 백준 1915번 가장 큰 정사각형
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp =  new int[n][m];
        for (int i=0; i<n; i++) {
            String row = br.readLine();
            for (int j=0; j<m; j++) {
                if (row.charAt(j) == '1') { dp[i][j] = 1; }
            }
        }
        // dp[i][j] : i,j가 제일 우측 하단에 있는 정사각형의 최대 크기
        for (int i=1; i<n; i++) {
            for (int j=1; j<m; j++) {
                if (dp[i][j]==1) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }
        int max = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(max * max);
    }
}
