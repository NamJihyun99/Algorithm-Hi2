import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static int[] req;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int low = 1, high = 1;
        
        N = Integer.parseInt(br.readLine());
        req = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            req[i] = Integer.parseInt(st.nextToken());

            high = Math.max(high, req[i]);
        }

        M = Integer.parseInt(br.readLine());

        System.out.println(search(low, high+1));
    }

    private static int search(int low, int high) {
        while(low + 1 < high) {
            int mid = (low+high)/2;
            
            if(check(mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }

    //특정한 예산을 책정할 경우 정해진 예산으로 배정이 가능한지 검사하는 메소드
    //1~MAX까지 예산을 배정할 수 있다.
    //TTTTFFFF
    private static boolean check(int budget) {  
        int remain = M;
        
        for(int i=0; i<N; i++) {
            remain -= Math.min(budget, req[i]);

            if(remain < 0)
                return false;
        }

        return true;
    }
}
