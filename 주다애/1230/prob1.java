import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int s;
    static int t;
    static List<List<Integer>> graph;
    static List<List<Integer>> rgraph;
    static boolean[] froms; // s -> 
    static boolean[] fromt; // t ->
    static boolean[] tos; // -> s
    static boolean[] tot; // -> t
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 그래프
        graph = new ArrayList<>();
        rgraph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            rgraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v); // 순방향 단방향
            rgraph.get(v).add(u); // 역방향 단방향
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        
        froms = new boolean[n + 1];
        fromt = new boolean[n + 1];
        tos = new boolean[n + 1];
        tot = new boolean[n + 1];

        froms[t] = true;
        fromt[s] = true;
        
        dfs(s, froms, graph);
        dfs(t, fromt, graph);
        dfs(s, tos, rgraph);
        dfs(t, tot, rgraph);

        int res = 0;

        for(int i = 1; i <= n; i++) {
            if(froms[i] && fromt[i] && tos[i] && tot[i]) res += 1;
        }
        System.out.println(res - 2); // 집과 회사는 제외
    }

    static void dfs(int v, boolean[] visited, List<List<Integer>> g) {
        if(visited[v]) return;
        visited[v] = true;

        for(int c : g.get(v)) {
            dfs(c, visited, g);
        }
    }
}
