import java.util.*;

// 프로그래머스 거리두기 확인하기

class Solution {
    
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    
    String[] board;
    Queue<Point> queue;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int t=0; t<5; t++) {
            boolean result = true;
            board = places[t];
            for (int i=0; i<5; i++) {
                for (int j=0; j<5; j++) {
                    if (board[i].charAt(j) == 'P' && !bfs(new Point(i, j))) {
                        result = false;
                        break;
                    }
                }
                if (!result) break;
            }
            answer[t] = result? 1 : 0;
        }
        return answer;
    }
    
    private boolean bfs(Point start) {
        queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx<0 || nx>=5 || ny<0 || ny>=5 || nx==start.x && ny==start.y) {
                    continue;
                }
                int dist = Math.abs(nx-start.x)+Math.abs(ny-start.y);
                
                if (board[nx].charAt(ny)=='P' && dist<=2) {
                    return false;
                } else if (board[nx].charAt(ny)=='O' && dist<2) {
                    queue.add(new Point(nx, ny));
                }
            }
        }
        return true;
    }
    
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
