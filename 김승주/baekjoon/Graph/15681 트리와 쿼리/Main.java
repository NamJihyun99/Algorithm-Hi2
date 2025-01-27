import java.io.*;
import java.util.*;

class Main {
    static int[] numOfNodes;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        numOfNodes = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            graph[U].add(V);
            graph[V].add(U);
        }
        
        Node rootNode = new Node(R);
        makeTree(rootNode);
        numOfNodes[R] = countNodes(rootNode);
        for (int i = 0; i < Q; i++) {
            sb.append(numOfNodes[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void makeTree(Node subRoot) {
        for (int childNum : graph[subRoot.num]) {
            Node childNode = new Node(childNum);
            subRoot.children.add(childNode);
            graph[childNum].remove(graph[childNum].indexOf(subRoot.num));
            makeTree(childNode);
        }
    }

    private static int countNodes(Node subRoot) {
        if (numOfNodes[subRoot.num] > 0) {
            return numOfNodes[subRoot.num];
        }
        if (subRoot.children.isEmpty()) {
            return 1;
        }

        int count = 1;
        for (Node child : subRoot.children) {
            int num = countNodes(child);
            numOfNodes[child.num] = num;
            count += num;
        }
        return count;
    }

    static class Node {
        int num;
        List<Node> children;

        Node (int num) {
            this.num = num;
            this.children = new ArrayList<>();
        }
    }
}