import java.util.*;
import java.io.*;

// 백준 2512번 - 예산
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] requests = new int[N];
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            sum += requests[i];
        }
        int M = Integer.parseInt(br.readLine());
        Arrays.sort(requests);
        if (sum <= M) { // 모두 요청 금액을 배정할 때
            System.out.println(requests[N-1]);
            return;
        }
        if (M/N <= requests[0]) { // 모든 요청에 상한액을 배정해야 할 때
            System.out.println(M/N);
            return;
        }
        for (int i=0; i<N-1; i++) { // requests[i]까지는 요청 금액을 배정할 때
            M -= requests[i];
            if (M/(N-i-1) <= requests[i+1]) {
                System.out.println(M/(N-i-1));
                return;
            }
        }
    }
}
