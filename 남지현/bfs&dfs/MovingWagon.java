import java.util.*;

// 프로그래머스 - 수레 움직이기

class Solution {
    
    int ROW, COL;
    int[][] board;
    
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maze) {
        board = maze;
        ROW = maze.length;
        COL = maze[0].length;
        Point redDep = null, blueDep = null, redDst = null, blueDst = null;
        for (int i=0; i<ROW; i++) {
            for (int j=0; j<COL; j++) {
                if (maze[i][j] == 1) {
                    redDep = new Point(i, j, 0, 0);
                } else if (maze[i][j] == 2) {
                    blueDep = new Point(i, j, 0, 0);
                } else if (maze[i][j] == 3) {
                    redDst = new Point(i, j, 0, 0);
                } else if (maze[i][j] == 4) {
                    blueDst = new Point(i, j, 0, 0);
                }
            }
        }
        ArrayDeque<Point> redQ = new ArrayDeque<>();
        ArrayDeque<Point> blueQ = new ArrayDeque<>();
        redQ.addLast(redDep); blueQ.addLast(blueDep);
        Point nowRed = null, nowBlue = null;
        while (!redQ.isEmpty() && !blueQ.isEmpty()) {
            nowRed = redQ.pollFirst();
            nowBlue = blueQ.pollFirst();
            if (equals(nowRed, nowBlue)) {
                // 두 수레를 동시에 같은 칸에 둘 수 없다. 
                continue;
            }
            if (equals(nowRed, redDst) && equals(nowBlue, blueDst)) {
                return nowBlue.depth;
            }
            int nextVisitedRed = nowRed.visited | 1<<(nowRed.x*COL+nowRed.y);
            int nextVisitedBlue = nowBlue.visited | 1<<(nowBlue.x*COL+nowBlue.y);
            List<Point> nextRs = new ArrayList<>();
            List<Point> nextBs = new ArrayList<>();
            Point nextRed = null, nextBlue = null;
            if (equals(nowRed, redDst)) {
                nextRed = new Point(nowRed.x, nowRed.y, nowRed.depth+1, nextVisitedRed);
                nextRs.add(nextRed);
            } else if (equals(nowBlue, blueDst)) {
                nextBlue = new Point(nowBlue.x, nowBlue.y, nowBlue.depth+1, nextVisitedBlue);
                nextBs.add(nextBlue);
            }
            for (int d=0; d<4; d++) {
                if (!equals(nowRed, redDst)) {
                    nextRed = new Point(nowRed.x+dx[d], nowRed.y+dy[d], nowRed.depth+1, nextVisitedRed);
                    if (canMoveTo(nextRed)) {
                        nextRs.add(nextRed);
                    }
                }
                if (!equals(nowBlue, blueDst)) {
                    nextBlue = new Point(nowBlue.x+dx[d], nowBlue.y+dy[d], nowBlue.depth+1, nextVisitedBlue);
                    if (canMoveTo(nextBlue)) {
                        nextBs.add(nextBlue);
                    }
                }
            }
            for (Point red: nextRs) {
                for (Point blue: nextBs) {
                    if (equals(nowRed, blue) && equals(nowBlue, red)) {
                        // 자리를 서로 맞바꿀 수 없다.
                        continue;
                    }
                    redQ.addLast(red);
                    blueQ.addLast(blue);
                }
            }
        }
        return 0;
    }
    
    private boolean equals(Point p1, Point p2) {
        return p1.x==p2.x && p1.y==p2.y;
    }
    
    private boolean canMoveTo(Point next) {
        // 수레는 벽이나 격자 판 밖으로 움직일 수 없고, 자신이 방문했던 칸으로 움직일 수 없다.
        return next.x>=0
            && next.x<ROW 
            && next.y>=0 
            && next.y<COL 
            && board[next.x][next.y]!=5
            && (next.visited&1<<(next.x*COL+next.y))==0;
    }
    
    static class Point {
        int x; 
        int y;
        int depth;
        int visited;
        
        Point (int x, int y, int depth, int visited) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.visited = visited;
        }
    }
}
