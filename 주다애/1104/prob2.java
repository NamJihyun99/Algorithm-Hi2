import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        Queue<Integer> temp = new LinkedList<>();
        
        long sum = 0;
        long q1Sum = 0;
        for(int q : queue1) {
            sum += q;
            q1.add(q);
            q1Sum += q;
        }
        for(int q : queue2) {
            sum += q;
            q2.add(q);
        }
        if(sum % 2 != 0) return -1;
        
        long t = sum / 2;
        while(true) {
            if((q1.size() + q2.size()) * 2 < answer) return -1;
            if(q1Sum == t) break;
            if(q1Sum > t) {
                int n = q1.poll();
                q1Sum -= n;
                q2.add(n);
                answer++;
            }
            else {
                int n = q2.poll();
                q1Sum += n;
                q1.add(n);
                answer++;
            }
        }
        return answer;
        
    }
}
