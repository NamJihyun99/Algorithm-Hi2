import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] volume = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            volume[i] = Integer.parseInt(br.readLine());

            if (i == 1) {
                dp[1] = volume[1];
            } else if (i == 2) {
                dp[2] = volume[1] + volume[2];
            } else {
                dp[i] = Math.max(dp[i - 1], Math.max(volume[i] + volume[i - 1] + dp[i - 3], volume[i] + dp[i - 2]));
            }
        }
        
        System.out.println(dp[n]);
    }
}