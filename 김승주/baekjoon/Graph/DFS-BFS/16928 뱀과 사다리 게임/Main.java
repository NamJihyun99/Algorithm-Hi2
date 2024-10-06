import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int[] board = new int[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + M; i++) {
            String[] input = br.readLine().split(" ");
            board[Integer.parseInt(input[0])] = Integer.parseInt(input[1]);
        }

        bfs();
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[100];
        queue.add(new int[] {1, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            if (curr[0] + 6 >= 100) {
                System.out.println(curr[1] + 1);
                return;
            }

            for (int dice = 1; dice <= 6; dice++) {
                int location = curr[0] + dice;
                while (board[location] != 0) {
                    location = board[location];
                }
                if (!isVisited[location]) {
                    isVisited[location] = true;
                    queue.add(new int[] {location, curr[1] + 1});
                }
            }
        }
    }
}