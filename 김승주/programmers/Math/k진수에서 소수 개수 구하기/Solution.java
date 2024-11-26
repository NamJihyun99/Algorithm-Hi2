import java.util.stream.IntStream;

class Solution {
    public int solution(int n, int k) {
        String kn = Integer.toString(n, k);
        String[] splited = kn.split("0");
        int count = 0;
        for (String num : splited) {
            if (num.isEmpty() || num.isBlank()) {
                continue;
            }
            if (isPrime(Long.parseLong(num))) {
                count++;
            }
        }
        
        return count;
    }
    
    private static boolean isPrime(long num) {
        if (num < 2) return false;
        return IntStream.rangeClosed(2, (int)Math.sqrt(num))
            .noneMatch(i -> num % i == 0);
    }
}