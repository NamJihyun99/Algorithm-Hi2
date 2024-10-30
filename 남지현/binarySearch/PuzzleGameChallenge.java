import java.util.*;

// 프로그래머스 - 퍼즐 게임 챌린지

class Solution {
    static final long MAX = 3_000_000_000L;
    
    public long solution(int[] diffs, int[] times, long limit) {
        long mid = -1;
        long left = 1;
        long right = MAX;
        int len = diffs.length;
        while (left < right) {
            mid = (left+right)/2;
            long time=0;
            for (int i=0; i<len; i++) {
                if (diffs[i] > mid) {
                    time += times[i]*(diffs[i]-mid+1);
                    if (i>0) {
                        time += (times[i-1])*(diffs[i]-mid);
                    }
                    
                } else {
                    time += times[i];
                }
            }
            if (time > limit) { // 실패 -> 숙련도를 좀 더 늘려야 함
                left = mid+1;
            } else { // 성공 -> 일단 숙련도를 좀 더 낮춰봐야 함
                right = mid;
            }
        }
        return left;
    }
}
