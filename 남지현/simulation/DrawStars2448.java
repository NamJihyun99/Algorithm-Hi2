import java.util.*;

// 백준 2448 - 별 찍기-11
class Main {

    static int N;
    static boolean[][] board;

    private static void draw(int row, int col, int size) {
        if (size==3) {
            board[row-2][col] = true;
            board[row-1][col-1] = true;
            board[row-1][col+1] = true;
            for (int i=col-2; i<=col+2; i++) {
                board[row][i] = true;
            }
            return;
        }
        draw(row-size/2, col, size/2);
        draw(row, col-size/2, size/2);
        draw(row, col+size/2, size/2);
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new boolean[N+1][2*N];
        draw(N, N, N);
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            for (int j=1; j<2*N; j++) {
                if (board[i][j]) sb.append('*');
                else sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        sc.close();
    }
}
