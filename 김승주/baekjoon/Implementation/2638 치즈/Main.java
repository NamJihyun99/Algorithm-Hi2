import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static char[][] grid;
    static boolean[][] isOuterSpace;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        isOuterSpace = new boolean[N][M];
        Queue<Cube> remainingCubes = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                grid[i][j] = input[j].charAt(0);
                if (grid[i][j] == '1') {
                    remainingCubes.offer(new Cube(i, j, 1));
                }
            }
        }
        findOuterSpaces(0, 0);

        int result = 1;
        List<int[]> beMelted = new ArrayList<>();
        while (!remainingCubes.isEmpty()) {
            Cube curr = remainingCubes.poll();
            if (result != curr.remainingHours) {
                result = curr.remainingHours;
                for (int[] loc : beMelted) {
                    grid[loc[0]][loc[1]] = '0';
                    findOuterSpaces(loc[0], loc[1]);
                }
                beMelted.clear();
            }
            int outerCnt = 0;
            for (int i = 0; i < 4; i++) {
                if (outerCnt >= 2) {
                    break;
                }
                int nextRow = curr.row + dx[i];
                int nextCol = curr.col + dy[i];
                if (grid[nextRow][nextCol] == '0' && isOuterSpace[nextRow][nextCol]) {
                    outerCnt++;
                }
            }
            if (outerCnt >= 2) {
                beMelted.add(new int[] {curr.row, curr.col});
            } else {
                curr.remainingHours++;
                remainingCubes.offer(curr);
            }
        }

        System.out.println(result);
    }

    static void findOuterSpaces(int startRow, int startCol) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startRow, startCol});
        isOuterSpace[startRow][startCol] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = curr[0] + dx[i];
                int nextCol = curr[1] + dy[i];
                if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
                    if (grid[nextRow][nextCol] == '0' && !isOuterSpace[nextRow][nextCol]) {
                        isOuterSpace[nextRow][nextCol] = true;
                        q.offer(new int[] {nextRow, nextCol});
                    }
                }
            }
        }
    }

    static class Cube {
        int row;
        int col;
        int remainingHours;

        Cube(int row, int col, int remainingHours) {
            this.row = row;
            this.col = col;
            this.remainingHours = remainingHours;
        }
    }
}