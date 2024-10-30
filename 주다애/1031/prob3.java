import java.util.*;

class Solution {
    static Queue<int[]>[] q;
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int robot = routes.length;
        q = new LinkedList[robot]; // 이동 경로 저장
        for(int i = 0; i < robot; i++) {
            q[i] = new LinkedList<>();
        }
        // 경로 q 배열에 저장하기
        getRoute(points, routes, robot);
        // 충돌 횟수 계산하기
        answer = getCrash(robot);
        return answer;
    }
    
    static int getCrash(int robot) {
        int ans = 0;
        boolean[][] map = new boolean[101][101];
        int[][] coor = new int[101][101];
        for(int i = 0; i < robot; i++) {
            Queue<int[]> queue = q[i];
            int idx = 1;
            while(!queue.isEmpty()) {
                int[] n = queue.poll();
                int x = n[0];
                int y = n[1];
                if(coor[x][y] > 0 && coor[x][y] == idx) {
                    map[x][y] = true;
                }
                coor[x][y] = idx;
                idx++;
            }
        }
        for(int i = 0; i < 101; i++) {
            for(int j = 0; j < 101; j++) {
                if(map[i][j]) ans++;
            }
        }
        return ans;
    }
    
    static void getRoute(int[][] points, int[][] routes, int robot) {
        for(int i = 0; i < robot; i++) {
            int[] now = points[routes[i][0] -1]; // 시작점
            q[i].add(new int[]{now[0], now[1]});
            int cx = now[0];
            int cy = now[1];
            // 탐색 시작
            // j는 1부터 시작
            for(int j = 1; j < routes[0].length; j++) {
                int[] next = points[routes[i][j] - 1]; // 다음 도착점
                int nx = next[0];
                int ny = next[1];
                int dx = nx - cx;
                int dy = ny - cy;
                
                // x방향 먼저 이동
                while(dx != 0) {
                    if(dx > 0) {
                        dx -= 1;
                        cx += 1;
                        q[i].add(new int[]{cx, cy});
                    }
                    else {
                        dx += 1;
                        cx -= 1;
                        q[i].add(new int[]{cx, cy});
                    }
                }
                // y방향 그 다음 이동
                while(dy != 0) {
                    if(dy > 0) {
                        dy -= 1;
                        cy += 1;
                        q[i].add(new int[]{cx, cy});
                    }
                    else {
                        dy += 1;
                        cy -= 1;
                        q[i].add(new int[]{cx, cy});
                    }
                }
            }
        }
    }
}
