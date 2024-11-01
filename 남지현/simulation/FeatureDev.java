import java.util.*;

// 프로그래머스 - 기능개발 (복습)

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int N = progresses.length;
        int[] remains = new int[N];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            remains[i] = (100-progresses[i])/speeds[i];
            if ((100-progresses[i])%speeds[i]!=0) {
                remains[i]++;
            }
        }
        List<Integer> result = new ArrayList<>();
        queue.addLast(remains[0]);
        int count=0;
        for (int i=1; i<N; i++) {
            if (remains[i] > queue.peekFirst()) {
                count=0;
                while (!queue.isEmpty()) {
                    queue.pollFirst();
                    count++;
                }
                result.add(count);
            } 
            queue.addLast(remains[i]);
        }
        count=0;
        while (!queue.isEmpty()) {
            queue.pollFirst();
            count++;
        }
        result.add(count);
        int[] answer = new int[result.size()];
        for (int i=0; i<result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
