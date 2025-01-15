import java.util.*;

// 프로그래머스 - 합승 택시 요금

class Solution {
    static final int MAX = 2_000_000_001;
    
    HashMap<Integer, ArrayList<Edge>> graph;
    int N;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        initGraph(fares);
        int[] fromSto = dijkstra(s);
        int[] fromAto = dijkstra(a);
        int[] fromBto = dijkstra(b);
        int answer = MAX;
        for (int X=1; X<=N; X++) {
            // S->X + X->A + X->B
            answer = Math.min(answer, fromSto[X]+fromAto[X]+fromBto[X]);
        }
        return answer;
    }

    private void initGraph(int[][] fares) {
        graph = new HashMap<>();
        for (int i=1; i<=N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : fares) {
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
            graph.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }
    }
    
    private int[] dijkstra(int start) {
        int[] dist = new int[N+1];
        boolean[] visited = new boolean[N+1];
        Arrays.fill(dist, MAX);
        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (visited[now.dst]) continue;
            visited[now.dst] = true;
            for (Edge next : graph.get(now.dst)) {
                if (dist[now.dst]+next.value < dist[next.dst]) {
                    dist[next.dst] = dist[now.dst]+next.value;
                    pq.add(new Edge(next.dst, dist[next.dst]));
                }
            }
        }
        return dist;
    }
    
    static class Edge implements Comparable<Edge> {
        int dst;
        int value;
        
        Edge(int dst, int value) {
            this.dst = dst;
            this.value = value;
        }
        
        public int compareTo(Edge edge) {
            return this.value - edge.value;
        }
    }
}
