import java.io.*;
import java.util.*;

class Main {
    static int V;
    static List<Node>[] tree;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        tree = new List[V + 1];

        for (int i = 1; i <= V; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < V; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j < input.length - 1; j += 2) {
                tree[Integer.parseInt(input[0])]
                .add(new Node(Integer.parseInt(input[j]), Integer.parseInt(input[j + 1])));
            }
        }
        dfs(dfs(1));
        System.out.println(max);
    }
    private static int dfs(int vNum) {
        Stack<Node> stack = new Stack<>();
        Stack<Integer> weightStack = new Stack<>();
        int[] distance = new int[V + 1];
        int maxV = 0;
        for (int i = 0; i < tree[vNum].size(); i++) {
            stack.push(tree[vNum].get(i));
            weightStack.push(0);
        }
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            int w = weightStack.pop();
            if (curr.value == vNum) continue;
            distance[curr.value] = w + curr.weight;
            if (distance[curr.value] > max) {
                max = distance[curr.value];
                maxV = curr.value;
            }
            for (int i = 0; i < tree[curr.value].size(); i++) {
                if (distance[tree[curr.value].get(i).value] != 0)   continue;
                stack.push(tree[curr.value].get(i));
                weightStack.push(w + curr.weight);
            }
        }
        return maxV;
    }
    static class Node {
        int value;
        int weight;
        Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }
}