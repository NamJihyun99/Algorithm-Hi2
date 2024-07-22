// https://making-a-scene.notion.site/1005-ACM-Craft-b3237e38e6664d978927b3b65091affd?pvs=4

import java.util.*;
import java.io.*;

class Main {
    static int[] D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int j = 0; j < T; j++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            D = new int[N + 1];
            List<Edge>[] reverseGraph = new List[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
                reverseGraph[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                String[] input = br.readLine().split(" ");
                reverseGraph[Integer.parseInt(input[1])].add(new Edge(Integer.parseInt(input[0]), D[Integer.parseInt(input[1])]));
            }

            int W = Integer.parseInt(br.readLine());
            long min = Long.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                if (reverseGraph[i].isEmpty()) {
                    long[] cache = new long[N + 1];
                    Arrays.fill(cache, -1);
                    min = Math.min(min, findMin(reverseGraph, new long[N + 1], W, i));
                }
            }
            sb.append(min).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static long findMin(List<Edge>[] graph, long[] cache, int curr, int start) {
        if (cache[curr] == -1) {
            if (curr == start) {
                cache[curr] = D[start];
            } else {
                if (graph[curr].size() == 0) {
                    cache[curr] = D[curr];
                } else {
                    long max = findMin(graph, cache, graph[curr].get(0).to, start);
                    for (int i = 1; i < graph[curr].size(); i++) {
                        max = Math.max(max, findMin(graph, cache, graph[curr].get(i).to, start));
                    }
                    cache[curr] = max + D[curr];
                }
            }   
        }
        return cache[curr];
    }

    static class Edge {
        int to;
        long weight;

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}