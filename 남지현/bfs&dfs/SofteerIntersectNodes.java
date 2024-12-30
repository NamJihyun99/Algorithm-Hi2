import java.io.*;
import java.util.*;

// Softeer - [HSAT 6회 정기 코딩 인증평가 기출] 출퇴근길
public class Main {

    static int n, S, T;
    static boolean[] visited;
    static Map<Integer, List<Integer>> originalGraph, reverseGraph;

    private static int solution() {
        Set<Integer> ST = getReachableNodes(S, T);
        Set<Integer> TS = getReachableNodes(T, S);
        
        ST.retainAll(TS);
        int answer = ST.size();
        if (ST.contains(S)) {
            answer--;
        }
        if (ST.contains(T)) {
            answer--;
        }
        return answer;
    }

    private static Set<Integer> getReachableNodes(int start, int end) {
        Set<Integer> fromStart = new HashSet<>();
        Set<Integer> fromEnd = new HashSet<>();
        visited = new boolean[n+1];
        dfs(start, end, originalGraph, fromStart);
        visited = new boolean[n+1];
        dfs(end, -1, reverseGraph, fromEnd);
        fromStart.retainAll(fromEnd);
        return fromStart;
    }

    private static void dfs(int node, int end, Map<Integer, List<Integer>> graph, Set<Integer> nodes) {
        if (end!=-1 && node == end) {
            return;
        }
        for (int next: graph.get(node)) {
            if (!visited[next]) {
                visited[next] = true;
                nodes.add(next);
                dfs(next, end, graph, nodes);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        originalGraph = new HashMap<>();
        reverseGraph = new HashMap<>();
        for (int i=1; i<=n; i++) {
            originalGraph.put(i, new ArrayList<>());
            reverseGraph.put(i, new ArrayList<>());
        }
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            Integer fromNode = Integer.valueOf(st.nextToken());
            Integer toNode = Integer.valueOf(st.nextToken());
            originalGraph.get(fromNode).add(toNode);
            reverseGraph.get(toNode).add(fromNode);
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        System.out.println(solution());
    }
}
