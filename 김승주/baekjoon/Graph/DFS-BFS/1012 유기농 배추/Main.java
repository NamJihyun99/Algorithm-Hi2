import java.util.*;
import java.io.*;

class Main {
    static int M;
    static int N;
    static int[][] land;
    static boolean[][] visited;
    static int[] x = {-1, 0, 1, 0};
    static int[] y = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            M = Integer.parseInt(input[0]);
            N = Integer.parseInt(input[1]);
            int K = Integer.parseInt(input[2]);
            land = new int[N][M];
            visited = new boolean[N][M];
            int count = 0;
            
            for (int j = 0; j < K; j++) {
                input = br.readLine().split(" ");
                land[Integer.parseInt(input[1])][Integer.parseInt(input[0])] = 1;
            }
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < M; b++) {
                    if (visited[a][b] || land[a][b] == 0)  continue;
                    dfs(a, b);
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int a, int b) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {a, b});
        visited[a][b] = true;
        while (!stack.isEmpty()) {
            int[] idx = stack.pop();
            for (int i = 0; i < 4; i++) {
                if (idx[0] + x[i] >= 0 && idx[0] + x[i] < N && idx[1] + y[i] >= 0 && idx[1] + y[i] < M) {
                    if (land[idx[0] + x[i]][idx[1] + y[i]] == 1 && !visited[idx[0] + x[i]][idx[1] + y[i]]) {
                        stack.push(new int[] {idx[0] + x[i], idx[1] + y[i]});
                        visited[idx[0] + x[i]][idx[1] + y[i]] = true;
                    }
                }
            }
        }
    }
}