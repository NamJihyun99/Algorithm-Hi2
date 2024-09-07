import java.util.*;

// 프로그래머스 - 양궁대회

class Solution {
    
    int[] answer, apeach;
    int num, max;
    
    public int[] solution(int n, int[] info) {
        answer = new int[]{-1};
        apeach = info;
        num = n;
        max = 0;
        dfs(n, new int[11], 0);
        return answer;
    }
    
    private void dfs(int sum, int[] lionResult, int depth) {
        if (sum == 0) {
            int lionScore=0; int apeachScore=0;
            for (int i=0; i<=10; i++) {
                if (apeach[i]==0 && lionResult[i]==0) continue;
                if (apeach[i] >= lionResult[i]) {
                    apeachScore += 10-i;
                } else {
                    lionScore += 10-i;
                }
            }
            int result = lionScore-apeachScore;
            if (result>max || result>0 && result==max && canUpdate(answer, lionResult)) {
                max = result;
                answer = lionResult;
            }
            return;
        }
        if (depth == 10) {
            int[] tmpResult = Arrays.copyOf(lionResult, 11);
            tmpResult[depth] = sum;
            dfs(0, tmpResult, depth+1);
            return;
        }
        for (int i=0; i<=sum; i++) {
            int[] tmpResult = Arrays.copyOf(lionResult, 11);
            tmpResult[depth] = i;
            dfs(sum-i, tmpResult, depth+1);
        }
    }
    
    private boolean canUpdate(int[] oldArr, int[] newArr) {
        if (oldArr.length==1) {
            return true;
        }
        for (int i=10; i>=0; i--) {
            if (oldArr[i] != newArr[i]) {
                return (oldArr[i] < newArr[i]);
            }
        }
        return false;
    }
}
