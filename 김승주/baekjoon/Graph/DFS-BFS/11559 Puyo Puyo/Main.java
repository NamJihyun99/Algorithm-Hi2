// https://www.notion.so/making-a-scene/0000-39084d67aa1c45b3867a756155c37fd9?pvs=4

import java.util.*;
import java.io.*;

class Main {
    static char[][] field = new char[12][6];
    static List<int[]> adjacent;
    static boolean[][] isVisited;
    static int[] x = {-1, 0, 1, 0};
    static int[] y = {0, -1, 0, 1};
    static int chain = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            field[i] = br.readLine().toCharArray();
        }

        while (true) {
            boolean wasGone = false;
            adjacent = new ArrayList<>();
            isVisited = new boolean[12][6];
            for (int i = 11; i >= 0; i--) {
                boolean isBlank = true;
                for (int j = 0; j < 6; j++) {
                    if (field[i][j] != '.') {
                        isBlank = false; // 현재 row에 뿌요가 하나라도 존재하는 경우 isBlank를 false로 변경.
                        if (!isVisited[i][j]) {
                            if (dfs(i, j)) { // 새로운 연쇄가 추가된 경우
                                wasGone = true; 
                            }
                        }
                    }
                }
                if (isBlank) { // 현재 row에 뿌요가 하나도 존재하지 않았다면 존재하는 모든 뿌요를 확인한 것이므로 종료.
                    break;
                }
            }

            if (wasGone) { // 새로운 연쇄가 추가된 경우
                for (int[] idx : adjacent) {
                    field[idx[0]][idx[1]] = '.'; // 없어져야 할 뿌요를 .으로 변경
                }
                falling();
                chain++;
            } else {
                break;
            }
        }
        System.out.println(chain);
    }

    private static boolean dfs(int row, int col) {
        Stack<int[]> s = new Stack<>();
        s.push(new int[] {row, col});
        isVisited[row][col] = true;
        List<int[]> adjacentTemp = new ArrayList<>();

        while (!s.isEmpty()) {
            int[] curr = s.pop();
            adjacentTemp.add(curr);
            
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + x[i];
                int ny = curr[1] + y[i];
                if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !isVisited[nx][ny]) {
                    if (field[nx][ny] == field[row][col]) {
                        isVisited[nx][ny] = true;
                        s.push(new int[] {nx, ny});
                    }
                }
            }
        }
        if (adjacentTemp.size () >= 4) {
            adjacent.addAll(adjacentTemp);
            return true;
        }
        return false;
    }

    private static void falling() {
        // 모든 열에 대해, 각 행을 위에서부터 확인하면서 빈 칸을 찾고 그 위의 뿌요를 아래로 내려줌
        for (int col = 0; col < 6; col++) {
            int emptyRow = 11;  // 열의 가장 밑에서부터 채워야 할 위치
            for (int row = 11; row >= 0; row--) {
                if (field[row][col] != '.') {
                    // 빈칸이 아닌 뿌요를 만났을 때, 그 위치를 채우고 한 줄 위로 올라감
                    field[emptyRow][col] = field[row][col];
                    if (emptyRow != row) {
                        field[row][col] = '.';
                    }
                    emptyRow--;
                }
            }
        }
    }
}