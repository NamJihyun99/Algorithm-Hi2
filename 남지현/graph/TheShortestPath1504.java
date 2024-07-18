import java.util.*;
import java.io.*;

// 백준 1504 - 특정한 최단 경로 (골드4)

class Main {

    static Map<Integer, List<int[]>> graph;
    static int[] dist;
    static int N;

    static final int MAX = 200_000_001;
    
    static class Edge implements Comparable<Edge>{
        int dest;
        int value;

        Edge(int dest, int value) {
            this.dest = dest;
            this.value = value;
        }

        public int compareTo(Edge e) {
            return this.value-e.value;
        }
    }

    private static void dijkstra(int departure) {
        boolean[] visited = new boolean[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, MAX);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(departure, 0));
        dist[departure]=0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (visited[now.dest]) continue;
            visited[now.dest] = true;
            for (int[] next: graph.get(now.dest)) {
                if (dist[next[0]] > dist[now.dest]+next[1]) {
                    dist[next[0]] = dist[now.dest]+next[1];
                    pq.add(new Edge(next[0], dist[next[0]]));
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new HashMap<>();
        for (int dep=1; dep<=N; dep++) {
            graph.put(dep, new ArrayList<>());
        }
        for (int i=0; i<E; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(x).add(new int[]{y, v});
            graph.get(y).add(new int[]{x, v});
        }
        st = new StringTokenizer(bf.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        
        dijkstra(v1);
        int v1ToV2 = dist[v2];
        
        dijkstra(1);
        int dstToV1 = dist[v1];
        int dstToV2 = dist[v2];

        dijkstra(N);
        int v1ToN = dist[v1];
        int v2ToN = dist[v2];
        
        int answer = MAX * 3;
        if (v1ToV2 != MAX && dstToV1 != MAX && v2ToN != MAX) {
            answer = Math.min(answer, dstToV1 + v1ToV2 + v2ToN);
        }
        if (v1ToV2 != MAX && dstToV2 != MAX && v1ToN != MAX) {
            answer = Math.min(answer, dstToV2 + v1ToV2 + v1ToN);
        }
        if (answer == MAX*3) {
            answer = -1;
        }
        System.out.println(answer);
    }
}
