import java.util.*;
import java.io.*;

// 백준 16947번 서울 지하철 2호선
class Main {

    static int N;
    static boolean isCycle;
    static int[] answer;
    static boolean[] visited;
    static HashMap<Integer, List<Integer>> graph;
    static ArrayDeque<Integer> queue;

    private static void solve() {
        isCycle = false;
        visited = new boolean[N+1];
        answer = new int[N+1];
        Arrays.fill(answer, -1);
        queue = new ArrayDeque<>();
        searchCycle(1, -1);
        calculateDists();
    }

    private static void searchCycle(int now, int prev) {
        visited[now] = true;
        for (int next : graph.get(now)) {
            if (!visited[next]) {
                searchCycle(next, now);
                if (isCycle) {
                    if (answer[next] == 0) {
                        isCycle = false;
                    } else {
                        answer[next] = 0;
                        queue.addLast(next);
                    }
                    return;
                }
            } else if (prev != next) {
                isCycle = true;
                answer[next] = 0;
                queue.addLast(next);
                break;
            }
        }
    }

    private static void calculateDists() {
        int count = 1;
        while(!queue.isEmpty()) {
            int initialSize = queue.size();
            for (int i=0; i<initialSize; i++) {
                int node = queue.pollFirst();
                for (Integer next : graph.get(node)) {
                    if (answer[next] != -1) continue;
                    answer[next] = count;
                    queue.add(next);
                }
            }
            count++;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        StringTokenizer st;
        int U, V;
        for (int i=1; i<=N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            U = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            graph.get(U).add(V);
            graph.get(V).add(U);
        }
        solve();
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
