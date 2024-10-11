import java.util.*;

// 프로그래머스 - 에어컨

class Solution {
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        final int MAX = 101*1001;
        int low = t1+10; int high = t2+10; 
        int temp = temperature+10; int time = onboard.length;
        int[][] dp = new int[time][51];
        for (int i=0; i<time; i++) {
            Arrays.fill(dp[i], MAX); 
        }
        dp[0][temp] = 0;
        for (int i=0; i<time-1; i++) {
            for (int j=0; j<51; j++) {
                if (onboard[i]==1 && (j<low || j>high)) {
                    continue;
                }
                // 1. 에어컨을 끄는 경우
                int next = j;
                if (j < temp) {
                    next = j+1;
                } else if (j > temp) {
                    next = j-1;
                }
                dp[i+1][next] = Math.min(dp[i+1][next], dp[i][j]);
              
                // 2. 온도를 1도 올리는 경우
                if (j < 50) {
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+a);
                }
              
                // 3. 온도를 1도 내리는 경우
                if (j > 0) {
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]+a);
                }
              
                // 4. 온도를 유지하는 경우
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+b);
            }
        }
        int answer = MAX;
        for (int i=0; i<=50; i++) {
            if (onboard[time-1] == 1 && (i<low || i>high)) {
                continue;
            }
            answer = Math.min(dp[time-1][i], answer);
        }
        return answer;
    }
}
