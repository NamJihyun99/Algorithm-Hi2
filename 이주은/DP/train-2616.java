//백준 2616번 소형기관차

import java.util.*;
import java.io.*;

class Main {
    static int N, K;
    static int[] train;
    static int[] mini;
    static int[][] dp; //dp[i][j] i번째 소형기관차까지 j인덱스의 객차를 끝으로 하는 부분 기차의 최대 승객 수
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        train = new int[N];
        mini = new int[N+1];
        dp = new int[3][N+1];

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(br.readLine());

        int sum = 0;
        for(int i=0; i<K; i++) {
            train[i] = Integer.parseInt(st.nextToken());
            sum += train[i];
        }
        mini[K-1] = sum;
        for(int i=K; i<N; i++) {
            train[i] = Integer.parseInt(st.nextToken());
            sum = sum - train[i-K] + train[i];
            mini[i] = sum;
        }

        for(int i=K-1; i<N-K-K; i++) {
            dp[0][i] = Math.max(mini[i], dp[0][i-1]);
        }

        for(int i=K+K-1; i<N-K; i++) {
            dp[1][i] = Math.max(dp[0][i-K] + mini[i], dp[1][i-1]);
        }

        for(int i=K+K+K-1; i<N; i++) {
            dp[2][i] = Math.max(dp[1][i-K] + mini[i], dp[2][i-1]);
        }

        System.out.println(dp[2][N-1]);
    }
}
