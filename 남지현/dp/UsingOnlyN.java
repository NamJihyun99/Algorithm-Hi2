import java.util.*;

// 프로그래머스 - N으로 표현 (복습)

class Solution {
    public int solution(int N, int number) {
        if (N==number) return 1;
        int answer = 0;
        List<Integer>[] dp = new List[9];
        for (int i=1; i<=8; i++) {
            dp[i] = new ArrayList<>();
        }
        dp[1].add(N);
        for (int num=2; num<=8; num++) {
            int concatN = dp[num-1].get(0) + N * (int) Math.pow(10, num-1);
            if (concatN==number) return num;
            dp[num].add(concatN);
            for (int i=1; i<num; i++) {
                for (int x: dp[i]) {
                    for (int y: dp[num-i]) {
                        if (x+y==number || x-y==number || x*y==number || y!=0 && x/y==number) {
                            return num;
                        }
                        if (!dp[num].contains(x+y)) dp[num].add(x+y);
                        if (!dp[num].contains(x-y)) dp[num].add(x-y);
                        if (!dp[num].contains(x*y)) dp[num].add(x*y);
                        if (y!=0 && !dp[num].contains(x/y)) dp[num].add(x/y);
                    }
                }
            }
        }
        return -1;
    }
}
