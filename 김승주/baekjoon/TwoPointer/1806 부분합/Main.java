import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] prefixSum = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            if (k >= S) {
                System.out.println(1);
                return;
            }
            prefixSum[i] = (i == 0) ? k : prefixSum[i - 1] + k;
        }

        if (prefixSum[N - 1] < S) {
            System.out.println(0);
            return;
        }

        int minLength = N + 1;
        for (int i = 0, j = 1; i < j && j < N;) {
            int k = (i == 0) ? prefixSum[j] : prefixSum[j] - prefixSum[i - 1];
            if (k >= S) {
                minLength = Math.min(minLength, j - i + 1);
                i++;
            } else {
                j++;
            }
        }
        System.out.println(minLength);
    }
}