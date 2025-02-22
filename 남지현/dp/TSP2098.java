import java.util.*;
import java.io.*;

// 백준 2098번 외판원 순회
class Main {
    
    static final int INF = 1_000_000_000;
    
    static int[][] C, dp;
    static int N, max;

    private static int topDown() {
        for (int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }
        return getMinCost(0, 1);
    }

    private static int getMinCost(int city, int bit) {
        if (bit == Math.pow(2, N)-1) {
            if (C[city][0] != 0) {
                return C[city][0];
            } else {
                return INF;
            }
        }
        if (dp[city][bit] != -1) {
            return dp[city][bit];
        }
        dp[city][bit] = INF;
        for (int i=0; i<N; i++) {
            if ((bit & 1<<i)==0 && C[city][i]!=0) {
                dp[city][bit] = Math.min(dp[city][bit], getMinCost(i, bit|1<<i)+C[city][i]);
            }
        }
        return dp[city][bit];
    }

    private static int bottomUp() {
        for (int i=0; i<N; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][1] = 0;
        for (int bit=0; bit<=max; bit++) {
            for (int cur=0; cur<N; cur++) {
                if ((bit&1<<cur)==0) continue;
                for (int prev=0; prev<N; prev++) {
                    if ((bit&1<<prev)!=0 && cur!=prev && C[prev][cur]!=0) {
                        dp[cur][bit] = Math.min(dp[cur][bit], dp[prev][bit^1<<cur]+C[prev][cur]);
                    }
                }
            }
        }
        int answer  = INF;
        for (int i=0; i<N; i++) {
            if (C[i][0]!=0) {
                answer = Math.min(answer, dp[i][max]+C[i][0]);
            }
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        C = new int[N][N];
        max = (1<<N)-1;
        dp = new int[N][max+1];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                C[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(topDown());
        System.out.println(bottomUp());
    }
}
