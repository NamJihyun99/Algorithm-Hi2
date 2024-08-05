// https://www.notion.so/2616-07b28208a340436dac5101502c8cf892?pvs=4
import java.io.*;
import java.util.*;

class Main {
    static int n;
    static int m;
    static int[] train;
    static int[][] dp;
    static int[] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        train = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            train[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        dp = new int[n][4];
        sum = new int[n];
        
        // 0번째 객석에서 i번째 객석까지 모든 승객의 합
        sum[0] = train[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + train[i];
        }
        System.out.println(findMaxCustomers(n - 1, 3));
    }

    // 0번 객차부터 trainNum번 객차의 승객들을 maxNum개의 소형 기관차로 최대 몇 명까지 태울 수 있는지?
    private static int findMaxCustomers(int trainNum, int maxNum) {
        if (maxNum <= 0)    return 0;

        if (dp[trainNum][maxNum] == 0) {
            if (trainNum == 0) {
                dp[trainNum][maxNum] = train[trainNum];
            } else if (trainNum + 1 <= m * maxNum) {
                dp[trainNum][maxNum] = sum[trainNum];
            } else {
                dp[trainNum][maxNum] = Math.max(findMaxCustomers(trainNum - 1, maxNum), 
                findMaxCustomers(trainNum - m, maxNum - 1) + (sum[trainNum] - sum[trainNum - m]));
            }    
        }
        return dp[trainNum][maxNum];
    }
}