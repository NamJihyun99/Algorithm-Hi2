import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            long answer = 0;
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int peak = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (peak < arr[i]) {
                    peak = arr[i];
                } else {
                    answer += peak - arr[i];
                }
            }

            sb.append(answer).append('\n');
        }     

        System.out.print(sb.toString());
    }
}