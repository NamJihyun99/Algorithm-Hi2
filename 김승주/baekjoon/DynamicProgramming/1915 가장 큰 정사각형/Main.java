import java.io.*;
import java.util.*;

class Main {
    static int n;
    static int m;
    static char[][] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        dp = new int[n][m]; // (n, m)을 오른쪽 아래 점으로 하여 만들 수 있는 정사각형의 최대 크기
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = input.charAt(j);
                if (arr[i][j] == '1') {
                    dp[i][j] = -1;
                    makeMaxSquare(i, j);
                    answer = Math.max(answer, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        System.out.println(answer * answer);
    }

    private static int makeMaxSquare(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) {
            return 0;
        }

        if (dp[row][col] == -1) {
            dp[row][col] = (row == 0 || col == 0)? 1 : Math.min(makeMaxSquare(row - 1, col), Math.min(makeMaxSquare(row, col - 1), makeMaxSquare(row - 1, col - 1))) + 1;
        }

        return dp[row][col];
    }
}