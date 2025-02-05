import java.util.*;
import java.io.*;

// 백준 15681번 트리와 쿼리 (재귀 풀이)
class Main {

    static HashMap<Integer, List<Integer>> graph;
    static int[] size;
    
    static class Node {
        int number;
        List<Node> children;

        Node(int number) {
            this.number = number;
            children = new ArrayList<>();
        }

        void connectWith(Node parent) {
            for (Integer next : graph.get(this.number)) {
                if (next == parent.number) continue; 
                Node child = new Node(next);
                this.children.add(child);
                child.connectWith(this);
            }
        }

        void countSubTree() {
            size[this.number] = 1;
            for (Node child : this.children) {
                child.countSubTree();
                size[this.number] += size[child.number];
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Node root = new Node(Integer.parseInt(st.nextToken()));
        int Q = Integer.parseInt(st.nextToken());
        graph = new HashMap<>();
        size = new int[N+1];
        for (int i=1; i<=N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            graph.get(U).add(V);
            graph.get(V).add(U);
        }
        root.connectWith(new Node(-1));
        root.countSubTree();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(size[q]).append("\n");
        }
        System.out.print(sb);
    }
}
