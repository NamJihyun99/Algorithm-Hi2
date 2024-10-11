import java.util.*;
import java.io.*;

// 백준 1654번 - 랜선 자르기

class Main {

    static int[] lengths;
    static int K, N;
    static long answer = 0L;

    private static void search(long left, long right) {
        if (left > right) {
            return;
        }
        long mid = (left + right)/2;
        int num = count(mid);
        if (num < N) {
            search(left, mid-1);
        } else if (num >= N) {
            answer = mid;
            search(mid+1, right);
        }
    }

    private static int count(long len) {
        int sum = 0;
        for (int i=0; i<K; i++) {
            sum += lengths[i]/len;
        }
        return sum;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lengths = new int[K];
        long max = 0L;
        for (int i=0; i<K; i++) {
            lengths[i] = Integer.parseInt(bf.readLine());
            max = Math.max(max, lengths[i]);
        }
        search(1, max+1);
        System.out.println(answer);
    }
}
