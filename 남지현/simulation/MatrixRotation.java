import java.util.*;

// 프로그래머스 - 행렬 테두리 회전하기

class Solution {
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] board = new int[rows+1][columns+1];
        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=columns; j++) {
                board[i][j] = (i-1)*columns+j;
            }
        }
        int[] answer = new int[queries.length];
        for (int i=0; i<queries.length; i++) {
            int min = 10002;
            int[] query = queries[i];
            int nr = query[2]-query[0]+1;
            int nc = query[3]-query[1]+1;
            int[][] tmp = new int[nr][nc];
            for (int y=1; y<nc; y++) {
                tmp[0][y] = board[query[0]][query[1]+y-1];
                min = Math.min(min, tmp[0][y]);
            }
            for (int y=0; y<nc-1; y++) {
                tmp[nr-1][y] = board[query[2]][query[1]+y+1];
                min = Math.min(min, tmp[nr-1][y]);
            }
            for (int x=1; x<nr; x++) {
                tmp[x][nc-1] = board[query[0]+x-1][query[3]];
                min = Math.min(min, tmp[x][nc-1]);
            }
            for (int x=0; x<nr-1; x++) {
                tmp[x][0] = board[query[0]+x+1][query[1]];
                min = Math.min(min, tmp[x][0]);
            }
            for (int x=0; x<nr; x++) {
                for (int y=0; y<nc; y++) {
                    if (tmp[x][y] > 0) {
                        board[query[0]+x][query[1]+y] = tmp[x][y];
                    }
                }
            }
            answer[i] = min;
        }
        return answer;
    }
}
