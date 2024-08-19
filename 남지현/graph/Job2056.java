import java.util.*;
import java.io.*;

// 백준 2056 - 작업

public class Job2056 {

    // 위상 정렬
    public static void solution1() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] inDegree = new int[N+1];
        int[] times = new int[N+1];
        int[] costs = new int[N+1];
        List<List<Integer>> graph = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
            inDegree[i] = Integer.parseInt(st.nextToken());
            if (i == 1) {
                queue.addLast(i);
                times[i] = costs[i];
            }
            for (int j=0; j<inDegree[i]; j++) {
                graph.get(Integer.parseInt(st.nextToken())).add(i);
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
        int max = 0;
        for (int i=1; i<=N; i++) {
            max = Math.max(max, times[i]);
        }
        System.out.println(max);
    }

    // DP
    public static void solution2() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] times = new int[N+1];
        int max = 0;
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            times[i] = cost;
            for (int j=0; j<num; j++) {
                times[i] = Math.max(times[i], times[Integer.parseInt(st.nextToken())] + cost);
            }
            max = Math.max(max, times[i]);
        }
        System.out.println(max);
    }
    
    public static void main(String[] args) throws Exception {
        solution1();
        solution2();
    }
}
