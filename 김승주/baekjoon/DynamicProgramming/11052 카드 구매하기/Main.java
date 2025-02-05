import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] P = new int[N + 1];
        int[] dp = new int[N + 1];
        // dp[i]: i장의 카드를 구매하기 위해 지불해야 하는 금액의 최댓값

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
            // dp 배열 초기화: P1 카드팩만으로 i개를 구매하기 위해 지불해야 하는 금액
            dp[i] = P[1] * i;
        }

        // P[1]부터 P[i]까지의 카드팩에서 j장의 카드를 구매하기 위해 지불해야 하는 금액의 최댓값 갱신
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // e: P[i]를 구매하는 횟수
                for (int e = 1; e * i <= j; e++) {
                    if (j - e * i > j) {
                        continue;
                    }
                    dp[j] = Math.max(dp[j], P[i] * e + dp[j - e * i]);
                }
            }
        }
        
        System.out.println(dp[N]);
    }
}