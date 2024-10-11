import java.util.*;
import java.io.*;

// 백준 15900 - 나무 탈출 (실버1) 스택을 사용하는 것이 더 빠름.

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        List<Integer>[] graph = new List[N+1];
        int[] inDegree = new int[N+1];
        boolean[] visited = new boolean[N+1];
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        for (int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        int count = 0;
        stack.addLast(new int[]{1, 1});
        while (!stack.isEmpty()) {
            int[] node = stack.pollLast();
            visited[node[0]] = true;
            boolean isLeaf = true;
            for (int next: graph[node[0]]) {
                if (! visited[next]) {
                    isLeaf  = false;
                    stack.addLast(new int[]{next, node[1]+1});
                }
            }
            if (isLeaf) count += node[1]-1;
        }
        System.out.println(count%2==0? "No": "Yes");
    }
}
