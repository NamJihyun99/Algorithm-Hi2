import java.util.*;
import java.io.*;

// 백준 1647번 도시 분할 계획
class Main {

    static int[] parents;
    static int sum, count;

    static int findParent(int child) {
        if (parents[child] == child) return child;
        return parents[child] = findParent(parents[child]);
    }

    static boolean union(int nodeA, int nodeB) {
        int parentA = findParent(nodeA);
        int parentB = findParent(nodeB);
        if (parentA == parentB) {
            return false;
        }
        parents[parentB] = parentA;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        
        int dep, dst, value;

        Edge(int dep, int dst, int value) {
            this.dep = dep;
            this.dst = dst;
            this.value = value;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.value - edge.value;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        parents = new int[N+1];
        for (int i=1; i<=N; i++) {
            parents[i] = i;
        }
        
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), 
                               Integer.parseInt(st.nextToken()), 
                               Integer.parseInt(st.nextToken())));
        }
        
        sum = 0;
        count = 0;
        while (!edges.isEmpty() && count<N-2) {
            Edge edge = edges.poll();
            if (union(edge.dep, edge.dst)) {
                count++;
                sum += edge.value;
            }
        }
        System.out.println(sum);
    }
}
