import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer>[] tree = new List[N + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> tree[o2].size() - tree[o1].size());
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        for (int i = 1; i <= N; i++) {
            pq.offer(i);
        }

        int result = 0;
        int spreadCount = 0;
        while (spreadCount < N) {
            int curr = pq.poll();
            result++;
            spreadCount++;
            for (int friend : tree[curr]) {
                tree[friend].remove(tree[friend].indexOf(curr));
                if (tree[friend].isEmpty()) {
                    spreadCount++;
                }
                pq.remove(friend);
                pq.offer(friend);
            }
        }

        System.out.println(result);
    }
}