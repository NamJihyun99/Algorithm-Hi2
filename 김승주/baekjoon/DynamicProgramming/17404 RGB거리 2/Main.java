import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][3];
        int[][] cost = new int[N + 1][3];
        int firstR;
        int firstG;
        int firstB;
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            cost[i][0] = Integer.parseInt(input[0]);
            cost[i][1] = Integer.parseInt(input[1]);
            cost[i][2] = Integer.parseInt(input[2]);
        }

        if (N == 2) {
            dp[1][0] = cost[1][0];
            dp[1][1] = cost[1][1];
            dp[1][2] = cost[1][2];
            dp[2][0] = Math.min(dp[1][1], dp[1][2]) + cost[2][0];
            dp[2][1] = Math.min(dp[1][0], dp[1][2]) + cost[2][1];
            dp[2][2] = Math.min(dp[1][0], dp[1][1]) + cost[2][2];
            System.out.println(Math.min(dp[2][0], Math.min(dp[2][1], dp[2][2])));
            return;
        }
		
        // 1번 집을 빨간색으로 칠하는 경우
        dp[2][0] = 2001; // 2번 집은 빨간색으로 칠할 수 없음
        dp[2][1] = cost[1][0] + cost[2][1]; // 1번 집 빨강, 2번 집 초록
        dp[2][2] = cost[1][0] + cost[2][2]; // 1번 집 빨강, 2번 집 파랑
        for (int i = 3; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }
        firstR = Math.min(dp[N][1], dp[N][2]);

		// 1번 집을 초록색으로 칠하는 경우
        dp[2][0] = cost[1][1] + cost[2][0]; // 1번 집 초록, 2번 집 빨강
        dp[2][1] = 2001; // 2번 집은 초록색으로 칠할 수 없음
        dp[2][2] = cost[1][1] + cost[2][2]; // 1번 집 초록, 2번 집 파랑
        for (int i = 3; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }
        firstG = Math.min(dp[N][0], dp[N][2]);

		// 1번 집을 파란색으로 칠하는 경우
        dp[2][0] = cost[1][2] + cost[2][0]; // 1번 집 파랑, 2번 집 빨강
        dp[2][1] = cost[1][2] + cost[2][1]; // 1번 집 파랑, 2번 집 초록
        dp[2][2] = 2001; // 2번 집은 파란색으로 칠할 수 없음
        for (int i = 3; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }
        firstB = Math.min(dp[N][0], dp[N][1]);

        System.out.println(Math.min(firstR, Math.min(firstG, firstB)));
    }
}