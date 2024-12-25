// 프로그래머스 - 산 모양 타일링

class Solution {
    
    static final int MOD = 10_007;
    
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n][2];
        dp[0][0] = 1; // 좌로 기울어진 마름모 타일을 사용하는 경우
        dp[0][1] = 2 + tops[0]; // top이 존재하면 세운 마름모도 사용 가능
        for (int i=1; i<n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % MOD;
            dp[i][1] = (dp[i-1][0]*(tops[i]+1) + dp[i-1][1]*(tops[i]+2)) % MOD; 
            // top이 존재하면 세운 마름모도 사용할 수 있으므로 tops[i]를 더하여 계산.
        }
        return (dp[n-1][0] + dp[n-1][1]) % MOD;
    }
}
