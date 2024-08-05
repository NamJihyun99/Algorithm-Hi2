//백준 1012번 유기농 배추 (https://www.acmicpc.net/problem/1012)

import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] delta = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            sb.append(solve()).append("\n");
        }

        System.out.println(sb);
    }

    public static int solve() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] farm = new boolean[M][N];
        boolean[][] visited = new boolean[M][N];
        
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            farm[x][y] = true;
        }

        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(farm[i][j] && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j, M, N, farm, visited);
                    answer ++;
                }
            }
        }

        return answer;
    }

    public static void dfs(int i, int j, int M, int N, boolean[][] farm, boolean[][] visited) {
        for(int[] d: delta) {
            int nx = i + d[0];
            int ny = j + d[1];

            if(nx < 0 || nx >= M || ny < 0 || ny >= N || !farm[nx][ny] || visited[nx][ny])
                continue;
            
            visited[nx][ny] = true;
            dfs(nx, ny, M, N, farm, visited);
        }
    }
}
