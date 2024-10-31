import java.util.*;
import java.io.*;

// 백준 20922번 - 겹치는 건 싫어

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] counts = new int[100_001];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int head = 0; int tail = 0;
        int answer = -1;
        while (head < N) {
            if (counts[arr[head]] < K) {
                counts[arr[head]]++;
                head++;
            } else {
                counts[arr[tail]]--;
                tail++;
            }
            answer = Math.max(answer, head-tail);
        }
        System.out.println(answer);
    }
}
