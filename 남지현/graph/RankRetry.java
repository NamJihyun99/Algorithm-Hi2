import java.util.*;

// 프로그래머스 - 순위 (복습)

class Solution {
    
    public int solution(int n, int[][] results) {
        boolean[][] known = new boolean[n+1][n+1];
        for (int[] result: results) {
            known[result[0]][result[1]] = true;
        }
        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (known[i][k] && known[k][j]) {
                        known[i][j] = true;
                    }
                }
            }
        }
        int count=0;
        for (int i=1; i<=n; i++) {
            int sum=0;
            for (int j=1; j<=n; j++) {
                if (known[j][i] || known[i][j]) sum++;
            }
            if (sum == n-1) count++;
        }
        return count;
    }
}
