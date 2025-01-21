import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] answer = new int[2];
        answer[0] = 1000000000;
        answer[1] = 1000000000;

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] liq = new int[N];
        for (int i = 0; i < N; i++) {
            liq[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liq);
    
        for (int left = 0, right = N - 1; left < right; ) {
            int feature = liq[left] + liq[right];
            if (Math.abs(feature) < Math.abs(answer[0] + answer[1])) {
                answer[0] = liq[left];
                answer[1] = liq[right];
                if (feature == 0) {
                    break;
                }
            }
            if (feature > 0) {
                right--;
            } else {
                left++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb.toString());
    }
}