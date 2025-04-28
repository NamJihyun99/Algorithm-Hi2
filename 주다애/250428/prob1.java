import java.util.*;
// 1. 예외가 무엇일까요?
// 2. 맨해튼 거리 2를 탐색할 때 다시 for문에서 direct 4방향을 탐색해주어야 한다.
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int idx = 0;
        boolean flag;
        for(int i = 0; i < 5; i++) {
            String[] p = places[i];
            flag = false;
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    String now = String.valueOf(p[j].charAt(k));
                    if(now.equals("P")) {
                        if(!search(j, k, p)) {
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag) break;
            }
            if(flag) answer[idx++] = 0;
            else answer[idx++] = 1;
        }
        return answer;
    }
    
    static boolean search(int x, int y, String[] p) {
        int[][] direct = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        // 맨해튼 거리 = 1
        for(int[] d : direct) {
            int nx = x + d[0];
            int ny = y + d[1];
            if(!canMove(nx, ny)) continue;
            if(String.valueOf(p[nx].charAt(ny)).equals("P")) return false;
            // 맨해튼 거리 = 2
            if(String.valueOf(p[nx].charAt(ny)).equals("O")) {
                for(int[] dd : direct) {
                    int nnx = nx + dd[0];
                    int nny = ny + dd[1];
                    if(!canMove(nnx, nny)) continue;
                    if(nnx == x && nny == y) continue; // 기준점
                    if(String.valueOf(p[nnx].charAt(nny)).equals("P")) return false;
                }
            }
        }
        return true;
    }
    
    static boolean canMove(int x, int y) {
        if(x < 0 || x >= 5 || y < 0 || y >= 5) return false;
        return true;
    }
}
