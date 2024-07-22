import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// ACM Craft(골드 3)
// 위상정렬
 /*
    1. 메모리 초과
    -> if(degree[n] == 0) q.offer(n)로 해결
 */
public class BaekJoon1005 {
    static int n;
    static int[] acm;
    static List<List<Integer>> list;
    static int[] degree;
    static int[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            acm = new int[n + 1];
            degree = new int[n + 1];
            cost = new int[n + 1];
            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                acm[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list.get(s).add(e); // 단방향 그래프
                degree[e]++; // 위상정렬을 위한 차수 배열
            }
            int w = Integer.parseInt(br.readLine());
            // 위상정렬
            topologicalSort();
            System.out.println(cost[w]);
        }
    }

    private static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            // 최상위 노드를 저장
            if(degree[i] == 0) {
                q.offer(i);
                cost[i] = acm[i]; // 내 건물 건설 시간
            }
        }

        while (!q.isEmpty()) {
            int t = q.poll();
            for (int n : list.get(t)) {
                degree[n]--;
                cost[n] = Math.max(acm[n] + cost[t], cost[n]);
                if(degree[n] == 0) q.offer(n); // 모든 n을 q에 넣으면 메모리 초과가 남. 탐색해야할 때(차수가 0일 때)만 넣어줘라
            }
        }
    }
}
