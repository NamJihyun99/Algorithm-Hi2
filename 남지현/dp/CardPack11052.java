import java.util.*;
import java.io.*;

// 백준 11052번 - 카드 구매하기
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) { // dp[i]: 카드 i장을 사는데 지불하는 금액의 최댓값
            dp[i] = Integer.parseInt(st.nextToken());
            for (int j=1; j<=i/2; j++) {
                dp[i] = Math.max(dp[i], dp[j]+dp[i-j]);
            }
        }
        System.out.println(dp[N]);
    }
}
