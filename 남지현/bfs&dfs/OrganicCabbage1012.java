import java.util.*;
import java.io.*;

// 백준 1210 유기농 배추 (실버2)

class Main {

    static boolean[][] fields, visited;
    static int count, N, M;

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static void dfs(int x, int y) {
        visited[y][x] = true;
        for (int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx>=0 && nx<M && ny>=0 && ny<N && fields[ny][nx] && !visited[ny][nx]) {
                dfs(nx, ny);
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        for (int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            fields = new boolean[N][M];
            visited = new boolean[N][M];
            count = 0;
            for (int i=0; i<K; i++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                fields[y][x] = true;
            }
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if (fields[i][j] && !visited[i][j]) {
                        dfs(j, i);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }
}
