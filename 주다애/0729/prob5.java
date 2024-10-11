import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Puyo Puyo(골드 4)
/*
* 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
* --> 이해가 안가네..
*/
/*
* 1. 먼저 탐색하고 --> 터트릴 수 있는 뿌요 다 터트림
* 2. 연쇄 횟수 증가시키고
* 3. 뿌요 밑으로 내림
*/
public class Main {
    static char[][] puyo;
    static boolean[][] visited;
    static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        puyo = new char[12][6];
        visited = new boolean[12][6];
        // 입력
        for (int i = 0; i < 12; i++) {
            String line = sc.next();
            for (int j = 0; j < 6; j++) {
                puyo[i][j] = line.charAt(j);
            }
        }
        int answer = 0;
        boolean isPop = false;
        while(true) {
            isPop = false;
            visited = new boolean[12][6];

            // 탐색
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (puyo[i][j] != '.') {
                        List<int[]> temp = new ArrayList<>();
                        dfs(i, j, puyo[i][j], temp);
                        if(temp.size() >= 4) {
                            // 빈칸으로 만듦
                            for (int[] coor : temp) {
                                puyo[coor[0]][coor[1]] = '.';
                            }
                            isPop = true;
                        }
                    }
                }
            }
            if(!isPop) break;
            answer += 1;
            dropPuyos();
        }
        System.out.println(answer);
    }

    private static void dropPuyos() {
        for (int c = 0; c < 6; c++) {
            int k = 11;
            for (int r = 11; r >= 0; r--) {
                if (puyo[r][c] != '.') {
                    puyo[k--][c] = puyo[r][c];
                }
            }
            for (int r = k; r >= 0; r--) {
                puyo[r][c] = '.';
            }
        }
    }

    private static List<int[]> dfs(int r, int c, char t, List<int[]> list) {
        visited[r][c] = true;
        list.add(new int[]{r, c});
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];
            if(!canMove(nr, nc)) continue;
            if(puyo[nr][nc] == t && !visited[nr][nc]) {
                dfs(nr, nc, t, list);
            }
        }
        return list;
    }

    private static boolean canMove(int r, int c) {
        if(r < 0 || r >= 12 || c < 0 || c >= 6) return false;
        return true;
    }
}
