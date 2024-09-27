import java.util.*;

// 프로그래머스 - 미로 탈출 명령어

class Solution {
    
    String answer = null;
    int sizeN, sizeM, dstX, dstY, dist;
    
    final int[] dx = {1, 0, 0, -1};
    final int[] dy = {0, -1, 1, 0};
    final char[] ch = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {  
        sizeN = n; sizeM = m; dstX = r; dstY = c; dist = k;
        int manhattan = Math.abs(x-r)+Math.abs(y-c);
        if (manhattan > dist || (dist-manhattan)%2==1) {
            return "impossible";
        }
        dfs(x, y, new StringBuilder());
        return answer;
    }
    
    private void dfs(int x, int y, StringBuilder word) {
        if (answer != null) {
            return;
        }
        if (word.length()==dist) {
            if (x==dstX && y==dstY) {
                answer = word.toString();
            }
            return;
        }
        
        int manhattan = Math.abs(dstX-x) + Math.abs(dstY-y);
        if (dist-word.length() < manhattan) {
            return;
        }
        
        for (int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (canMoveTo(nx, ny)) {
                word.append(ch[i]);
                dfs(nx, ny, word);
                word.deleteCharAt(word.length()-1);
            }
        }
    }
    
    private boolean canMoveTo(int x, int y) {
        return x>=1 && x<=sizeN && y>=1 && y<=sizeM;
    }
}
