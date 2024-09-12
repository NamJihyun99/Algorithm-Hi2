import java.util.*;
import java.io.*;

// 백준 5972번 - 택배 배송

class Main {

    static int N;
    static List<List<Edge>> graph;

    static final int MAX = 50_000_001;

    private static int dijkstra() {
        int[] dist = new int[N+1];
        Arrays.fill(dist, MAX);
        PriorityQueue<Edge> pq = new PriorityQueue<>(); 
        dist[1] = 0;
        pq.add(new Edge(1, 0));
        while (! pq.isEmpty()) {
            Edge now = pq.poll();
            if (dist[now.vertex] < now.cost) continue;
            for (Edge next: graph.get(now.vertex)) {
                if (dist[next.vertex] > dist[now.vertex]+next.cost) {
                    dist[next.vertex] = dist[now.vertex]+next.cost;
                    pq.add(new Edge(next.vertex, dist[next.vertex]));
                }
            }
        }
        return dist[N];
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(bf.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            graph.get(S).add(new Edge(E, V));
            graph.get(E).add(new Edge(S, V));
        }
        System.out.println(dijkstra());
    }

    static class Edge implements Comparable<Edge> {
        int vertex;
        int cost;

        Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }
}
