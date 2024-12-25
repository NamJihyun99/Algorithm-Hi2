import java.util.*;
import java.io.*;

// 백준 2003번 - 수들의 합 2
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] sum = new int[N+1];
        st = new StringTokenizer(br.readLine());
        sum[0] = 0;
        for (int i=1; i<=N; i++) {
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
        }
        int left = 0, right = 1, count = 0;
        while (right <= N) {
            int number = sum[right] - sum[left];
            if (number < M) {
                right++;
            } else {
                if (number == M) {
                    count++;
                    right++;
                }
                left++;
            }
        }
        System.out.println(count);
    }
}
