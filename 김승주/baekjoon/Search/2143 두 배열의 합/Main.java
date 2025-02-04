import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Long> sumOfA = new HashMap<>();
        for (int start = 0; start < n; start++) {
            int sum = 0;
            for (int end = start; end < n; end++) {
                sum += A[end];
                sumOfA.put(sum, sumOfA.getOrDefault(sum, 0L) + 1);
            }
        }

        Map<Integer, Long> sumOfB = new HashMap<>();
        for (int start = 0; start < m; start++) {
            int sum = 0;
            for (int end = start; end < m; end++) {
                sum += B[end];
                sumOfB.put(sum, sumOfB.getOrDefault(sum, 0L) + 1);
            }
        }

        long answer = 0;
        for (Integer subA : sumOfA.keySet()) {
            if (sumOfB.keySet().contains(T - subA)) {
                answer += (sumOfA.get(subA) * sumOfB.get(T - subA));
            }
        }

        System.out.println(answer);
    }
}