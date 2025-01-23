import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int left = 1;
        int right = 1;

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
            right = Math.max(right, C);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        while (left <= right) { 
            int mid = (left + right) / 2;
            if (bfs(graph, N, start, end, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }

    private static boolean bfs(List<Island>[] graph, int N, int start, int end, int target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        boolean[] visited = new boolean[N + 1];
        pq.offer(start);

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            if (curr == end) {
                return true;
            }

            for (Island neighbor : graph[curr]) {
                if (!visited[neighbor.num] && neighbor.weight >= target) {
                    visited[neighbor.num] = true;
                    pq.offer(neighbor.num);
                }
            }
        }

        return false;
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