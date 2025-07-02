import java.util.*;
import java.io.*;

// 백준 14442번 벽 부수고 이동하기 2
class Main {

    static final int[] dx = new int[]{-1, 1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};

    static class Node {
        int x, y, count, level;

        Node(int x, int y, int count, int level) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.level = level;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[] map = new String[N];
        boolean[][][] visited = new boolean[N][M][K+1];
        Queue<Node> queue = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            map[i] = br.readLine();
        }
        visited[0][0][0] = true;
        queue.add(new Node(0, 0, 0, 1));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x==N-1 && node.y==M-1) {
                System.out.println(node.level);
                return;
            }
            for (int i=0; i<4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx<0 || nx>=N || ny<0 || ny>=M) continue;
                if (map[nx].charAt(ny)=='1') {
                    if (node.count+1 > K || visited[nx][ny][node.count+1]) continue;
                    visited[nx][ny][node.count+1] = true;
                    queue.add(new Node(nx, ny, node.count+1, node.level+1));
                } else {
                    if (visited[nx][ny][node.count]) continue;
                    visited[nx][ny][node.count] = true;
                    queue.add(new Node(nx, ny, node.count, node.level+1));
                }
            }
        }
        System.out.println(-1);
    }
}
