import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int K;
    static char[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][K + 1];
        visited[0][0][0] = true;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        Queue<Path> queue = new LinkedList<>();
        queue.offer(new Path(0, 0, 1, 0));

        while (!queue.isEmpty()) {
            Path curr = queue.poll();
            if (curr.row == N - 1 && curr.col == M - 1) {
                return curr.passed;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = curr.row + dx[i];
                int nextCol = curr.col + dy[i];
                if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
                    if (map[nextRow][nextCol] == '1' && curr.brakeWalls < K && !visited[nextRow][nextCol][curr.brakeWalls + 1]) {
                        visited[nextRow][nextCol][curr.brakeWalls + 1] = true;
                        queue.offer(new Path(nextRow, nextCol, curr.passed + 1, curr.brakeWalls + 1));
                    } else if (map[nextRow][nextCol] == '0' && !visited[nextRow][nextCol][curr.brakeWalls]) {
                        visited[nextRow][nextCol][curr.brakeWalls] = true;
                        queue.offer(new Path(nextRow, nextCol, curr.passed + 1, curr.brakeWalls));
                    }
                }
            }
        }

        return -1;
    }

    static class Path {
        int row;
        int col;
        int passed;
        int brakeWalls;

        public Path(int row, int col, int passed, int brakeWalls) {
            this.row = row;
            this.col = col;
            this.passed = passed;
            this.brakeWalls = brakeWalls;
        }
    }
}