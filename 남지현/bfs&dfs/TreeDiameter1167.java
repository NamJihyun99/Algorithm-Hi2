import java.util.*;
import java.io.*;

// 백준 1167번 - 트리의 지름

class Main {

    static Map<Integer, List<int[]>> graph;
    static boolean[] visited;
    static int answer, farthestNode;

    private static void dfs(int node, int sum) {
        for (int[] next: graph.get(node)) {
            if (!visited[next[0]]) {
                visited[next[0]]=true;
                dfs(next[0], sum+next[1]);
                visited[next[0]]=false;
            }
        }
        if (answer < sum) {
            answer = sum;
            farthestNode = node;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int V = Integer.parseInt(bf.readLine());
        graph = new HashMap<>();
        List<Integer> terminals = new ArrayList<>();
        for (int i=1; i<=V; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i=1; i<=V; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = 0;
            while ((end = Integer.parseInt(st.nextToken())) != -1) {
                int value = Integer.parseInt(st.nextToken());
                graph.get(start).add(new int[]{end, value});
            }
            if (graph.get(start).size() == 1) {
                terminals.add(start);
            }
        }
        answer = 0;
        farthestNode = 0;
        visited = new boolean[V+1];
        visited[1] = true;
        dfs(1, 0);
        visited[1]=false;
        visited[farthestNode] = true;
        dfs(fartestNode, 0);
        System.out.println(answer);
    }
}
