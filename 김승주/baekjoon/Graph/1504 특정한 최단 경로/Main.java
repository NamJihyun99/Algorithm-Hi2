// https://making-a-scene.notion.site/1504-b7111fc180ef473db8184199ec0d4ad7?pvs=4

import java.util.*;
import java.io.*;

class Main {
    static int N;
    static List<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            String[] input = br.readLine().split(" ");
            graph[Integer.parseInt(input[0])].add(new Edge(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
            graph[Integer.parseInt(input[1])].add(new Edge(Integer.parseInt(input[0]), Integer.parseInt(input[2])));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        long[] fromV1 = dijkstra(v1);
        long[] fromV2 = dijkstra(v2);
        if (fromV1[v2] == Long.MAX_VALUE
         || ((fromV1[1] == Long.MAX_VALUE || fromV2[N] == Long.MAX_VALUE) && (fromV1[N] == Long.MAX_VALUE || fromV2[1] == Long.MAX_VALUE))) {
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(fromV1[v2] + fromV1[1] + fromV2[N], fromV1[v2] + fromV1[N] + fromV2[1]));
    }

    private static long[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> (int)(a.weight - b.weight));
        long[] distances = new long[N + 1];
        boolean[] isVisited = new boolean[N + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[start] = 0;
        pq.add(new Edge(start, 0));
        while(!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (isVisited[curr.next])   continue;
            isVisited[curr.next] = true;
            for (Edge neighbor : graph[curr.next]) {
                if (distances[neighbor.next] > distances[curr.next] + neighbor.weight) {
                    distances[neighbor.next] = distances[curr.next] + neighbor.weight;
                    pq.add(new Edge(neighbor.next, distances[neighbor.next]));
                }
            }
        }
        return distances;
    }
    static class Edge {
        int next;
        long weight;
        Edge(int next, long weight) {
            this.next = next;
            this.weight = weight;
        }
    }
}