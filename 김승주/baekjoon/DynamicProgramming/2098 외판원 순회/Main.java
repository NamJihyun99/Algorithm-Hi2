import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] W;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(tsp(0, 1));
    }

    private static int tsp(int location, int mask) {
        if (mask == ((1 << N) - 1)) {
            if (W[location][0] == 0) {
                return 20000000;
            }
            return W[location][0];
        }

        if (dp[location][mask] == -1) {
            dp[location][mask] = 20000000;
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) == (1 << i) || W[location][i] == 0) {
                    continue;
                }
                dp[location][mask] = Math.min(dp[location][mask], tsp(i, mask | (1 << i)) + W[location][i]);
            }
        }

        return dp[location][mask];
    }
}