import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<int[]> locationOfVirus = new ArrayList<>();
    static int initialStatus = 0;
    static int maxSafetyZone = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    locationOfVirus.add(new int[] {i, j});
                } else if (map[i][j] == 0) {
                    initialStatus++;
                }
            }
        }

        dfs(map, 0, 0, 0, 3);
        System.out.println(maxSafetyZone);
    }

    private static void dfs(int[][] status, int row, int col, int sequence, int leftWalls) {
        if (leftWalls == 0) {
            maxSafetyZone = Math.max(maxSafetyZone, spread(status));
            return;
        }

        for (int i = row; i < N; i++) {
            for (int j = col; j < M; j++) {
                if (status[i][j] != 0) {
                    continue;
                }
                int k = (i == row && j == col)? sequence : 0;
                for (; k < 4; k++) {
                    int nRow = dx[k] + i;
                    int nCol = dy[k] + j;
                    if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < M && status[nRow][nCol] == 0) {
                        status[nRow][nCol] = 1;
                        if (k == 3) {
                            dfs(status, i, j, 0, leftWalls - 1);
                        } else {
                            dfs(status, i, j, k + 1, leftWalls - 1);
                        }
                        
                        status[nRow][nCol] = 0;
                        if (k == 3) {
                            dfs(status, i, j, 0, leftWalls);
                        } else {
                            dfs(status, i, j, k + 1, leftWalls);
                        }
    
                        return;
                    }
                }
            }
        }
    }

    private static int spread(int[][] status) {
        int numOfSaftyZone = initialStatus - 3;
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.addAll(locationOfVirus);
        while (!q.isEmpty()) {
            int[] location = q.poll(); 
            visited[location[0]][location[1]] = true;
            for (int k = 0; k < 4; k++) {
                int nRow = dx[k] + location[0];
                int nCol = dy[k] + location[1];
                if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < M && !visited[nRow][nCol] && status[nRow][nCol] != 1) {
                    if (status[nRow][nCol] == 0) {
                        numOfSaftyZone--;
                    }
                    visited[nRow][nCol] = true;
                    q.offer(new int[] {nRow, nCol});
                }
            }
        }
        return numOfSaftyZone;
    }
}