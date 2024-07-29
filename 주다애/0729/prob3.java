import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 나무 탈출(실버 1)
public class BaekJoon15900 {
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        StringTokenizer st;
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(p).add(c);
            graph.get(c).add(p);
        }
        dfs(1, 0);
//        System.out.println(res);
        System.out.println(res % 2 == 0 ? "No" : "Yes");
    }

    private static void dfs(int p, int depth) {
        boolean isLeaf = true;
        visited[p] = true;
        // "깊이" 우선 탐색임을 잊으면 안됨
        for (int c : graph.get(p)) {
            if(!visited[c]) {
                dfs(c, depth + 1);
                isLeaf = false;
            }
        }
        if(isLeaf) res += depth;
    }
}
