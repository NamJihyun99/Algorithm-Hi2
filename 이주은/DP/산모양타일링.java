class Solution {
    public int solution(int n, int[] tops) {
        int N = 2*n + 1;
        
        int[] dp = new int[N+2];
        dp[0] = 1;
        for(int i=1; i<=N; i++) {
            //홀수
            if(i%2 == 1) {
                //정삼각형
                dp[i] += dp[i-1];
                
                //마름모2
                dp[i+1] += dp[i-1];
                
            }
            //짝수 + 위에 없음
            else if(tops[i/2 - 1] == 0) {
                //정삼각형
                dp[i] += dp[i-1];
                //마름모3
                dp[i+1] += dp[i-1];
            }
            //짝수 + 위에 있음.
            else {
                //정삼각형 + 마름모4
                dp[i] += dp[i-1] * 2;
                //마름모3
                dp[i+1] += dp[i-1];
            }
            dp[i] %= 10007;
        }
    
        return dp[N];
    }
}
