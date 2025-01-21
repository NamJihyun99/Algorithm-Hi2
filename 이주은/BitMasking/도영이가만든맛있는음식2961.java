import java.util.*;
import java.io.*;

class Main {
    static int N;
    
    static int[] sour;
    static int[] bitter;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sour = new int[N];
        bitter = new int[N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        int L = 1<<N;
        int min = 1_000_000_000;
        
        for(int bit=1; bit<L; bit++) {
            int s = 1;
            int b = 0;
            
            for(int i=0; i<N; i++) {
                if((bit & (1<<i) )> 0) {
                    s *= sour[i];
                    b += bitter[i];
                }
            }

            min = Math.min(min, Math.abs(s-b));
        }

        System.out.println(min);
    }
}
