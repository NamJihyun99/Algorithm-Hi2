import java.util.*;

// 프로그래머스 - k진수에서 소수 개수 구하기

class Solution {
    
    public int solution(int n, int k) {
        String[] tokens = Integer.toString(n, k).split("0");
        int count = 0;
        for (String token: tokens) {
            if (token.length()>0 && isPrime(Long.valueOf(token))) {
                count++;
            }
        }
        return count;
    }
    
    private boolean isPrime(Long number) {
        if (number <= 1L) return false;
        for (Long i=2L; i<=Math.sqrt(number); i++) {
            if (number%i == 0) return false;
        }
        return true;
    }
}
