import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] A;
    static int[] dp;
    static int prevIdx = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());    
        boolean isIncreasing = true;
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                dp[0] = 1;
                continue;
            } else if (i == 1 && A[1] < A[0]) {
                isIncreasing = false;
            }
            isIncreasing = bitonic(i, isIncreasing);
        }
        System.out.println(dp[N - 1]);
    }
    private static boolean bitonic(int currIdx, boolean isIncreasing) {
        if (A[currIdx] > A[prevIdx]) {
            if (isIncreasing) {
                prevIdx = currIdx;
                dp[currIdx] = dp[currIdx - 1] + 1;
                return true;
            } else { // 감소하고 있었는데 증가하는 수를 만난 경우
                // 이전까지의 수 중 현재 수를 포함하는 새로운 바이토닉 수열의 최대 길이 
                int newLength = findIncreasingOrDecreasingSubset(currIdx, true);
                if (newLength > dp[currIdx - 1]) {
                    prevIdx = currIdx;
                    dp[currIdx] = newLength;
                    return true;
                } else {
                    dp[currIdx] = dp[currIdx - 1];
                    return false;
                }
            }
        } else if (A[currIdx] < A[prevIdx]) {
            prevIdx = currIdx;
            if (isIncreasing) { // 증가하고 있었는데 감소하는 수를 만난 경우
                // 이전까지의 수 중 현재 수를 포함하는 감소하는 수열의 최대 길이 vs 현재까지의 바이토닉 수열의 길이 + 1
                int newLength = findIncreasingOrDecreasingSubset(currIdx, false);
                if (newLength > dp[currIdx - 1] + 1) {
                    dp[currIdx] = newLength;
                } else {
                    dp[currIdx] = dp[currIdx - 1] + 1;
                }
            } else {
                dp[currIdx] = dp[currIdx - 1] + 1;
            }
            return false;
        } else {
            dp[currIdx] = dp[currIdx - 1];
            return isIncreasing;
        }
    }

    private static int findIncreasingOrDecreasingSubset(int end, boolean isIncreasing) {
        if (end == 0) {
            return 1;
        }
        int max = 0;
            for (int i = end - 1; i >= -1; i--) {
                if (i == -1)    max = Math.max(max, 1);
                else if ((A[i] < A[end] && isIncreasing) || (A[i] > A[end] && !isIncreasing)) {
                    max = Math.max(findIncreasingOrDecreasingSubset(i, isIncreasing) + 1, max);
                }
            }
            return max;
    }
}