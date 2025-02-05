import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1]; //dp[i]는 i개의 카드를 얻을 수 있는 최대 금액

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=2; i<=N; i++) {
            for(int j=1; j<i; j++) {
                 dp[i] = Math.max(dp[i], dp[i-j] + dp[j]);
            }
        }

        System.out.println(dp[N]);     
    }
}
