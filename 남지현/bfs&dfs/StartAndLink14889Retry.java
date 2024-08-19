import java.util.*;
import java.io.*;

// 백준 14889번 - 스타트와 링크

class Main {

    static boolean[] team;
    static int[][] S;
    static int min, N;

    private static void dfs(int idx, int depth) {
        if (depth == N/2) {
            int start=0;
            int link=0;
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (i<j) {
                        if (team[i] && team[j]) {
                            start += S[i][j]+S[j][i];
                        } else if (!team[i] && !team[j]) {
                            link += S[i][j]+S[j][i];
                        }
                    }
                }
            }
            min = Math.min(min, Math.abs(start-link));
            return;
        }
        for (int i=idx+1; i<N; i++) {
            team[i] = true;
            dfs(i, depth+1);
            team[i] = false;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        S = new int[N][N];
        min = 5000;
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j=0; j<N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        team = new boolean[N];
        team[0] = true;
        dfs(0, 1);
        System.out.println(min);
    }
}
