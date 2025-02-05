import java.io.*;
import java.util.*;

class Main {
    static List<Integer>[] graph;
    static List<Integer> innerCircles = new ArrayList<>();
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        answer = new int[N + 1];
        Arrays.fill(answer, -1);

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // 순환선에 속하는 역 모두 찾기
        List<Integer> history = new ArrayList<>();
        history.add(1);
        dfs(history);

		// 순환선에 속하는 역에 대해 거리를 0으로 설정
        for (int station : innerCircles) {
            answer[station] = 0;
        }

		// 순환선에 속하는 역인데 간선이 2개보다 많다면 해당 역에서 뻗어나오는 지선이 존재한다는 뜻.
        // -> bfs를 통해 지선에 대한 탐색 수행
        for (int station : innerCircles) {
            if (graph[station].size() > 2) {
                bfs(station);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(List<Integer> history) {
        if (!innerCircles.isEmpty()) {
            return;
        }
        int curr = history.get(history.size() - 1);
        for (int neighbor : graph[curr]) {
        	// 이미 방문한 노드에 다시 방문했다면?
            if (history.contains(neighbor)) {
            	// 직전에 방문한 노드인 경우 이미 탐색한 경로이므로 스킵
                if (history.get(history.size() - 2) == neighbor) {
                    continue;
                }
                // 직전 방문 노드가 아닌 경우 -> 사이클 발견
                for (int i = history.indexOf(neighbor); i < history.size(); i++) {
                    innerCircles.add(history.get(i));
                }
                return;
            }
            
            // 방문한 적 없는 노드라면 히스토리에 추가하고 계속해서 dfs 탐색 수행
            history.add(neighbor);
            dfs(history);
            // 방문한 적 없는 노드에 대해 dfs를 종료했다면 해당 노드는 다시 히스토리에서 삭제
            history.remove(history.size() - 1);
        }
    }

    private static void bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        // {노드 번호, 순환선에 속하는 역과의 거리}
        q.offer(new int[] {start, 0});
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            for (int neighbor : graph[curr[0]]) {
            	// 이미 거리 구한 역이면 스킵
                if (answer[neighbor] >= 0)   continue;
                // curr에서 한 번 더 가야 neighbor에 도달 가능
                answer[neighbor] = curr[1] + 1;
                // 너비를 넓혀가며 bfs를 계속 수행
                q.offer(new int[] {neighbor, curr[1] + 1});
            }
        }
    }
}