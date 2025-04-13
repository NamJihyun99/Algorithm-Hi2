import java.util.*;
import java.io.*;

// 백준 1939번 중량제한 - union find 풀이
class Main {

    static int N, dep, dst;
    static int[] parent;
    static ArrayList<Link> edges;

    public static int unionFind() {
        parent = new int[N+1];
        Collections.sort(edges);
        for (int i=1; i<=N; i++) {
            parent[i] = i;
        }
        for (Link a : edges) {
            union(a.fromNode, a.toNode);
            if (findParent(dep)==findParent(dst)) {
                return a.value;
            }
        }
        return -1;
    }

    private static int findParent(int node) {
        if (node == parent[node]) return node;
        return parent[node] = findParent(parent[node]);
    }

    private static void union(int nodeA, int nodeB) {
        int parentA = findParent(nodeA);
        int parentB = findParent(nodeB);

        if (parentA != parentB) {
            parent[parentB] = parentA;
        }
    }

    static class Link implements Comparable<Link> {
        int fromNode, toNode, value;

        Link (int fromNode, int toNode, int value) {
            this.fromNode = fromNode;
            this.toNode = toNode;
            this.value = value;
        }

        public int compareTo(Link edge) {
            return edge.value - this.value;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        int M = Integer.parseInt(st.nextToken());
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Link(A, B, C));
        }
        st = new StringTokenizer(br.readLine());
        dep = Integer.parseInt(st.nextToken());
        dst = Integer.parseInt(st.nextToken());
        System.out.println(unionFind());
    }
}
