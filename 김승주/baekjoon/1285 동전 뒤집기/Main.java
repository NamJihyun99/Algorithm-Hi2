import java.io.*;
import java.util.*;

class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int init = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append(st.nextToken());
        }
        for (int i = 0; i < N * N; i++) {
            if (sb.charAt(i) == 'T') {
                init |= (1 << i);
            }
        }

        System.out.println(bfs(init));
    }

    private static int bfs(int init) {
        List<Integer> visited = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        int result = Integer.MAX_VALUE;
        q.offer(init);
        visited.add(init);

        while (!q.isEmpty()) {
            int curr = q.poll();
            result = Math.min(result, Integer.bitCount(curr));
            for (int i = 0; i < N; i++) {
                int changedR = changeRow(curr, i);
                if (!visited.contains(changedR)) {
                    visited.add(changedR);
                    q.offer(changedR);
                }
                int changedC = changeCol(curr, i);
                if (!visited.contains(changedC)) {
                    visited.add(changedC);
                    q.offer(changedC);
                }
            }
        }

        return result;
    }

    private static int changeRow(int bit, int num) {
        for (int i = N * num; i < N * (num + 1); i++) {
            bit = bit ^ (1 << i);
        }
        return bit;
    }

    private static int changeCol(int bit, int num) {
        for (int i = num; i < N * N; i += N) {
            bit = bit ^ (1 << i);
        }
        return bit;
    }
}