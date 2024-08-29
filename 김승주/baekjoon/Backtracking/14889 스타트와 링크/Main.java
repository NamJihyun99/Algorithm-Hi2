import java.io.*;
import java.util.*;

class Main {
    static int answer = Integer.MAX_VALUE;
    static int N;
    static int[][] S;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            S[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        backtracking(0, 0);
        System.out.println(answer);
    }

    private static void backtracking(int depth, int start) {
        if (depth == N / 2) {
            int startSum = 0;
            int linkSum = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (i == j) continue;

                    if (visited[i] && visited[j]) {
                        startSum += S[i][j] + S[j][i];
                    } else if (!visited[i] && !visited[j]) {
                        linkSum += (S[i][j] + S[j][i]);
                    }
                }
            }
            answer = Math.min(answer, Math.abs(startSum - linkSum));
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}