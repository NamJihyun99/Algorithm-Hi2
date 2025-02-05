import java.util.*;
import java.io.*;

// 백준 2512번 - 예산 (이분탐색 풀이)
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] requests = new int[N];
        int right = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, requests[i]);
        }
        int M = Integer.parseInt(br.readLine());
        Arrays.sort(requests);
        // 정수 상한액이 커질수록 합계도 커진다.
        int left = M/N;
        while (left <= right) {
            int mid = (left+right)/2;
            int sum = 0;
            for (int i=0; i<N; i++) {
                if (requests[i] > mid) {
                    sum += mid;
                } else {
                    sum += requests[i];
                }
            }
            if (M >= sum) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        System.out.println(right);
    }
}
