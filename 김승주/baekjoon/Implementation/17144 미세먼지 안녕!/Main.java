import java.io.*;
import java.util.*;

class Main {
    static int R;
    static int C;
    static int[][] A;
    static int[][] tempA;
    static int airPurifier = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        A = new int[R][C];
        
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
                if (A[r][c] == -1 && airPurifier == -1) {
                    airPurifier = r;
                }
            }
        }

        for (int sec = 0; sec < T; sec++) {
            diffuseFineDust();
            operateAirPurifier();
        }

        int answer = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] > 0) {
                    answer += A[r][c];
                }
            }
        }
        System.out.println(answer);
    }

    private static void diffuseFineDust() {
        boolean[][] isVisited = new boolean[R][C];
        tempA = new int[R][C];
        // 확산되기 이전 상태를 tempA에 복사
        for (int i = 0; i < R; i++) {
            tempA[i] = Arrays.copyOf(A[i], A[i].length);
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (tempA[r][c] > 0) { // 확산 가능한 미세먼지가 있는 칸인 경우
                    List<int[]> spreadingLocation = getSpreadingLocations(new int[] {r, c}); // 미세먼지가 확산될 수 있는 칸의 좌표 구하기
                    int spreadingAmount = tempA[r][c] / 5; // 현재 칸에서부터 확산될 미세먼지의 양

                    // 현재 칸의 미세먼지를 확산한 후 현재 칸에 남을 미세먼지 양 갱신
                    if (isVisited[r][c]) {
                        A[r][c] += (tempA[r][c] - spreadingAmount * spreadingLocation.size());
                    } else {
                        isVisited[r][c] = true;
                        A[r][c] = (tempA[r][c] - spreadingAmount * spreadingLocation.size());
                    }

                    // 인접한 칸에 미세먼지 확산
                    for (int[] neighbor : spreadingLocation) {
                        if (isVisited[neighbor[0]][neighbor[1]]) {
                            A[neighbor[0]][neighbor[1]] += spreadingAmount;
                        } else {
                            isVisited[neighbor[0]][neighbor[1]] = true;
                            A[neighbor[0]][neighbor[1]] = spreadingAmount;
                        }
                    }
                }
            }
        }

    }

    private static List<int[]> getSpreadingLocations(int[] curr) {
        List<int[]> spreadingLocation = new ArrayList<>();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int x = curr[0] + dx[i];
            int y = curr[1] + dy[i];
            if (x >= 0 && x < R && y >= 0 && y < C && A[x][y] != -1) {
                spreadingLocation.add(new int[] {x, y});
            }
        }
        return spreadingLocation;
    }

    private static void operateAirPurifier() {
        int prev1 = A[airPurifier][1];
        int prev2 = A[airPurifier + 1][1];
        A[airPurifier][1] = 0;
        A[airPurifier + 1][1] = 0;

        for (int i = 2; i < C - 1; i++) {
            int temp = A[airPurifier][i];
            A[airPurifier][i] = prev1;
            prev1 = temp;
            temp = A[airPurifier + 1][i];
            A[airPurifier + 1][i] = prev2;
            prev2 = temp;
        }

        for (int i = airPurifier; i >= 0; i--) {
            int temp = A[i][C - 1];
            A[i][C - 1] = prev1;
            prev1 = temp;
        }

        for (int i = airPurifier + 1; i < R; i++) {
            int temp = A[i][C - 1];
            A[i][C - 1] = prev2;
            prev2 = temp;
        }

        for (int i = C - 2; i >= 0; i--) {
            int temp = A[0][i];
            A[0][i] = prev1;
            prev1 = temp;
            temp = A[R - 1][i];
            A[R - 1][i] = prev2;
            prev2 = temp;
        }

        for (int i = 1; i < airPurifier; i++) {
            int temp = A[i][0];
            A[i][0] = prev1;
            prev1 = temp;
        }
        for (int i = R - 2; i > airPurifier + 1; i--) {
            int temp = A[i][0];
            A[i][0] = prev2;
            prev2 = temp;
        }
    }
}