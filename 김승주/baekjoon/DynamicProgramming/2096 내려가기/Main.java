import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] line = new int[N][3];
        int[][][] dp = new int[N][3][2]; // dp[a][b]: a행에서 b번째 값을 선택했을 때 0행~a행까지에서 얻을 수 있는 최대 or 최소 점수
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                line[i][j] = Integer.parseInt(st.nextToken());
            }

            if (i == 0) {
                dp[0][0][0] = dp[0][0][1] = line[0][0];
                dp[0][1][0] = dp[0][1][1] = line[0][1];
                dp[0][2][0] = dp[0][2][1] = line[0][2];
            } else {
                dp[i][0][0] = Math.max(dp[i - 1][0][0] + line[i][0], dp[i - 1][1][0] + line[i][0]);
                dp[i][0][1] = Math.min(dp[i - 1][0][1] + line[i][0], dp[i - 1][1][1] + line[i][0]);
                dp[i][1][0] = Math.max(dp[i - 1][0][0] + line[i][1], Math.max(dp[i - 1][1][0] + line[i][1], dp[i - 1][2][0] + line[i][1]));
                dp[i][1][1] = Math.min(dp[i - 1][0][1] + line[i][1], Math.min(dp[i - 1][1][1] + line[i][1], dp[i - 1][2][1] + line[i][1]));
                dp[i][2][0] = Math.max(dp[i - 1][1][0] + line[i][2], dp[i - 1][2][0] + line[i][2]);
                dp[i][2][1] = Math.min(dp[i - 1][1][1] + line[i][2], dp[i - 1][2][1] + line[i][2]);
            }
        } 
        StringBuilder sb = new StringBuilder();
        sb.append(Math.max(dp[N - 1][0][0], Math.max(dp[N - 1][1][0], dp[N - 1][2][0]))).append(" ")
        .append(Math.min(dp[N - 1][0][1], Math.min(dp[N - 1][1][1], dp[N - 1][2][1])));
        System.out.println(sb.toString());
    }
}