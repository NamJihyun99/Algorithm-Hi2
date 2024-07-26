// /15900-2f3bb8aa298643baa592c7ec1cfdaaa4?pvs=4

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        List<Integer>[] tree = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            String[] input = br.readLine().split(" ");
            tree[Integer.parseInt(input[0])].add(Integer.parseInt(input[1]));
            tree[Integer.parseInt(input[1])].add(Integer.parseInt(input[0]));
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int[] depth = new int[N + 1];
        q.add(1);
        visited[1] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next : tree[curr]) {
                if (!visited[next]) {
                    visited[next] = true;
                    depth[next] = depth[curr] + 1;
                    q.add(next);
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (tree[i].size() == 1) {
                sum += depth[i];
            }
        }
        if (sum % 2 == 1) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}