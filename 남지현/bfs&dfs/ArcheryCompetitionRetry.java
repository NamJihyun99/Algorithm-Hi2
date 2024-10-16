import java.util.*;

// 프로그래머스 - 양궁대회 (복습)

class Solution {
    
    int[] apeachInfo, answer;
    int diff;
    
    public int[] solution(int n, int[] info) {
        answer = new int[]{-1};
        apeachInfo = info;
        diff = 0;
        dfs(0, n, new int[11]);
        return answer;
    }
    
    private void dfs(int idx, int remains, int[] lionInfo) {
        if (idx==10) {
            lionInfo[10] = remains;
            int apeach = 0; int lion = 0;
            for (int i=0; i<=10; i++) {
                if (apeachInfo[i]==0 && lionInfo[i]==0) { continue; }
                if (lionInfo[i] > apeachInfo[i]) {
                    lion += 10-i;
                } else {
                    apeach += 10-i;
                }
            }
            if (lion>apeach) {
                if (diff < lion-apeach) {
                    answer = lionInfo;
                    diff = lion-apeach;
                } else if (diff == lion-apeach && canChange(answer, lionInfo)) {
                    answer = lionInfo;
                }
            }
            return;
        }
        for (int score=0; score<=remains; score++) {
            int[] tmpInfo = Arrays.copyOf(lionInfo, 11);
            tmpInfo[idx] = score;
            dfs(idx+1, remains-score, tmpInfo);
        }
    }
    
    private boolean canChange(int[] oldResult, int[] newResult) {
        if (oldResult.length==1) {
            return true;
        }
        for (int i=10; i>=0; i--) {
            if (oldResult[i] < newResult[i]) {
                return true;
            } else if (oldResult[i] > newResult[i]) {
                return false;
            }
        }
        return false;
    }
}
