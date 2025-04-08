import java.util.*;
import java.io.*;

// 백준 4179번 불!
class Main {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] maze = new char[R][C];
        Queue<Point> fireQ = new ArrayDeque<>();
        Queue<Point> moveQ = new ArrayDeque<>();
        int[][] fire = new int[R][C];
        int[][] move = new int[R][C];
        for (int i=0; i<R; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j=0; j<C; j++) {
                fire[i][j] = -1;
                move[i][j] = -1;
                if (maze[i][j] == 'F') {
                    fireQ.offer(new Point(i, j));
                    fire[i][j] = 0;
                } else if (maze[i][j] == 'J') {
                    moveQ.offer(new Point(i, j));
                    move[i][j] = 0;
                }
            }
        }
        while (!fireQ.isEmpty()) {
            Point point = fireQ.poll();
            for (int i=0; i<4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (nx<0 || nx>=R || ny<0 || ny>=C || fire[nx][ny]>=0 || maze[nx][ny]=='#') continue;
                fire[nx][ny] = fire[point.x][point.y]+1;
                fireQ.offer(new Point(nx, ny));
            }
        }
        while (!moveQ.isEmpty()) {
            Point point = moveQ.poll();
            for (int i=0; i<4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (nx<0 || nx>=R || ny<0 || ny>=C) {
                    System.out.println(move[point.x][point.y]+1);
                    return;
                }
                // 지훈이가 이미 이동한 적이 있는 곳은 다시 탐색할 필요가 없다.
                // 불이 옮은 시점이 현재 지훈이의 시점과 같거나 이전이면 이동할 수 없다. 단 fire 원소가 -1이면 아예 불이 닿지도 않은 곳이다.
                if (maze[nx][ny]=='#' || move[nx][ny]>=0 || fire[nx][ny] != -1 && fire[nx][ny] <= move[point.x][point.y]+1) continue;
                move[nx][ny] = move[point.x][point.y]+1;
                moveQ.offer(new Point(nx, ny));
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
