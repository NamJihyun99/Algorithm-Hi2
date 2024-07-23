import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 특정한 최단 경로(골드 4)
// 최단 경로 -> 다익스트라
// 75%에서 틀림 -> res1과 res2 중 작은 값이 아니라 둘 중 하나라도 max보다 크거나 같으면 조건 충족 X
public class BaekJoon1504 {
    static int n;
    static List<List<Node>> graph;
    static int max = 200000000; // 200000 * 1000
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 양방향
            graph.get(start).add(new Node(end, cost));
            graph.get(end).add(new Node(start, cost));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int res1 = 0;
        res1 += dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, n);

        int res2 = 0;
        res2 += dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, n);
        if(res1 >= max || res2 >= max) {
            System.out.println(-1);
            return;
        }
        int res = Math.min(res1, res2);
        System.out.println(res);
    }

    private static int dijkstra(int s, int e) {
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, max);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.des]) continue;
            visited[now.des] = true;

            for (Node next : graph.get(now.des)) {
                if(dist[next.des] > next.cost + dist[now.des]) {
                    dist[next.des] = next.cost + dist[now.des];
                    pq.offer(new Node(next.des, dist[next.des]));
                }
            }
        }
        return dist[e];
    }


    static class Node implements Comparable<Node>{
        int des;
        int cost;

        public Node(int des, int cost) {
            this.des = des;
            this.cost = cost;
        }

        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
}
