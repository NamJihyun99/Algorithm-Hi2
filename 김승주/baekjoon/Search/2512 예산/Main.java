import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] requests = new int[N];
        int total = 0;
        int max = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            total += requests[i];
            max = Math.max(max, requests[i]);
        }

        int M = Integer.parseInt(br.readLine());
        if (total <= M) {
            System.out.println(max);
            return;
        }
        int min = 1;
        int mid;
        while (min <= max) {
            mid = (min + max) >> 1;
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (requests[i] < mid) {
                    sum += requests[i];
                } else {
                    sum += mid;
                }
            }

            if (M < sum) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(max);
    }
}