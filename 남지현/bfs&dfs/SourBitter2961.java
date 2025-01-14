import java.util.*;
import java.io.*;

// 백준 2961번 - 도영이가 만든 맛있는 음식
class Main {

    static final int MAX = 1_000_000_001;
    
    static int N, answer;
    static int[] S, B;

    static void dfs(int s, int b, int depth) {
        if (depth == N) {
            if (b > 0) 
                answer = Math.min(Math.abs(s-b), answer);
            return;
        }
        dfs(s*S[depth], b+B[depth], depth+1);
        dfs(s, b, depth+1);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N];
        B = new int[N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }
        answer = MAX;
        dfs(1, 0, 0);
        System.out.println(answer);
    }
}
