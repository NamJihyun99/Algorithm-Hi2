import java.util.*;
import java.io.*;

// 백준 11066번 파일 합치기
class Main {

    static final int MAX_COST = 500_000_002;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0; t<T; t++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] prefixSum = new int[K+1];
            for (int i=1; i<=K; i++) {
                prefixSum[i] = prefixSum[i-1]+Integer.parseInt(st.nextToken());
            }
            int[][] dp = new int[K+1][K+1];
            for (int gap=1; gap<K; gap++) {
                for (int left=1; left+gap<=K; left++) {
                    int right = left+gap;
                    dp[left][right] = MAX_COST;
                    for (int mid=left; mid<right; mid++) {
                        dp[left][right] = Math.min(dp[left][right], dp[left][mid]+dp[mid+1][right]+(prefixSum[right]-prefixSum[left-1]));
                    }
                }
            }
            sb.append(dp[1][K]).append("\n");
        }
        System.out.print(sb);
    }
}
