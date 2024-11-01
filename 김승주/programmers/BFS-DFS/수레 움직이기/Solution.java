import java.util.*;

class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, 1};
    static int[][] m;
    static int row;
    static int col;
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{1, 0, 2}, {0, 0, 0}, {5, 0, 5}, {4, 0, 3}}));
    }
    public static int solution(int[][] maze) {
        row = maze.length;
        col = maze[0].length;
        m = maze;
        int[] redStart = null;
        int[] blueStart = null;
        int[] redEnd = null;
        int[] blueEnd = null;
        int cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (maze[i][j] == 1) {
                    redStart = new int[] {i, j, 0};
                    cnt++;
                } else if (maze[i][j] == 2) {
                    blueStart = new int[] {i, j, 0};
                    cnt++;
                } else if (maze[i][j] == 3) {
                    redEnd = new int[] {i, j};
                    cnt++;
                } else if (maze[i][j] == 4) {
                    blueEnd = new int[] {i, j};
                    cnt++;
                }
            }
            if (cnt == 4)   break;
        }
        if (redStart == null || blueStart == null || redEnd == null || blueEnd == null) {
            return 0;
        }
        return bfs(redStart, blueStart, redEnd, blueEnd);
    }
    
    private static int bfs(int[] redStart, int[] blueStart, int[] redEnd, int[] blueEnd) {
        Queue<int[]> redQ = new LinkedList<>();
        redQ.offer(redStart);
        boolean[][] redVisited = new boolean[row][col];
        redVisited[redStart[0]][redStart[1]] = true;
        
        Queue<int[]> blueQ = new LinkedList<>();
        blueQ.offer(blueStart);
        boolean[][] blueVisited = new boolean[row][col];
        blueVisited[blueStart[0]][blueStart[1]] = true;
        
        while (!redQ.isEmpty() || !blueQ.isEmpty()) {
            boolean canMove = false;
            int[] redCurr = redQ.poll();
            int[] blueCurr = blueQ.poll();
            if (redCurr[0] == redEnd[0] && redCurr[1] == redEnd[1] && blueCurr[0] == blueEnd[0] && blueCurr[1] == blueEnd[1]) {
                return blueCurr[2];
            }

            for (int i = 0; i < 4; i++) {
                int redNextX = (redCurr[0] == redEnd[0] && redCurr[1] == redEnd[1]) ? redCurr[0] : redCurr[0] + dx[i];
                int redNextY = (redCurr[0] == redEnd[0] && redCurr[1] == redEnd[1]) ? redCurr[1] : redCurr[1] + dy[i];
                if (redNextX >= 0 && redNextX < row && redNextY >= 0 && redNextY < col) {
                    if (m[redNextX][redNextY] == 5 || redVisited[redNextX][redNextY]) {
                        continue;
                    }
                    for (int j = 0; j < 4; j++) {
                        int blueNextX = (blueCurr[0] == blueEnd[0] && blueCurr[1] == blueEnd[1]) ? blueCurr[0] : blueCurr[0] + dx[j];
                        int blueNextY = (blueCurr[0] == blueEnd[0] && blueCurr[1] == blueEnd[1]) ? blueCurr[1] : blueCurr[1] + dy[j];
                        if (blueNextX >= 0 && blueNextX < row && blueNextY >= 0 && blueNextY < col) {
                            if (m[blueNextX][blueNextY] == 5
                                || blueVisited[blueNextX][blueNextY] 
                                || (blueNextX == redNextX && blueNextY == redNextY)
                                || (redCurr[0] == blueNextX && redCurr[1] == blueNextY && blueCurr[0] == redNextX && blueCurr[1] == redNextY)) {
                                continue;
                            }
                            canMove = true;
                            redVisited[redNextX][redNextY] = true;
                            blueVisited[blueNextX][blueNextY] = true;
                            redQ.add(new int[] {redNextX, redNextY, redCurr[2] + 1});
                            blueQ.add(new int[] {blueNextX, blueNextY, blueCurr[2] + 1});
                        }
                    }
                }
            }
            if (!canMove) {
                return 0;
            }
        }
        return 0;
    }
}