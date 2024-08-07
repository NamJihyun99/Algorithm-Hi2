import java.util.*;
import java.io.*;

// 백준 17144번 - 미세먼지 안녕!

class Main {

    static int R, C, purifier = 0;
    static int[][] A;

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    private static void solution() {
        int[][] result = new int[R][C];
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                int divide = A[i][j]/5;
                int sum = 0;
                if (divide > 0) {
                    for (int d=0; d<4; d++) {
                        int nx = i+dx[d];
                        int ny = j+dy[d];
                        if (canDiffuse(nx, ny)) {
                            sum += divide;
                            result[nx][ny] += divide;
                        }
                    }
                }
                result[i][j] += A[i][j]-sum;
            }
        }
        for (int i=1; i<purifier; i++) {
            A[i][0] = result[i-1][0];
        }
        for (int i=purifier+2; i<R-1; i++) {
            A[i][0] = result[i+1][0];
        }
        for (int i=0; i<purifier; i++) {
            A[i][C-1] = result[i+1][C-1];
        }
        for (int i=purifier+2; i<R; i++) {
            A[i][C-1] = result[i-1][C-1];
        }
        for (int j=0; j<C-1; j++) {
            A[0][j] = result[0][j+1];
            A[R-1][j] = result[R-1][j+1];
        }
        for (int j=2; j<C; j++) {
            A[purifier][j] = result[purifier][j-1];
            A[purifier+1][j] = result[purifier+1][j-1];
        }
        for (int i=1; i<R-1; i++) {
            if (i==purifier || i==purifier+1) continue;
            System.arraycopy(result[i], 1, A[i], 1, C-2);
        }
        A[purifier][1] = 0;
        A[purifier+1][1] = 0;
    }

    private static boolean canDiffuse (int row, int col) {
        return row>=0 && row<R && col>=0 && col<C && A[row][col]!=-1;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new int[R][C];
        int T = Integer.parseInt(st.nextToken());
        for (int i=0; i<R; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j=0; j<C; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
            if (A[i][0] == -1 && purifier == 0) 
                purifier = i;
        }
        for (int t=0; t<T; t++) {
            solution();
        }
        int sum = 0;
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (A[i][j] == -1) continue;
                sum += A[i][j];
            }
        }
        System.out.println(sum);
    }
}
