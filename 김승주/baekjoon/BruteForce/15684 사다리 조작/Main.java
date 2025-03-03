import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int H;
    static int[][] ladder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H + 2][N + 2];
        for (int i = 0; i <= H + 1; i++) {
            for (int j = 0; j <= N + 1; j++) {
                ladder[i][j] = j;
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = b + 1;
            ladder[a][b + 1] = b;
        }

        for (int cnt = 0; cnt < 4; cnt++) {
            addLine(cnt, 0, 1, 1);
        }
        System.out.println(-1);
    }

    private static void addLine(int goal, int curr, int row, int col) {
        if (row > H) {
            return;
        }

        if (goal == curr) {
            if (isManipulationCompleted()) {
                System.out.println(goal);
                System.exit(0);
            }
            return;
        }
        
        if (ladder[row][col] != col + 1 
                && ladder[row][col - 1] != col && ladder[row][col + 1] != col + 2) {
            ladder[row][col] = col + 1;
            ladder[row][col + 1] = col;
            addLine(goal, curr + 1, (col == N)? row + 1 : row, (col == N)? 1 : col + 1);
            ladder[row][col] = col;
            ladder[row][col + 1] = col + 1;
        }
        addLine(goal, curr, (col == N)? row + 1 : row, (col == N)? 1 : col + 1);
    }

    private static boolean isManipulationCompleted() {
        for (int i = 1; i <= N; i++) {
            int loc = i;
            for (int j = 1; j <= H; j++) {
                loc = ladder[j][loc];
            }
            if (loc != i) {
                return false;
            }
        }
        return true;
    }
}