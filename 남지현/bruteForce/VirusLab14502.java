import java.util.*;
import java.io.*;

// 백준 14502번 - 연구소
class Main {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static List<int[]> virus;
    static boolean[][] visited;
    static int[][] board;
    static int N, M, total, count, minVirusArea;

    private static void bruteForce() {
        // 새로운 벽을 세울 index : i, j, k
        for (int i=0; i<total; i++) {
            if (board[i/M][i%M] != 0) continue;
            board[i/M][i%M] = 1;
            for (int j=i+1; j<total; j++) {
                if (board[j/M][j%M] != 0) continue;
                board[j/M][j%M] = 1;
                for (int k=j+1; k<total; k++) {
                    if (board[k/M][k%M] != 0) continue;
                    board[k/M][k%M] = 1;
                    visited = new boolean[N][M];
                    count = 0;
                    for (int[] pt : virus) {
                        dfs(pt[0], pt[1]);
                    }
                    minVirusArea = Math.min(minVirusArea, count);
                    board[k/M][k%M] = 0;
                }
                board[j/M][j%M] = 0;
            }
            board[i/M][i%M] = 0;
        }
    }

    private static void dfs(int x, int y) {
        for (int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx<0 || nx>=N || ny<0 || ny>=M) continue;
            if (board[nx][ny] != 0 || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            count++;
            dfs(nx, ny);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        virus = new ArrayList<>();
        board = new int[N][M];
        int filled = 0;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
                if (board[i][j] != 0) {
                    filled++;
                }
            }
        }
        total = N*M;
        minVirusArea = total;
        bruteForce();
        System.out.println(total - filled - 3 - minVirusArea);
    }
}
