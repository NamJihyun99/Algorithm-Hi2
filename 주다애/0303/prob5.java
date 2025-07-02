import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int now = 0;
        Queue<int[]> q = new LinkedList<>(); // 서버 지속 시간, 서버 증설 횟수
        
        for(int i = 0; i < 24; i++) {
            // 서버 내리기
            while(!q.isEmpty() && q.peek()[0] == i) {
                now -= q.poll()[1];
            }
            int p = players[i] / m; // 필요한 서버 개수
            int diff = now - p;
            // 필요한 서버 개수보다 현재 서버 개수가 작으면
            // 서버 증설하기
            if(diff < 0) {
                now += -diff;
                answer += -diff;
                q.offer(new int[]{i + k, -diff});
            }
        }
        return answer;
    }
}
