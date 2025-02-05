import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map;
    static long[][] cnt;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        cnt = new long[N][N];

        cnt[0][0] = 1;
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(cnt[i][j] == 0 || map[i][j] == 0)
                    continue;

                int next = i + map[i][j];
                if(next < N) {
                    cnt[next][j] += cnt[i][j];
                }

                next = j + map[i][j];
                if(next < N) {
                    cnt[i][next] += cnt[i][j];               
                }
            } 
        }

        System.out.println(cnt[N-1][N-1]);
    }
}
