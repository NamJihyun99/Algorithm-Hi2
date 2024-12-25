import java.util.*;

// 프로그래머스 - 문자열 압축

class Solution {
    int N;
    public int solution(String s) {
        N = s.length();
        int count = N;
        for (int len=1; len<=N/2; len++) {
            count = Math.min(count, convert(s, len).length());
        }
        return count;
    }
    
    private String convert(String s, int len) {
        StringBuilder result = new StringBuilder();
        String sub = s.substring(0, len);
        int count = 1;
        int i = len;
        for (i=len; i<=N-len; i+=len) {
            if (!sub.equals(s.substring(i, i+len))) {
                if (count > 1) {
                    result.append(count);
                }
                result.append(sub);
                sub = s.substring(i, i+len);
                count = 1;
            } else {
                count++;
            }
        }
        if (count > 1) {
            result.append(count);
        }
        result.append(sub);
        for (; i<N; i++) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }
}
