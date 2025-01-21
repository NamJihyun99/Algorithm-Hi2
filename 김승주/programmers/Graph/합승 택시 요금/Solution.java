import java.util.*;

class Solution {
    static List<Edge>[] graph;
    static List<Edge>[] reversedGraph;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<Edge>[] graph = new List[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] info : fares) {
            graph[info[0]].add(new Edge(info[2], info[1]));
            graph[info[1]].add(new Edge(info[2], info[0]));
        }
        int[] fromS = dijkstra(graph, n, s);
        int[] toA = dijkstra(graph, n, a);
        int[] toB = dijkstra(graph, n, b);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, fromS[i] + toA[i] + toB[i]);
        }
        
        return answer;
    }
    
    private static int[] dijkstra(List<Edge>[] graph, int n, int start) {
        int[] distances = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(distances, 19900001);
        distances[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> (a.cost - b.cost));
        pq.offer(new Edge(0, start));
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (visited[curr.to])   continue;
            visited[curr.to] = true;
            for (Edge neighbor : graph[curr.to]) {
                if (distances[neighbor.to] > distances[curr.to] + neighbor.cost) {
                    distances[neighbor.to] = distances[curr.to] + neighbor.cost;
                    pq.offer(new Edge(distances[neighbor.to], neighbor.to));
                }
            }
        }
        
        return distances;
    }
    
    static class Edge {
        int cost;
        int to;
        public Edge(int cost, int to) {
            this.cost = cost;
            this.to = to;
        }
        
    }
}