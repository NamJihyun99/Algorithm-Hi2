class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int maxDiff = 0;
        for (int diff : diffs) {
            maxDiff = Math.max(maxDiff, diff);
        }
        int start = 1;
        int end = maxDiff;
        int succeeded = maxDiff;
        while (start <= end) {      
            int mid = (start + end) / 2;
            
            if (canBeSolved(diffs, times, limit, mid)) {
                succeeded = Math.min(succeeded, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return succeeded;
    }
    
    private boolean canBeSolved(int[] diffs, int[] times, long limit, int proficiency) {
        long consumingTime = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (consumingTime > limit) {
                return false;
            }
            if (diffs[i] <= proficiency) {
                consumingTime += times[i];
            } else {
                consumingTime += ((times[i] + times[i - 1]) * (diffs[i] - proficiency) + times[i]);
            }
        }
        if (consumingTime > limit) {
            return false;
        }
        return true;
    }
}