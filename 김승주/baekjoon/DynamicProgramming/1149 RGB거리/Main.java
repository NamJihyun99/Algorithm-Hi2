import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][3];

        String[] input = br.readLine().split(" ");
        dp[0][0] = Integer.parseInt(input[0]);
        dp[0][1] = Integer.parseInt(input[1]);
        dp[0][2] = Integer.parseInt(input[2]);

        for (int i = 1; i < N; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0]);
            int g = Integer.parseInt(input[1]);
            int b = Integer.parseInt(input[2]);

            dp[i][0] = Math.min(dp[i - 1][1] + r, dp[i - 1][2] + r);
            dp[i][1] = Math.min(dp[i - 1][0] + g, dp[i - 1][2] + g);
            dp[i][2] = Math.min(dp[i - 1][0] + b, dp[i - 1][1] + b);
        }

        System.out.println(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));
        // System.out.println(Math.min(topDown(N - 1, 0), Math.min(topDown(N - 1, 1), topDown(N - 1, 2))));
    }

    // private static int topDown(int num, int color) {
    //     if (dp[num][color] == 0) {
    //         if (color == 0) {
    //             dp[num][color] = Math.min(topDown(num - 1, 1), topDown(num - 1, 2)) + cost[num][0];
    //         } else if (color == 1) {
    //             dp[num][color] = Math.min(topDown(num - 1, 0), topDown(num - 1, 2)) + cost[num][1];
    //         } else {
    //             dp[num][color] = Math.min(topDown(num - 1, 0), topDown(num - 1, 1)) + cost[num][2];
    //         }
    //     }
    //     return dp[num][color];
    // }
}
