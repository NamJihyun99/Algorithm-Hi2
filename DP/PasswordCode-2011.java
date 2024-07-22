//백준 2011번 암호코드 (https://www.acmicpc.net/problem/2011)

import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] dp;
    static final int MOD = 1000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        
        N = num.length();
        dp = new int[N];
        
        char prev = num.charAt(0);
        if(prev != '0')
            dp[0] = 1;

        if(N==1) {
            System.out.println(dp[0]);
            return;
        }

        char now = num.charAt(1);
        if(now != '0')
            dp[1] += dp[0];
        if((prev == '1') || (prev == '2' && now <= '6'))
            dp[1] += 1;
        prev = now;
        
        for(int i=2; i<N; i++) {
            now = num.charAt(i);
        
            if(now != '0')
                dp[i] += dp[i-1];
            if((prev == '1') || (prev == '2' && now <= '6'))
                dp[i] += dp[i-2];


            if(dp[i] == 0)
                break;
            
            prev = now;

            dp[i] %= MOD;
        }

        System.out.print(dp[N-1]);
    }
}
