import java.util.*;
import java.io.*;

// 백준 2533번 사회망 (SNS)
class Main {
    
    static Map<Integer, List<Integer>> tree;
    static boolean[] visited;
    static int[][] dp;    // dp[i][0] : i 노드가 얼리어답터일 때 조건을 만족하기 위한 최소 얼리어답터 수

    private static void solve(int parent) {
        visited[parent] = true;
        dp[parent][0] = 1;
        for (int child : tree.get(parent)) {
            if (visited[child]) continue;
            solve(child);
            dp[parent][0] += Math.min(dp[child][0], dp[child][1]);
            // 부모가 얼리어답터가 아닐 때 자식은 무조건 얼리어답터여야 함.
            dp[parent][1] += dp[child][0];
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new HashMap<>();
        for (int i=1; i<=N; i++) {
            tree.put(i, new ArrayList<>());
        }
        dp = new int[N+1][2];
        visited = new boolean[N+1];
        StringTokenizer st;
        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        solve(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
}
