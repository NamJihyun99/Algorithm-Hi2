import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Island>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Island(B, C));
            graph[B].add(new Island(A, C));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(bfs(graph, N, start, end));
    }

    private static int bfs(List<Island>[] graph, int N, int start, int end) {
        int maxWeight = 0;
        boolean[] visited = new boolean[N + 1];
        Queue<Island> q = new LinkedList<>();
        q.offer(new Island(start, Integer.MAX_VALUE));
        visited[start] = true;

        while (!q.isEmpty()) {
            Island curr = q.poll();
            if (curr.num == end) {
                maxWeight = Math.max(maxWeight, curr.weight);
                continue;
            }

            for (Island neighbor : graph[curr.num]) {
                if (!visited[neighbor.num]) {
                    visited[neighbor.num] = true;
                    q.offer(new Island(neighbor.num, Math.min(curr.weight, neighbor.weight)));
                }
            }
        }

        return maxWeight;
    }

    static class Island {
        int num;
        int weight;

        Island (int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
}