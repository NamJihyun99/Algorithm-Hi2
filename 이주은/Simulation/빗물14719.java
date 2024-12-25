import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[H][W];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++) {
            int h = Integer.parseInt(st.nextToken());

            for(int j=0; j<h; j++)
                map[j][i] = true;
        }

        int sum = 0;
        
        for(int i=0; i<H; i++) {
            boolean flag = false;
            int cnt = 0;

            for(int j=0; j<W; j++) {
                //벽
                if(map[i][j]) {
                    if(flag) {
                        sum += cnt;
                        cnt = 0;
                    }
                    else {
                        flag = true;
                    }
                    
                }
                else if(flag) {
                    cnt++;
                }
            }        }

        System.out.println(sum);
    }
}
