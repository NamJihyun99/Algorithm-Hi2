import java.util.*;

// 프로그래머스 - 코딩 테스트 공부

class Solution {
    
    static final int MAX_VALUE = 30000;
    
    public int solution(int alp, int cop, int[][] problems) {
        int alpGoal = -1, copGoal = -1;
        for (int[] problem: problems) {
            alpGoal = Math.max(alpGoal, problem[0]);
            copGoal = Math.max(copGoal, problem[1]);
        }
        if (alpGoal <= alp && copGoal <= cop) {
            return 0;
        }
        if (alp > alpGoal) {
            alp = alpGoal;
        }
        if (cop > copGoal) {
            cop = copGoal;
        }
        // dp[x][y] : 알고력 x+alp와 코딩력 y+cop를 갖기 위한 최소 시간
        int[][] dp = new int[alpGoal-alp+2][copGoal-cop+2];
        for (int i=0; i<=alpGoal-alp+1; i++) {
            for (int j=0; j<=copGoal-cop+1; j++) {
                dp[i][j] = MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for (int i=0; i<=alpGoal-alp; i++) {
            for (int j=0; j<=copGoal-cop; j++) {
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                
                for (int[] problem: problems) {
                    if (i+alp < problem[0] || j+cop < problem[1]) continue;
                    int x = Math.min(i+problem[2], alpGoal-alp);
                    int y = Math.min(j+problem[3], copGoal-cop);
                    dp[x][y] = Math.min(dp[x][y], dp[i][j]+problem[4]);
                }
            }
        }
        return dp[alpGoal-alp][copGoal-cop];
    }
}
