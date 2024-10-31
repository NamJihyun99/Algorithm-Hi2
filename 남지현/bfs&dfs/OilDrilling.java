// 프로그래머스 석유 시추

import java.util.*;

class Solution {
    int[][] board, landArr;
    int[] sum;
    int ROW, COL, idx;
    
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        landArr = land;
        ROW = land.length;
        COL = land[0].length;
        board = new int[ROW][COL];
        sum = new int[250001];
        idx = 1;
        for (int i=0; i<ROW; i++) {
            for (int j=0; j<COL; j++) {
                if (landArr[i][j]==1 && board[i][j]==0) {
                    dfs(i, j);
                    idx++;
                }
            }
        }
        int max = 0;
        for (int j=0; j<COL; j++) {
            Set<Integer> s = new HashSet<>();
            int count = 0;
            for (int i=0; i<ROW; i++) {
                if (landArr[i][j] > 0 && !s.contains(board[i][j])) {
                    s.add(board[i][j]);
                    count += sum[board[i][j]];
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }
    
    private void dfs(int row, int col) {
        if (landArr[row][col]==0 || board[row][col]>0) {
            return;
        }
        board[row][col] = idx;
        sum[idx] += 1;
        for (int d=0; d<4; d++) {
            int nr = row+dx[d];
            int nc = col+dy[d];
            if (nr>=0 && nr<ROW && nc>=0 && nc<COL) {
                dfs(nr, nc);
            }
        }
    }
}
