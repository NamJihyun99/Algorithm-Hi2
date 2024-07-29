import java.util.*;
import java.io.*;

// 백준 2616번 - 소형 기관차

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] passengers = new int[N+1];
        int[][] dp = new int[4][N+1];
        for (int i=1; i<=N; i++) {
            passengers[i] = passengers[i-1]+Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(bf.readLine());
        for (int i=1; i<=3; i++) {
            for (int j=i*K; j<=N; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-K]+passengers[j]-passengers[j-K]);
            }
        }
        for (int i=0; i<=3; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(dp[3][N]);
    }
}
