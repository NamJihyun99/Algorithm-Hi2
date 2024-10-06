import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] res = new int[N + 2];
        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken()); 
            if (i + T - 1 <= N) {
                res[i + T] = Math.max(res[i + T], res[i] + P);
            }
            res[i + 1] = Math.max(res[i],res[i + 1]);
        }
        System.out.println(res[N + 1]);
    }
}