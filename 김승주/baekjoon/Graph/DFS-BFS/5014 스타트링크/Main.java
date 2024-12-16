import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        boolean[] isVisited = new boolean[F + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {S, 0});
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0] == G) {
                System.out.println(curr[1]);
                return;
            }
            if (curr[0] + U <= F && !isVisited[curr[0] + U]) {
                isVisited[curr[0] + U] = true;
                q.offer(new int[] {curr[0] + U, curr[1] + 1});
            }
            if (curr[0] - D >= 1 && !isVisited[curr[0] - D]) {
                isVisited[curr[0] - D] = true;
                q.offer(new int[] {curr[0] - D, curr[1] + 1});
            }
        }
        System.out.println("use the stairs");
    }
}
