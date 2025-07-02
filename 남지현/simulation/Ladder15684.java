import java.util.*;
import java.io.*;

// 백준 15684번 사다리 조작
class Main {

    static int answer, N, H;
    static int[][] ladder;

    private static void dfs(int depth, int idx) {
        if (answer <= depth) return;
        if (depth <= 3) {
            if (success()) {
                answer = depth;
                return;
            }
            if (depth == 3) return;
        }

        for (int i=idx; i<N*H; i++) {
            int r = i/N;
            int c = i%N;
            if (c==N-1 || ladder[r][c]!=0 || ladder[r][c+1]!=0) continue;
            ladder[r][c] = 1;
            ladder[r][c+1] = -1;
            dfs(depth+1, idx+1);
            ladder[r][c] = 0;
            ladder[r][c+1] = 0;
        }
    }

    private static boolean success() {
        for (int i=0; i<N; i++) {
            int r = 0, c = i;
            while (r < H) {
                if (ladder[r][c] == 1) c++;
                else if (ladder[r][c] == -1) c--;
                r++;
            }
            if (c != i) return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H][N];
        int[] colSum = new int[N];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            ladder[a][b] = 1;
            ladder[a][b+1] = -1;
            colSum[b]++;
        }
        int count = 0;
        for (int i=0; i<N; i++) {
            if (colSum[i]%2 == 1) count++; // 실패하는 column
        }
        if (count > 3) {
            System.out.println(-1);
            return;
        }
        answer = 4;
        dfs(0, 0);
        System.out.println(answer==4 ? -1 : answer);
    }
}
