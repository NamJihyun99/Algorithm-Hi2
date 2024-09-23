import java.util.*;
import java.io.*;

// 백준 1865번 - 웜홀

class Main {

    static final int MAX = 52_000_010;

    static int N;
    static List<int[]> edges;

    private static boolean hasNegativeCycle() {
        int[] dist = new int[N+1];
        boolean[] visited = new boolean[N+1];
        int start = 1;
        Arrays.fill(dist, MAX);
        boolean update = false;
        while (!visited[start]) {
            dist[start] = 0;
            visited[start] = true;
            for (int i=1; i<N; i++) {
                update = false;
                for (int[] edge: edges) {
                    if (dist[edge[0]]!=MAX && dist[edge[1]] > dist[edge[0]] + edge[2]) {
                        dist[edge[1]] = dist[edge[0]] + edge[2];
                        update = true;
                    }
                }
                if (!update) break;
            }
            if (update) {
                for (int[] edge: edges) {
                    if (dist[edge[0]]!=MAX && dist[edge[1]] > dist[edge[0]] + edge[2]) {
                        return true;
                    }
                }
            }
            for (int node=1; node<=N; node++) {
                if (dist[node] == MAX && !visited[node]) {
                    start = node;
                    break;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0; t<TC; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            for (int i=0; i<M; i++) {
                st = new StringTokenizer(bf.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                edges.add(new int[]{S, E, V});
                edges.add(new int[]{E, S, V});
            }
            for (int i=0; i<W; i++) {
                st = new StringTokenizer(bf.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int V = -1 * Integer.parseInt(st.nextToken());
                edges.add(new int[]{S, E, V});
            }
            if (hasNegativeCycle()) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
