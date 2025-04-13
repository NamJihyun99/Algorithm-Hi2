import java.util.*;
import java.io.*;

// 백준 1766번 문제집
class Main {

    static HashMap<Integer, List<Integer>> graph;
    static int[] inDegree;
    static int N;

    private static String solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            if (inDegree[i]==0) {
                pq.add(i);
            }
        }
        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(" ");
            for (Integer next : graph.get(now)) {
                inDegree[next]--;
                if(inDegree[next] == 0) {
                    pq.add(next);
                }
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new HashMap<>();
        inDegree = new int[N+1];
        for (int i=1; i<=N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            inDegree[b]++;
        }
        System.out.println(solve());
    }
}
