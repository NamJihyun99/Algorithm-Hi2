import java.util.*;
import java.io.*;

// 백준 14391번 종이조각 (복습)
class Main {

    static int N, M, answer;
    static int[][] board;
    static boolean[][] visited;

    static void dfs(int idx, int sum) {
        if (idx == N*M) {
            answer = Math.max(answer, sum);
            return;
        }
        int row = idx / M;
        int col = idx % M;
        if (visited[row][col]) {
            dfs(idx+1, sum);
        } else {
            int number = board[row][col];
            dfs(idx+1, sum+number);
            int i=row+1;
            for (; i<N; i++) {
                if (visited[i][col]) break;
                visited[i][col] = true;
                number = number*10 + board[i][col];
                dfs(idx+1, sum+number);
            }
            for (int j=row+1; j<i; j++) {
                visited[j][col] = false;
            }

            number = board[row][col];
            i=col+1;
            int len = 1;
            for (; i<M; i++) {
                if (visited[row][i]) break;
                visited[row][i] = true;
                number = number*10 + board[row][i];
                dfs(idx+len, sum+number);
                len++;
            }
            for (int j=col+1; j<i; j++) {
                visited[row][j] = false;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i=0; i<N; i++) {
            String input = br.readLine();
            for (int j=0; j<M; j++) {
                board[i][j] = input.charAt(j)-'0';
            }
        }
        visited = new boolean[N][M];
        answer = 0;
        dfs(0, 0);
        System.out.println(answer);
    }
}
