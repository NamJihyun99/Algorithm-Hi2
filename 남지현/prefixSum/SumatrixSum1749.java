import java.util.*;
import java.io.*;

// 백준 1749번 점수 따먹기
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] sums = new int[N+1][M+1];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                int num = Integer.parseInt(st.nextToken());
                sums[i+1][j+1] = sums[i+1][j] + sums[i][j+1] - sums[i][j] + num;
            }
        }
        int answer = Integer.MIN_VALUE;
        for (int r=0; r<N; r++) {
            for (int c=0; c<M; c++) {
                for (int i=0; i<=r; i++) {
                    for (int j=0; j<=c; j++) {
                        int tmp = sums[r+1][c+1] - sums[r+1][j] - sums[i][c+1] + sums[i][j];
                        answer = Math.max(answer, tmp);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
