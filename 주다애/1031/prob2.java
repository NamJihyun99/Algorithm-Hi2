// 이진 탐색..?
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int len = diffs.length;
        int max = 0;
        for(int i = 0; i < len; i++) {
            max = Math.max(max, diffs[i]);
        }
        int left = 1;
        int right = max;
        int mid = 0;
        while(left <= right) {
            mid = (left + right) / 2;
            long sum = times[0];
            for(int i = 1; i < len; i++) {
                int diff = diffs[i];
                int time = times[i];
                if(diff <= mid) {
                    sum += time;
                }
                else {
                    int t = diff - mid;
                    sum += t * (time + times[i - 1]) + time;
                }
            }
            if(sum > limit) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return right + 1;
    }
}
