import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static Map<int[], Integer> map = new HashMap<>();
    static int[] oil;
    
    public int solution(int[][] land) {
        int answer = 0;
        int col = land[0].length;
        int row = land.length;
        visited = new boolean[row][col];
        oil = new int[col];
        for(int i = 0; i < row; i++) {
            int res = 0;
            for(int j = 0; j < col; j++) {
                if(!visited[i][j] && land[i][j] == 1) {
                    res = bfs(i, j, row, col, land);
                }
            }
        }
        int max = 0;
        for(int i = 0; i < col; i++) {
            int res = oil[i];
            max = Math.max(max, res);
        }
        return max;
    }
    
    static int bfs(int r, int c, int row, int col, int[][] land) {
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        visited[r][c] = true;
        q.offer(new int[]{r, c});
        Set<Integer> set = new HashSet<>();

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int cr = now[0];
            int cc = now[1];
            set.add(cc);
            for(int[] d : dir) {
                int nr = cr + d[0];
                int nc = cc + d[1];

                if(nr < 0 || nr >= row || nc < 0 || nc >= col) continue;
                if(visited[nr][nc]) continue;
                if(land[nr][nc] == 1) {
                    q.offer(new int[]{nr, nc});
                    cnt += 1;
                    visited[nr][nc] = true;
                }
            }
        }
        for(int i : set) {
            oil[i] += cnt;
        }
        return cnt;
    }
}
