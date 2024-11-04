import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        long sum1 = 0;
        long sum2 = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];

            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        while (sum1 != sum2) {
            if (answer >= queue1.length * 3) {
                return -1;
            }

            if (sum1 > sum2) {
                sum1 -= q1.peek();
                q2.offer(q1.peek());
                sum2 += q1.poll();
            } else {
                sum2 -= q2.peek();
                q1.offer(q2.peek());
                sum1 += q2.poll();
            }

            answer++;
        }

        return answer;
    }
}