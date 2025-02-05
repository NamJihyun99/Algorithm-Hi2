import java.util.*;
import java.io.*;

// 백준 1939번 중량제한 - parametric search 풀이
class Main {

    static int N, dep, dst;
    static boolean enable;
    static boolean[] visited;
    static ArrayList<Edge>[] graph;

    static int solve() {
        int left = 1, right = 1_000_000_000;
        while (left <= right) {
            int mid = (left + right)/2;
            enable = false;
            visited = new boolean[N+1];
            dfs(dep, mid);
            if (enable) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return right;
    }

    static void dfs(int node, int weight) {
        visited[node] = true;
        if (node == dst) {
            enable = true;
            return;
        }
        for (Edge next : graph[node]) {
            if (visited[next.node] || next.value<weight) continue;
            dfs(next.node, weight);
        }
    }

    static class Edge {
        int node;
        int value;

        Edge (int node, int value) {
            this.node = node;
            this.value = value;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for (int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        int M = Integer.parseInt(st.nextToken());
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Edge(B, C));
            graph[B].add(new Edge(A, C));
        }
        st = new StringTokenizer(br.readLine());
        dep = Integer.parseInt(st.nextToken());
        dst = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }
}
