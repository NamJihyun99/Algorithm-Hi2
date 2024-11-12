import java.util.*;
import java.io.*;

// 백준 9466번 - 텀 프로젝트

class Main {

    static int[] graph; // 노드 하나당 간선은 하나씩만 나온다.
    static boolean[] visited, done;
    static int count;

    private static void dfs(int now) {
        if (done[now]) return;
        if (visited[now]) {
            count++;
            done[now] = true;
        }
        visited[now] = true;
        dfs(graph[now]);
        done[now] = true;
        visited[now] = false;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i=0; i<testCase; i++) {
            count = 0;
            int N = Integer.parseInt(br.readLine());
            done = new boolean[N+1];
            visited = new boolean[N+1];
            graph = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (Integer n=1; n<=N; n++) {
                graph[n] = Integer.parseInt(st.nextToken());
            }
            for (int n=1; n<=N; n++) {
                if (!done[n]) {
                    dfs(n);
                }
            }
            sb.append(N-count).append("\n");
        }
        System.out.print(sb);
    }
}
