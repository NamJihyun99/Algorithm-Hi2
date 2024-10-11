import java.util.*;
import java.io.*;

// 백준 16928번 - 뱀과 사다리 게임

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> jump = new HashMap<>();
        for (int i=0; i<N+M; i++) {
            st = new StringTokenizer(bf.readLine());
            jump.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        int answer = 101;
        queue.addLast(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            int idx = now[0];
            int count = now[1];
            if (idx==100) {
                answer = Math.min(answer, count);
                break;
            }
            if (visited[idx]) continue;
            visited[idx] = true;
            if (jump.containsKey(idx)) {
                idx = jump.get(idx);
            } 
            for (int i=1; i<=6; i++) {
                if (idx+i<101)
                    queue.addLast(new int[]{idx+i, count+1});
            }
        }
        System.out.println(answer);
    }
}
