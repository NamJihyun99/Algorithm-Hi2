import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] prev = {Integer.parseInt(br.readLine())};
        for(int i=2; i<=N; i++) {
            int[] now = new int[N];

            st = new StringTokenizer(br.readLine());
            now[0] = prev[0] + Integer.parseInt(st.nextToken());
            for(int j=1; j<i-1; j++) {
                now[j] = Integer.parseInt(st.nextToken()) + Math.max(prev[j-1], prev[j]);       
            }
            now[i-1] = prev[i-2] + Integer.parseInt(st.nextToken());

            prev = now;
        }

        int max = 0;
        for(int i=0; i<N; i++) {
            max = Math.max(max, prev[i]);
        }
        System.out.println(max);
    }
}
