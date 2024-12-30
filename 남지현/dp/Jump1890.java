import java.util.*;
import java.io.*;

// 백준 1890번 점프
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        long[][] count = new long[N][N];
        count[0][0] = 1l;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                int jump = Integer.parseInt(st.nextToken());
                if (count[i][j] == 0) continue;
                if (i==N-1 && j==N-1) break;
                if (i+jump < N) {
                    count[i+jump][j] += count[i][j];
                }
                if (j+jump < N) {
                    count[i][j+jump] += count[i][j];
                }
            }
        }
        System.out.println(count[N-1][N-1]);
    }
}
