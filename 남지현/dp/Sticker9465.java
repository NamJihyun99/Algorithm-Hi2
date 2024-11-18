import java.util.*;
import java.io.*;

// 백준 9465번 - 스티커

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][100_000];
            for (int i=0; i<2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            stickers[0][1] += stickers[1][0];
            stickers[1][1] += stickers[0][0];
            for (int j=2; j<N; j++) {
                stickers[0][j] += Math.max(stickers[1][j-2], stickers[1][j-1]);
                stickers[1][j] += Math.max(stickers[0][j-2], stickers[0][j-1]);
            }
            sb.append(Math.max(stickers[0][N-1], stickers[1][N-1])).append("\n");
        }
        System.out.print(sb);
    }
}
