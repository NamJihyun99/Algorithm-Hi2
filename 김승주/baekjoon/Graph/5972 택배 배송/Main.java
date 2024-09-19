import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static List<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        int[] distances = new int[N + 1];
        boolean[] isVisited = new boolean[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[1] = 0;
        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (isVisited[curr.to]) continue;

            isVisited[curr.to] = true;
            for (Edge neighbor : graph[curr.to]) {
                if (distances[neighbor.to] > distances[curr.to] + neighbor.weight) {
                    distances[neighbor.to] = distances[curr.to] + neighbor.weight;
                    pq.add(new Edge(neighbor.to, distances[neighbor.to]));
                }
            }
        }
        return distances[N];
    }

    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}