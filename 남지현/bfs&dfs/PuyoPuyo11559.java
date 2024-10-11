import java.util.*;
import java.io.*;

// 백준 11559 - Puyo Puyo (골드4)

class Main {

    static char[][] puyo;
    static boolean[][] visited;
    static List<int[]> buffer;
    static int depth;
    
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static final int ROW=12, COL=6;

    static void dfs(int x, int y, char color) {
        if (visited[x][y]) return;
        visited[x][y] = true;
        depth++;
        for (int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx>=0 && nx<ROW && ny>=0 && ny<COL && puyo[nx][ny]==color) {
                buffer.add(new int[]{nx, ny});
                dfs(nx, ny, color);
            }
        }
    }

    static void arrange() {
        for (int j=0; j<COL; j++) {
            for (int i=1; i<ROW; i++) {
                if (puyo[i][j]=='.') continue;
                int idx = i-1;
                while (idx>=0 && puyo[idx][j]=='.') {
                    idx--;
                }
                char color = puyo[i][j];
                puyo[i][j]='.';
                puyo[idx+1][j]=color;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        puyo = new char[ROW][COL];
        for (int i=ROW-1; i>=0; i--) {
            puyo[i] = bf.readLine().toCharArray();
        }
        int answer = 0;
        boolean finished;
        while (true) {
            finished = true;
            visited = new boolean[ROW][COL];
            for (int i=0; i<ROW; i++) {
                for (int j=0; j<COL; j++) {
                    if (puyo[i][j]!='.' && !visited[i][j]) {
                        depth=0;
                        buffer = new ArrayList<>();
                        buffer.add(new int[]{i, j});
                        dfs(i, j, puyo[i][j]);
                        if (depth >= 4) {
                            finished = false;
                            for (int[] point: buffer) {
                                puyo[point[0]][point[1]] = '.';
                            }
                        }
                    }
                }
            }
            if (finished) {
                break;
            } else {
                answer++;
                arrange();
            }
        }
        System.out.println(answer);
    }
}
