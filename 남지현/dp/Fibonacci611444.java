import java.util.*;
import java.io.*;

// 백준 11444번 - 피보나치 수 6

class Main {

    static Map<Long, Long> memoization;

    static final int MOD = 1_000_000_007;

    private static long fib(Long n) {
        if (memoization.containsKey(n)) {
            return memoization.get(n);
        }
        if (n%2 == 1) {
            long a = fib((n+1)/2);
            long b = fib((n-1)/2);
            memoization.put(n, (a*a + b*b) % MOD);
        } else {
            long a = fib(n/2);
            long b = fib(n/2 - 1);
            memoization.put(n, (a*(a + 2*b)) % MOD);
        }
        return memoization.get(n);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(bf.readLine());
        memoization = new HashMap<>();
        memoization.put(0L, 0L);
        memoization.put(1L, 1L);
        memoization.put(2L, 1L);
        memoization.put(3L, 2L);
        long answer = fib(N);
        System.out.println(answer);
    }
}
