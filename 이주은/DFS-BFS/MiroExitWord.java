//프로그래머스 미로 탈출 명령어 (https://school.programmers.co.kr/learn/courses/30/lessons/150365?language=java)

class Solution {
    StringBuilder sb = new StringBuilder();
    int N, M, X, Y, R, C, K;

    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    char[] dir = {'d', 'l', 'r', 'u'};
    
    String answer;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n; M = m; X = x-1; Y = y-1; R = r-1; C = c-1; K = k;
        
        int mDistance = Math.abs(X-R) + Math.abs(Y-C);
        
        if(mDistance > K || (K - mDistance)%2 == 1) {
            return "impossible";
        }
        
        dfs(X, Y, 0);
        
        return answer;
    }
    
    private boolean dfs(int x, int y, int len) {  
        if(len == K && x == R && y == C) {
            answer = sb.toString();
            return true;
        }
        
        int mDistance = Math.abs(x-R) + Math.abs(y-C);
        if(len== K || K - len < mDistance)
            return false;
        
        for(int i=0; i<4; i++) {
            int nX = x+dx[i];
            int nY = y+dy[i];
            
            if(nX < 0 || nX >= N || nY < 0 || nY >= M)
                continue;
            
            sb.append(dir[i]);
            if(dfs(nX, nY, len+1))
                return true;
            sb.deleteCharAt(len);
        }
        return false;
    }
}
