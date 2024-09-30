import java.io.*;
import java.util.*;

class Main {
    static int[] input = new int[100002];
    static int[][][] dp = new int[5][5][100002];
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; ; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            if (input[i] == 0) {
                break;
            }
        }
        ddr(0, 0, 1);
        System.out.println(min);
    }
    private static void ddr(int left, int right, int cnt) {
        if (input[cnt] == 0) {
            min = Math.min(min, dp[left][right][cnt - 1]);
            return;
        }

        if (dp[left][right][cnt - 1] >= min) {
            return;
        }

        if (input[cnt] == 1) {
            if (left == 0) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 2;
            } else if (left == 1) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 1;
            } else if (left == 2 || left == 4) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 3;
            } else {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 4;
            }
            if (right == 0) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 2;
            } else if (right == 1) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 1;
            } else if (right == 2 || right == 4) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 3;
            } else {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 4;
            }
        } else if (input[cnt] == 2) {
            if (left == 0) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 2;
            } else if (left == 2) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 1;
            } else if (left == 1 || left == 3) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 3;
            } else {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 4;
            }
            if (right == 0) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 2;
            } else if (right == 2) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 1;
            } else if (right == 1 || right == 3) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 3;
            } else {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 4;
            }
        } else if (input[cnt] == 3) {
            if (left == 0) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 2;
            } else if (left == 3) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 1;
            } else if (left == 2 || left == 4) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 3;
            } else {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 4;
            }
            if (right == 0) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 2;
            } else if (right == 3) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 1;
            } else if (right == 2 || right == 4) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 3;
            } else {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 4;
            }
        } else {
            if (left == 0) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 2;
            } else if (left == 4) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 1;
            } else if (left == 1 || left == 3) {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 3;
            } else {
                dp[input[cnt]][right][cnt] = dp[left][right][cnt - 1] + 4;
            }
            if (right == 0) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 2;
            } else if (right == 4) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 1;
            } else if (right == 1 || right == 3) {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 3;
            } else {
                dp[left][input[cnt]][cnt] = dp[left][right][cnt - 1] + 4;
            }
        }        
        ddr(left, input[cnt], cnt + 1);
        ddr(input[cnt], right, cnt + 1);
    }
}