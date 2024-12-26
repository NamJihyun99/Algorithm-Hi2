import java.util.*;
import java.io.*;

// 백준 1419번 - 빗물
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        boolean[][] board = new boolean[H][W];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int j=0; j<W; j++) {
            int height = Integer.parseInt(st.nextToken());
            max = Math.max(max, height);
            for (int i=0; i<height; i++) {
                board[i][j] = true;
            }
        }
        int count = 0;
        for (int i=0; i<max; i++) {
            int sum = 0;
            boolean left = false;
            for(int j=0; j<W; j++) {
                if (board[i][j]) {
                    if (sum>0 && left) {
                        count += sum;
                    }
                    sum = 0;
                    left = true;
                } else {
                    sum++;
                }
            }
        }
        System.out.println(count);
    }
}
