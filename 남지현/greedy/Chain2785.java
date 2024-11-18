import java.util.*;
import java.io.*;

// 백준 2785번 - 체인

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] chains = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            chains[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(chains);
        int count = N;
        int tied = 1;
        for (int chain: chains) {
            if (tied + chain >= count) {
                break;
            } else {
                tied += chain;
                count -= 1;
            }
        }
        System.out.println(count-1);
    }
}
