import java.io.*;

// 백준 2156번 포도주 시식
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] arr = new int[N+1];
        for (int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = arr[1];
        if (N >= 2) {
            dp[2] = arr[1] + arr[2];
        }
        for (int i=3; i<=N; i++) {
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2], dp[i-3] + arr[i-1]) + arr[i]);
        }
        System.out.println(dp[N]);
    }
}
