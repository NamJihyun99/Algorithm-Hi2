import java.util.*;
import java.io.*;

// 백준 2630 - 색종이 만들기

class Main {
    
    static int[] count;
    static int[][] square;

    private static void divide (int n, int x, int y) {
        boolean passed = true;
        int color = square[x][y];
        for (int i=x; i<x+n; i++) {
            for (int j=y; j<y+n; j++) {
                if (square[i][j] != color) {
                    passed = false;
                    break;
                }
            }
            if (!passed) break;
        }
        if (passed) {
            count[color]++;
            return;
        }
        divide(n/2, x, y);
        divide(n/2, x, y+n/2);
        divide(n/2, x+n/2, y);
        divide(n/2, x+n/2, y+n/2);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        square = new int[N][N];
        count = new int[2];
        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j=0; j<N; j++){
                square[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divide(N, 0, 0);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<2; i++) {
            sb.append(count[i]).append("\n");
        }
        System.out.print(sb);
    }
}
