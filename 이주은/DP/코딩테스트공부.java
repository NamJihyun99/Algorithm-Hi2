//https://school.programmers.co.kr/learn/courses/30/lessons/118668

class Solution {
    static final int MAX_POW = 150;
    static final int MAX_TIME = 300;
    
    public int solution(int alp, int cop, int[][] problems) {
        //[시간][알고][코딩]
        boolean[][][] dp = new boolean[302][152][152];
        dp[0][alp][cop] = true;
        
        
        int max_alp = 0;
        int max_cop = 0;
        
        for(int[] problem : problems) {
            max_alp = Math.max(max_alp, problem[0]);
            max_cop = Math.max(max_cop, problem[1]);
        }
        
        for(int t=0; t<=300; t++) {
            for(int a=0; a<=150; a++) {
                for(int c=0; c<=150; c++) {
                    
                    if(!dp[t][a][c])
                        continue;
                    
                    if(a >= max_alp && c >= max_cop)
                        return t;
                    
                    dp[t+1][a+1][c] = true;
                    dp[t+1][a][c+1] = true;
                    
                    for(int[] problem : problems) {
                        int time = t + problem[4];
                        
                        if(time > 300 || a < problem[0] || c < problem[1])
                            continue;
                        
                        int nextA = Math.min(MAX_POW, a + problem[2]);
                        int nextC = Math.min(MAX_POW, c + problem[3]);
                        
                        dp[time][nextA][nextC] = true;
                    }
                }
            }
        }
        
        return MAX_TIME;
    }
}
