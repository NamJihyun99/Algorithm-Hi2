import java.io.*;

class Main {
    static int[][] board;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
        System.out.println(dynamicProgramming(N - 1, N - 1));
    }

    private static long dynamicProgramming(int row, int col) {
        if ((row == 0 && col == board[0][0]) || (row == board[0][0] && col == 0)) {
            return 1;
        }

        if (dp[row][col] == 0) {
            for (int i = 0; i < row; i++) {
                if (row - i == board[i][col]) {
                    dp[row][col] += dynamicProgramming(i, col);
                }
            }
    
            for (int i = 0; i < col; i++) {
                if (col - i == board[row][i]) {
                    dp[row][col] += dynamicProgramming(row, i);
                }
            }
        }

        return dp[row][col];
    }
}
