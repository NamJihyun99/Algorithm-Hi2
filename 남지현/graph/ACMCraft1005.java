import java.util.*;
import java.io.*;

// 백준 1005 - ACM Craft (골드3) 위상 정렬

class Main {

    static List<List<Integer>> graph;
    static int[] costs, times, inDegree;
    static int N;

    static int solution(int destination) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i=1; i<=N; i++) {
            if (inDegree[i]==0) {
                queue.addLast(i);
                times[i] = costs[i];
            }
        }
        while (! queue.isEmpty()) {
            int now = queue.pollFirst();
            for (int next: graph.get(now)) {
                inDegree[next]--;
                if (inDegree[next]==0) queue.addLast(next);
                times[next] = Math.max(times[next], times[now]+costs[next]);
            }
        }
        return times[destination];
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            costs = new int[N+1];
            times = new int[N+1];
            inDegree = new int[N+1];
            graph = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            graph.add(new ArrayList<>());
            for (int i=1; i<=N; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
                graph.add(new ArrayList<>());
            }
            for (int i=0; i<K; i++) {
                st = new StringTokenizer(bf.readLine());
                int dep = Integer.parseInt(st.nextToken());
                int dst = Integer.parseInt(st.nextToken());
                graph.get(dep).add(dst);
                inDegree[dst]++;
            }
            sb.append(solution(Integer.parseInt(bf.readLine()))).append("\n");
        }
        System.out.print(sb);
    }
}
