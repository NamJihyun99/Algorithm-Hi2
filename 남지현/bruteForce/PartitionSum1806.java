import java.util.*;
import java.io.*;

// 백준 1806번 - 부분합

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] sum = new int[N+1];
        int answer = 0; int dist = 100_001;
        int left = 1; int right = 1;
        st = new StringTokenizer(bf.readLine());
        for (int i=1; i<=N; i++) {
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
            if (sum[i]>=S && answer==0) {
                right = i;
                answer = sum[i];
            }
        }
        while (right<=N) {
            // 합이 S를 넘기면서 최대한 right와의 거리를 좁히도록 left를 이동시킨다.
            while (left<right && sum[right]-sum[left]>=S) left++;
            // 거리가 기존 거리보다 짧으면 업데이트한다.
            if (dist > right-left+1) {
                dist = right-left+1; 
                answer = sum[right]-sum[--left];
            }
            right++;
        }
        // 현재까지 계산된 값이 S를 넘기지 못하면 0을 출력한다.
        if (answer < S) dist = 0;
        System.out.println(dist);
    }
}
