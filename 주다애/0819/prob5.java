// 1. 시간 초과
// getDistance 메소드 사용
class Solution {
    static int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}}; // d l r u
    static char[] word = {'d', 'l', 'r', 'u'};
    static int N;
    static int M;
    static int[] path;
    static StringBuilder answer = new StringBuilder();
    static boolean flag = false;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer.append("impossible");
        N = n;
        M = m;
        path = new int[k];

        int dr = getDistance(x, y, r, c);
        if((k - dr) % 2 == 1 || k < dr) return answer.toString(); // 탈출 자체가 불가한 2가지 경우
        
        run(0, k, x - 1, y - 1, r - 1, c - 1);
        return answer.toString();
    }
    
    static void run(int level, int k, int cx, int cy, int nx, int ny) {
        // 남은 거리와 현재 level의 합이 k보다 크면 k번에 탈출 불가
        if(level + getDistance(cx, cy, nx, ny) > k) return;
        if(level == k) {
            if(cx == nx && cy == ny) {
                answer.setLength(0);
                for(int i : path) {
                    answer.append(word[i]);
                }
                flag = true;
                return;
            }
            return;
        }
        for(int i = 0; i < 4; i++) {
            if(!canMove(cx + dir[i][0], cy + dir[i][1])) continue;
            path[level] = i;
            run(level + 1, k, cx + dir[i][0], cy + dir[i][1], nx, ny);
            if(flag) return;
            path[level] = -1;
        }
    }
    
    static int getDistance(int x, int y, int r, int c) {
        return (int) Math.abs(x - r) + (int) Math.abs(y - c);
    }
    
    static boolean canMove(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= M) return false;
        return true;
    }
}
