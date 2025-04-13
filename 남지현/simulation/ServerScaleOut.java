import java.util.*;

// 프로그래머스 서버 증설 횟수

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int T = players.length;
        int number = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int t=0; t<T; t++) {
            while (!queue.isEmpty() && queue.peekFirst() == t) {
                queue.pollFirst();
                number--;
            }
            if (players[t] >= m*(number+1)) {
                int needs = players[t]/m - number;
                for (int i=0; i<needs; i++) {
                    queue.addLast(t+k);
                }
                number += needs;
                answer += needs;
            }
        }
        return answer;
    }
}
