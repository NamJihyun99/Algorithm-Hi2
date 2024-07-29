import java.util.Scanner;

// 소형기관차(골드 3)
// DP
public class BaekJoon2616 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] train = new int[n + 1];
        int[] psum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            train[i] = sc.nextInt();
            psum[i] = train[i] + psum[i - 1]; // 누적합
        }
        int k = sc.nextInt();
        int[][] dp = new int[4][n + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = i * k; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - k] + psum[j] - psum[j - k]);
            }
        }
        System.out.println(dp[3][n]);
    }
}
