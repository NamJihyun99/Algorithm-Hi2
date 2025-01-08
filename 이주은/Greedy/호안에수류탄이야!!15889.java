import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        if(N == 1) {
            System.out.println("권병장님, 중대장님이 찾으십니다");
            return;
        }
        
        int[] pos = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int range = 0;
        
        for(int i=1; i<N; i++) {
            int pow = Integer.parseInt(st.nextToken());

            range = Math.max(range, pos[i-1] + pow);

            if(range < pos[i]) {
                System.out.println("엄마 나 전역 늦어질 것 같아");
                return;
            }
        }
        
        System.out.println("권병장님, 중대장님이 찾으십니다");
    }
}
