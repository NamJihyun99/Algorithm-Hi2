import java.util.*;

// 프로그래머스 - 전력망을 둘로 나누기 (복습)

class Solution {
    
    int N;
    boolean[] cut, visited;
    int[][] wireArray;
    
    public int solution(int n, int[][] wires) {
        int answer = 100;
        N = n;
        cut = new boolean[N-1];
        wireArray = wires;
        for (int i=0; i<N-1; i++) {
            cut[i] = true;
            visited = new boolean[N+1];
            dfs(1);
            int count = 0;
            for (boolean mark: visited) {
                if (mark) {count++;}
            }
            answer = Math.min(answer, Math.abs(N-count-count));
            cut[i] = false;
        }
        return answer;
    }
    
    private void dfs(int node) {
        visited[node] = true;
        for (int i=0; i<N-1; i++) {
            if (!cut[i]) {
                if (wireArray[i][0]==node && !visited[wireArray[i][1]]) {
                    dfs(wireArray[i][1]);
                } else if (wireArray[i][1]==node && !visited[wireArray[i][0]]) {
                    dfs(wireArray[i][0]);
                }
            }
        }
    }
}
