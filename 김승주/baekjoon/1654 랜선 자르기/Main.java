import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] lans;
    static long answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lans = new int[K];

        int maxLan = 0;
        for (int i = 0; i < K; i++) {
            lans[i] = Integer.parseInt(br.readLine());
            maxLan = Math.max(maxLan, lans[i]);
        }
        binarySearch(1, maxLan);
        System.out.println(answer);
    }

    private static void binarySearch(long start, long end) {
        if (start > end)    return;

        long mid = (start + end) / 2;
        int count = 0;
        for (int lan : lans) {
            count += (lan / mid);
        }

        if (count >= N) {
            answer = Math.max(answer, mid);
            binarySearch(mid + 1, end);
        } else {
            binarySearch(start, mid - 1);
        }
    }
}