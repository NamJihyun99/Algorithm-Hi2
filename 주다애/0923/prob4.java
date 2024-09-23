import java.util.*;

class Solution {
    static int[][] res;
    public int solution(int n, int[][] results) {
        int answer = 0;
        res = new int[n + 1][n + 1];
        for(int [] r : results) {
            int w = r[0];
            int l = r[1];
            res[w][l] = 1; // win
            res[l][w] = -1; // lose
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                for(int k = 1; k <= n; k++) {
                    // k : 거쳐가는 엣지
                    if(res[i][k] == 1 && res[k][j] == 1) {
                        res[i][j] = 1;
                        res[j][i] = -1;
                    }
                    // 아래 if문을 왜 넣어야 하는지 잘 모르겠어요.
                    if(res[i][k] == -1 && res[k][j] == -1) {
                        res[i][j] = -1;
                        res[j][i] = 1;
                    }
                }
            }
        }
        for(int i = 1; i <= n; i++) {
            int cnt = 0;
            for(int j = 1; j <= n ;j++) {
                if(res[i][j] == 1) cnt++;
                if(res[j][i] == 1) cnt++;
            }
            if(cnt == n - 1) answer++;
        }
        return answer;
    }
}
