import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = times[0];
        long right = (long) times[times.length - 1] * n; // 최댓값이 중요!
        while(left < right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for(int t : times) {
                sum += (mid / t);
            }
            if(sum >= n) {
                right = mid;
                answer = mid;
            }
            else {
                left = mid + 1;
            }
            // System.out.println(left + " " + right + " " + mid + " " + sum);
        }
        return right;
    }
}
