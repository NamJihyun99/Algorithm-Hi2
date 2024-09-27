import java.util.*;
import java.io.*;

// 백준 14501번 - 퇴사

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] T = new int[N+1];
        int[] P = new int[N+1];
        int[] dp = new int[N+2];
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        for (int i=N; i>0 ;i--) {
            if (i+T[i]-1 <= N) {
                dp[i] = Math.max(dp[i], dp[i+T[i]]+P[i]);
            }
            dp[i-1] = dp[i];
        }
        System.out.println(dp[0]);
    }
}
