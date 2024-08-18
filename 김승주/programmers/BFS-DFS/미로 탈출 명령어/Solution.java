import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int manhattanDistance = Math.abs(x - r) + Math.abs(y - c);
        if (manhattanDistance > k || (k - manhattanDistance) % 2 == 1) {
            System.out.println("impossible");
        } else {
            System.out.println(bfs(n, m, x, y, r, c, k));
        }
    }

    private static String bfs(int n, int m, int x, int y, int r, int c, int k) {
        PriorityQueue<Path> queue = new PriorityQueue<>((a, b) -> a.history.toString().compareTo(b.history.toString()));
        queue.offer(new Path(x, y, new StringBuilder()));
        boolean[][][] visited = new boolean[n + 1][m + 1][k + 1];
        visited[x][y][0] = true;
        
        while (!queue.isEmpty()) {
            Path curr = queue.poll();  
            if (curr.history.length() > k) {
                return "impossible";
            }
            
            if (curr.history.length() == k) {
                if (curr.row == r && curr.col == c) {
                    return curr.history.toString();
                }
                continue;
            }
            
            int[] dx = {1, 0, 0, -1};
            int[] dy = {0, -1, 1, 0};
            char[] directions = {'d', 'l', 'r', 'u'};
            for (int i = 0; i < 4; i++) {
                int nextR = curr.row + dx[i];
                int nextC = curr.col + dy[i];
                if (nextR >= 1 && nextR <= n && nextC >= 1 && nextC <= m && !visited[nextR][nextC][curr.history.length() + 1]) {
                    int remainingDist = Math.abs(nextR - r) + Math.abs(nextC - c);
                    if (remainingDist <= k - curr.history.length() + 1) {
                        visited[nextR][nextC][curr.history.length() + 1] = true;
                        queue.offer(new Path(nextR, nextC, new StringBuilder(curr.history).append(directions[i])));
                    }
                }
            }
        }
        return "impossible";
    }
    
    static class Path {
        int row;
        int col;
        StringBuilder history;
        private Path(int row, int col, StringBuilder history) {
            this.row = row;
            this.col = col;
            this.history = history;
        }
    }
}