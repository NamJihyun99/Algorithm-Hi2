import java.util.*;
import java.io.*;

// 백준 1941번 소문난 칠공주
class Main {

    static final int N = 5;
    static final int SQUARE = 25;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static char[][] seats;
    static boolean[][] visited;
    static int count;

    private static void combination(int depth, int numOfY, int idx) {
        if (numOfY >= 4) {
            return;
        }
        if (depth == 7) {
            if (areAdjacent((idx-1)/N, (idx-1)%N)) {
                count++;
            }
            return;
        }
        if (idx == SQUARE) {
            return;
        }
        for (int i=idx; i<SQUARE; i++) {
            int x = i/N;
            int y = i%N;
            visited[x][y] = true;
            if (seats[x][y]=='Y') {
                combination(depth+1, numOfY+1, i+1);
            } else {
                combination(depth+1, numOfY, i+1);
            }
            visited[x][y] = false;
        }
    }

    private static boolean areAdjacent(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] marked = new boolean[N][N];
        marked[x][y] = true;
        queue.addLast(new int[]{x, y});
        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] pt = queue.pollFirst();
            for (int i=0; i<4; i++) {
                int nx = pt[0]+dx[i];
                int ny = pt[1]+dy[i];
                if (nx>=0 && nx<N && ny>=0 && ny<N && visited[nx][ny] && !marked[nx][ny]) {
                    marked[nx][ny] = true;
                    queue.addLast(new int[]{nx, ny});
                    cnt++;
                }
            }
        }
        return cnt==7;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        seats = new char[N][N];
        count = 0;
        for (int i=0; i<N; i++) {
            seats[i] = br.readLine().toCharArray();
        }
        visited = new boolean[N][N];
        combination(0, 0, 0);
        System.out.println(count);
    }
}
