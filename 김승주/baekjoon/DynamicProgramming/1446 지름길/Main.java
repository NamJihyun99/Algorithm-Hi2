import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int D;
    static List<Edge>[] graph;
    static int[][] dp;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        dp = new int[D + 1][D + 1];
        visited = new boolean[D + 1][D + 1];
        graph = new List[D + 1];

        for (int i = 0; i <= D; i++) {
            graph[i] = new ArrayList<>();
            for (int j = i + 1; j <= D; j++) {
                dp[i][j] = j - i;
            }
        }

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int length = Integer.parseInt(input[2]);

            // 역주행할 수 없으므로 목적지를 벗어나는 지름길 정보는 저장하지 않음.
            if (start > D || end > D) {
                continue;
            }

            boolean exists = false;
            for (Edge edge : graph[start]) {
                if (edge.to == end) {
                    // 이미 같은 도착지를 가진 지름길이 있으면 더 짧은 지름길로 갱신
                    edge.length = Math.min(edge.length, length);
                    exists = true;
                    break;
                }
            }
        
            // 동일한 지름길이 없으면 새로 추가
            if (!exists) {
                graph[start].add(new Edge(end, length));
            }
        }
        System.out.println(findShortestPath(0, D));
    }

    private static int findShortestPath(int start, int end) {
        if (start == end)   return 0;
        

        if (!visited[start][end]) {
            for (int i = start; i < end; i++) {
                for (Edge next : graph[i]) {
                    if (next.to > end)  continue;
                    dp[start][end] = Math.min(dp[start][end], findShortestPath(start, i) + next.length + findShortestPath(next.to, end));
                }
            }
            visited[start][end] = true;
        }
        
        return dp[start][end];
    }

    static class Edge {
        int to;
        int length;

        Edge(int to, int length) {
            this.to = to;
            this.length = length;
        }
    }
}