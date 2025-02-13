import java.io.*;
import java.util.*;

class Main {
    static int result = 0;
    static List<int[]> selected = new ArrayList<>();
    static boolean[][] isS = new boolean[5][5];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sCount = 0;
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                if (input.charAt(j) == 'S') {
                    sCount++;
                    isS[i][j] = true;
                    continue;
                }
                isS[i][j] = false;
            }
        }
        if (sCount < 4) {
            System.out.println(0);
            return;
        }
        makeCombination(0, 0, 0, 0);
        
        System.out.println(result);
    }

    private static void makeCombination(int row, int col, int total, int yCnt) {
        if (yCnt > 3) {
            return;
        }

        if (total == 7) {
            bfs();
            return;
        }

        for (int i = row; i < 5; i++) {
            for (int j = (i == row)? col : 0; j < 5; j++) {                
                selected.add(new int[] {i, j});
                if (isS[i][j]) {
                    makeCombination((j == 4)? i + 1 : i, (j == 4)? 0 : j + 1, total + 1, yCnt);
                } else {
                    makeCombination((j == 4)? i + 1 : i, (j == 4)? 0 : j + 1, total + 1, yCnt + 1);
                }
                selected.remove(selected.size() - 1);
            }
        }
    }

    private static void bfs() {
        boolean[] isValid = new boolean[7];
        int count = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(selected.get(0));
        isValid[0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = curr[0] + dx[i];
                int nextCol = curr[1] + dy[i];
                if (nextRow >= 0 && nextRow < 5 && nextCol >= 0 && nextCol < 5) {
                    for (int k = 0; k < 7; k++) {
                        if (selected.get(k)[0] == nextRow && selected.get(k)[1] == nextCol && !isValid[k]) {
                            count++;
                            isValid[k] = true;
                            q.offer(selected.get(k));
                        }
                    }
                }
            }
        }
        if (count == 7) {
            result++;
        }
    }
}