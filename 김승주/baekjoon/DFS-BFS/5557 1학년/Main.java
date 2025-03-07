import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] arr;
    static int[][] dp;
    static long answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N][21]; // dp[i][j]: arr[i]부터 arr[N - 2]까지의 수들로 j를 만들 수 있는 가짓수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }
        dfs(0, 0);
        System.out.println(answer);
    }
    
    private static void dfs(int num, int idx) {
        if (idx == N - 1) {
            if (num == arr[idx]) {
                answer++;
            }
            return;
        }
        if (num + arr[idx] <= 20) {
            dfs(num + arr[idx], idx + 1);
        }
        if (num - arr[idx] >= 0) {
            dfs(num - arr[idx], idx + 1);
        }
    }

    private static int dynamicProgramming(int idx, int num) {
        if (dp[idx][num] == -1) {
            if (idx == N - 2) {
                dp[idx][num] = (arr[idx] == num)? 1 : 0;
            } else {
                dp[idx][num] = dynamicProgramming(idx + 1, num + arr[idx]);
            }

        }

        return dp[idx][num];
 
    }
}