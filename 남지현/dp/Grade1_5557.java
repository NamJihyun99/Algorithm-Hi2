import java.util.*;
import java.io.*;

// 백준 5557번 1학년
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N-1];
        for (int i=0; i<N-1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int result = Integer.parseInt(st.nextToken());
        long[][] dp = new long[N-1][21];
        // dp[i][j] : i번째 숫자를 계산했을 때 j가 나오는 가짓수
        dp[0][nums[0]] = 1l;
        for (int i=1; i<N-1; i++) {
            for (int n=0; n<=20; n++) {
                if (dp[i-1][n] == 0l) continue;
                if (n-nums[i] >= 0) dp[i][n-nums[i]] += dp[i-1][n];
                if (n+nums[i] <= 20) dp[i][n+nums[i]] += dp[i-1][n];
            }
        }
        System.out.println(dp[N-2][result]);
    }
}
