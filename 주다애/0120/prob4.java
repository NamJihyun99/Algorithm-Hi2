import java.util.*;

class Solution {
    static List<List<Node>> graph = new ArrayList<>();
    static int[] distA;
    static int[] distB;
    static int[] distS;
    static int max = Integer.MAX_VALUE;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = max;
        for(int i = 0; i<= n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] f : fares) {
            graph.get(f[0]).add(new Node(f[1], f[2]));
            graph.get(f[1]).add(new Node(f[0], f[2]));
        }
        distA = new int[n + 1];
        distB = new int[n + 1];
        distS = new int[n + 1];

        dijkstra(a, n, distA);
        dijkstra(b, n, distB);
        dijkstra(s, n, distS);
        
        for(int i = 1; i <= n; i++) {
            // System.out.println(distA[i] + " " + distB[i] + " " + distS[i]);
            if(distA[i] != max && distB[i] != max && distS[i] != max) {
                answer = Math.min(answer, distA[i] + distB[i] + distS[i]);
            }
        }
        return answer;
    }
    
    static void dijkstra(int start, int n, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        pq.add(new Node(start, 0));
        Arrays.fill(dist, max);
        dist[start] = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.f]) continue;
            visited[now.f] = true;
            
            for(Node c : graph.get(now.f)) {
                int f = c.f;
                int cost = c.cost;
                if(dist[f] > dist[now.f] + cost) {
                    dist[f] = dist[now.f] + cost;
                    pq.offer(new Node(f, dist[f]));
                }
            }
        }
    }
    
    static class Node implements Comparable<Node> {
        int f;
        int cost;
        
        Node(int f, int cost) {
            this.f = f;
            this.cost = cost;
        }
        
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
