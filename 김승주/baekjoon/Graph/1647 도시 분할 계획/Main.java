import java.io.*;
import java.util.*;

class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Street> edges = new PriorityQueue<>((s1, s2) -> s1.cost - s2.cost);
        parent = new int[N + 1];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.offer(new Street(A, B, C));
        }

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int edgeCnt = 0;
        int minCost = 0;
        int existingMaxCost = 0;
        while (edgeCnt < N - 1) {
            Street curr = edges.poll();
            int rootA = find(curr.houseA);
            int rootB = find(curr.houseB);
            if (rootA != rootB) {
                parent[rootB] = rootA;
                existingMaxCost = Math.max(existingMaxCost, curr.cost);
                minCost += curr.cost;
                edgeCnt++;
            } 
        }

        minCost -= existingMaxCost;
        System.out.println(minCost);
    }

    static class Street {
        int houseA;
        int houseB;
        int cost;

        Street(int houseA, int houseB, int cost) {
            this.houseA = houseA;
            this.houseB = houseB;
            this.cost = cost;
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}