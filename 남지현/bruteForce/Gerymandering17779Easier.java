import java.util.*;
import java.io.*;

// 백준 17779번 - 게리맨더링 2 (d1, d2를 쉽게 가늠하는 방법)

class Main {

    static int N, x, y, d1, d2;
    static int[] sum;
    static int[][] A;
    static boolean[][] border;

    static final int MAX = 21*21*101;

    private static void mark() {
        border = new boolean[N+1][N+1];

        for (int i=0; i<=d1; i++) {
            border[x+i][y-i] = true;
            border[x+d2+i][y+d2-i] = true;
        }
        for (int i=0; i<=d2; i++) {
            border[x+i][y+i] = true;
            border[x+d1+i][y-d1+i] = true;
        }
        sum[4] += A[x][y]+A[x+d1+d2][y-d1+d2];
        for (int row=x+1; row<x+d1+d2; row++) {
            boolean inBound = false;
            for (int col=1; col<=N; col++) {
                if (border[row][col]) {
                    sum[4] += A[row][col];
                    inBound = !inBound;
                } else if (inBound) {
                    sum[4] += A[row][col];
                }
            }
        }
        for (int row=1; row<x+d1; row++) {
            for (int col=1; col<=y&&!border[row][col]; col++) {
                sum[0] += A[row][col];
            }
        }
        for (int row=1; row<=x+d2; row++) {
            for (int col=N; col>y&&!border[row][col]; col--) {
                sum[1] += A[row][col];
            }
        }
        for (int row=x+d1; row<=N; row++) {
            for (int col=1; col<y-d1+d2&&!border[row][col]; col++) {
                sum[2] += A[row][col];
            }
        }
        for (int row=x+d2+1; row<=N; row++) {
            for (int col=N; col>=y-d1+d2&&!border[row][col]; col--) {
                sum[3] += A[row][col];
            }
        }
    }

    private static boolean inRange(int row, int col) {
        return row>=1 && row<=N && col>=1 && col<=N;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        A = new int[N+1][N+1];
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j=1; j<=N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = MAX;
        for (x=1; x<=N-2; x++) {
            for (y=2; y<N; y++) {
                for (d1=1; d1<N; d1++) {
                    for (d2=1; d2<N; d2++) {
                        if (!inRange(x+d1, y-d1) || !inRange(x+d2, y+d2) || !inRange(x+d1+d2, y-d1+d2)) {
                            continue;
                        }
                        sum = new int[5];
                        mark();
                        int min = MAX;
                        int max = 0;
                        for (int i=0; i<5; i++) {
                            min = Math.min(min, sum[i]);
                            max = Math.max(max, sum[i]);
                        }
                        answer = Math.min(answer, max-min);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
