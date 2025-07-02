import java.io.*;
import java.util.*;

class Main {
    static int result = 0;
    static int n;
    static int m;
    static int[][] arr;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = Math.max(result, putExclusiveTetromino(i, j));
                visited[i][j] = true;
                putTetromino(i, j, 1, arr[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(result);
    }

    private static void putTetromino(int row, int col, int count, int sum) {
        if (count == 4) {
            result = Math.max(result, sum);
            return;
        }
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int nextX = row + dx[i];
            int nextY = col + dy[i];
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && !visited[nextX][nextY]) {
                visited[nextX][nextY] = true;
                putTetromino(nextX, nextY, count + 1, sum + arr[nextX][nextY]);
                visited[nextX][nextY] = false;
            }
        }
    }

    private static int putExclusiveTetromino(int row, int col) {
        int sum = 0;

        if (row - 1 >= 0 && col - 1 >= 0) {
            // ㅜ
            if (col + 1 < m) {
                sum = Math.max(sum, arr[row][col] + arr[row - 1][col - 1] + arr[row - 1][col] + arr[row - 1][col + 1]);
            }

            // ㅏ
            if (row + 1 < n) {
                sum = Math.max(sum, arr[row][col] + arr[row - 1][col - 1] + arr[row][col - 1] + arr[row + 1][col - 1]);
            }
        }

        if (row + 1 < n && col + 1 < m) {
            // ㅗ
            if (col - 1 >= 0) {
                sum = Math.max(sum, arr[row][col] + arr[row + 1][col - 1] + arr[row + 1][col] + arr[row + 1][col + 1]);
            }

            // ㅓ
            if (row - 1 >= 0) {
                sum = Math.max(sum, arr[row][col] + arr[row - 1][col + 1] + arr[row][col + 1] + arr[row + 1][col + 1]);
            }
        }

        return sum;
    }
}