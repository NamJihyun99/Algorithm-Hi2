import java.util.*;

// 1. s,w를 static으로 관리하면 백트래킹하면서 이전 결과가 영향을 줘서 안됨
// 2. temp = visited로 하면 둘이 같은 boolen 배열 가리키므로 독립적으로 움직이지 않음
class Solution {
    static int sheep = 0;
    static int wolf = 0;
    static boolean[] checked;
    static int answer = 0;
    static int len;
    public int solution(int[] info, int[][] edges) {
        len = info.length;
        checked = new boolean[len];
        run(0, checked, edges, info, 0, 0);
        return answer;
    }
    
    static void run(int idx, boolean[] visited, int[][] edges, int[] info, int s, int w) {
        visited[idx] = true;
        
        if (info[idx] == 0) {
            s++;
            if (s > answer) {
                answer = s;
            }
        }
        else {
            w++;
        }

        if (w >= s) {
            return;
        }

        for(int[] e : edges) {
            int parent = e[0];
            int child = e[1];
            // 내 부모를 먼저 방문해야 나를 방문할 수 있음
            if(visited[parent] && !visited[child]) {
                boolean[] temp = new boolean[len];
                temp = visited.clone();
                run(child, temp, edges, info, s, w);
            }
        }
    }
}
