import java.util.*;

// 백준 10844번 - 쉬운 계단수

class Main {

    static final int MOD = 1_000_000_000;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int[N+1][10];
        for (int i=1; i<10; i++) {
            dp[1][i] = 1;
        }
        for (int i=2; i<=N; i++) {
            for (int j=0; j<10; j++) {
                if (j-1 >= 0) {
                    dp[i][j-1] += dp[i-1][j];
                    dp[i][j-1] %= MOD;
                }
                if (j+1<10) {
                    dp[i][j+1] += dp[i-1][j];
                    dp[i][j+1] %= MOD;
                }
            }
        }
        long sum = 0;
        for (int i=0; i<10; i++) {
            sum+=dp[N][i];
            sum%=MOD;
        }
        System.out.println(sum);
    }
}
