import java.util.*;

// 프로그래머스 - 완전범죄

class Solution {
    
    public int solution(int[][] info, int n, int m) {
        int count = info.length;
        int[][] dp = new int[count+1][m];
        for (int i=0; i<=count; i++) {
            Arrays.fill(dp[i], 200);
        }
        // dp[i][j] = i+1번째 물건을 훔칠 때, B가 남긴 흔적의 수가 j개면 A가 남긴 흔적의 개수
        dp[0][0] = 0;
        for (int i=1; i<=count; i++) {
            for (int j=0; j<m; j++) {
                if (dp[i-1][j] == 200) continue;
                if (dp[i-1][j]+info[i-1][0]<n) 
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + info[i-1][0]);
                if (j+info[i-1][1] < m)
                    dp[i][j+info[i-1][1]] = Math.min(dp[i][j], dp[i-1][j]);
            }
        }
        int min = dp[count][0];
        for (int i=1; i<m; i++) {
            min = Math.min(min, dp[count][i]);
        }
        return min==200? -1 : min;
    }
}
