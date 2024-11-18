import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            String[][] sticker = new String[2][n];
            int[][] dp = new int[n][2];

            sticker[0] = br.readLine().split(" ");
            sticker[1] = br.readLine().split(" ");

            if (n == 1) {
                sb.append(Math.max(Integer.parseInt(sticker[0][0]), Integer.parseInt(sticker[1][0]))).append("\n");
                continue;
            }

            dp[0][0] = Integer.parseInt(sticker[0][0]);
            dp[0][1] = Integer.parseInt(sticker[1][0]);
            dp[1][0] = Integer.parseInt(sticker[1][0]) + Integer.parseInt(sticker[0][1]);
            dp[1][1] = Integer.parseInt(sticker[0][0]) + Integer.parseInt(sticker[1][1]);

            for (int j = 2; j < n; j++) {
                int upper = Integer.parseInt(sticker[0][j]);
                int lower = Integer.parseInt(sticker[1][j]);
                dp[j][0] = Math.max(upper + dp[j - 1][1], Math.max(upper + dp[j - 2][0], upper + dp[j - 2][1]));
                dp[j][1] = Math.max(lower + dp[j - 1][0], Math.max(lower + dp[j - 2][0], lower + dp[j - 2][1]));
            }

            sb.append(Math.max(dp[n - 1][0], dp[n - 1][1])).append("\n");
        }
        System.out.println(sb.toString());
    }
}