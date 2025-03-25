import java.io.*;
import java.util.*;

class Main {
    static int[] arr;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new long[N - 1][21]; // dp[i][j]: arr[0]부터 arr[i]까지의 수들로 j를 만들 수 있는 가짓수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }
        arr[N - 1] = Integer.parseInt(st.nextToken());
        dynamicProgramming(N - 2, arr[N - 1]);
        System.out.println(dp[N - 2][arr[N - 1]]);
    }
    
    private static long dynamicProgramming(int idx, int num) {
        if (num < 0 || num > 20) {
            return 0;
        }
        if (dp[idx][num] == -1) {
            if (idx == 0) {
                dp[0][num] = (arr[0] == num)? 1 : 0;
            } else {
                dp[idx][num] = dynamicProgramming(idx - 1, num - arr[idx]);
                dp[idx][num] += dynamicProgramming(idx - 1, num + arr[idx]);
            }
        }

        return dp[idx][num];
    }
}