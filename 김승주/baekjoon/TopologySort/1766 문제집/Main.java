import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] edgeCount = new int[N + 1];
        List<Integer> orders[] = new List[N + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            orders[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            orders[A].add(B);
            edgeCount[B]++;
        }

        for (int i = 1; i <= N; i++) {
            if (edgeCount[i] == 0) {
                pq.offer(i);
            }
        }
        
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr).append(" ");
            for (int i = 0; i < orders[curr].size(); i++) {
                if (--edgeCount[orders[curr].get(i)] == 0) {
                    pq.offer(orders[curr].get(i));
                }
            }
        }

        System.out.println(sb.toString());
    }
}