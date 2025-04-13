import java.util.*;
import java.io.*;

class Main {
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static final int TEMP = 3;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    static int N, M, max;
    static int[][] map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeWall(0, -1, 0);

        System.out.println(max);
        
    }

    private static void makeWall (int r, int c, int cnt) {
        
        if(cnt == 3) {
            spread();
            return;
        }
        
        for(int j=c+1; j<M; j++) {
            if(map[r][j] == EMPTY) {
                map[r][j] = WALL;
                makeWall(r, j, cnt+1);
                map[r][j] = EMPTY;
            }
        }

        for(int i=r+1; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == EMPTY) {
                    map[i][j] = WALL;
                    makeWall(i, j, cnt+1);
                    map[i][j] = EMPTY;
                }
            }
        }
    }

    private static void spread() {    
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == VIRUS) {
                    dfs(i, j);
                }
            }
        }

        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == TEMP) {
                    map[i][j] = EMPTY;
                } else if(map[i][j] == EMPTY) {
                    cnt++;
                }
            }
        }

        max = Math.max(max, cnt);
    }

    private static void dfs(int x, int y) {
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M)
                continue;

            if(map[nx][ny] == EMPTY) {
                map[nx][ny] = TEMP;
                dfs(nx, ny);
            }
        }
    }
}
