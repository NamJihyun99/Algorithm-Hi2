import java.util.*;

// 프로그래머스 - 자물쇠와 열쇠

class Solution {
    
    int N, M;
    int[][] lockShape, keyShape;
    
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        lockShape = lock;
        keyShape = key;
        for (int r=0; r<4; r++) {
            for (int x=1-N; x<N; x++) {
                for (int y=1-N; y<N; y++) {
                    if (match(x, y)) {
                        return true;
                    }
                }
            }
            if (r!=3) {
                keyShape = rotate(keyShape);
            }
        }
        return false;
    }
    
        
    private int[][] rotate(int[][] key) {
        int[][] result = new int[M][M];
        for (int x=0; x<M; x++) {
            int newY = M-1-1*x;
            for (int y=0; y<M; y++) {
                result[y][newY] = key[x][y];
            }
        }
        return result;
    }
    
    private boolean match(int startX, int startY) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                // lockShape[i][j] & keyShape[i-startX][j-startY]를 비교
                if (i-startX>=0 && i-startX<M && j-startY>=0 && j-startY<M) { // key랑 겹치는 부분이면
                    if (lockShape[i][j]==0 && keyShape[i-startX][j-startY]==0 
                        || lockShape[i][j]==1 && keyShape[i-startX][j-startY]==1) {
                        return false;
                    }
                } else if (lockShape[i][j]==0) {
                    return false;
                }
            }
        }
        return true;
    }
}
