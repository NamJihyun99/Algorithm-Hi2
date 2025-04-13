import java.util.*;
import java.io.*;

// 백준 2638번 치즈
// 1: 치즈, 2: 외부 여백, 3: 외부 임시 마킹, 0:내부 여백
class Main {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static char[][] board;
    static int N, M, total;

    private static void removeCheese() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] != '1') continue;
                int count = 0;
                for (int d=0; d<4; d++) {
                    int nx = i+dx[d];
                    int ny = j+dy[d];
                    if (nx<0 || nx>=N || ny<0 || ny>=M) {
                        count++;
                    } else if (board[nx][ny]=='2') {
                        count++;
                    }
                }
                if (count >= 2) {
                    board[i][j] = '3';
                }
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == '3') {
                    board[i][j] = '2';
                    total++;
                }
            }
        }
    }

    private static void initOutside() {
        for (int x=0; x<N; x++) {
            if (board[x][0] == '0') {
                markOutside(x, 0);
                return;
            }
            if (board[x][M-1] == '0') {
                markOutside(x, M-1);
                return;
            }
        }
        for (int y=1; y<M-1; y++) {
            if (board[0][y] == '0') {
                markOutside(0, y);
                return;
            }
            if (board[N-1][y] == '0') {
                markOutside(N-1, y);
                return;
            }
        }
    }

    private static void markOutside(int x, int y) {
        board[x][y] = '2';
        for (int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx>=0 && nx<N && ny>=0 && ny<M && board[nx][ny]=='0') {
                markOutside(nx, ny);
            }
        }
    }

    private static void findHole() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] != '0') continue;
                for (int d=0; d<4; d++) {
                    int nx = i+dx[d];
                    int ny = j+dy[d];
                    if (nx>=0 && nx<N && ny>=0 && ny<M && board[nx][ny]=='2') {
                        markHole(i, j);
                        break;
                    }
                }
            }
        }
    }

    private static void markHole(int x, int y) {
        board[x][y] = '2';
        for (int d=0; d<4; d++) {
            int nx = x+dx[d];
            int ny = y+dy[d];
            if (nx>=0 && nx<N && ny>=0 && ny<M && board[nx][ny]=='0') {
                markHole(nx, ny);
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        int numOfCheese = 0;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                board[i][j] = st.nextToken().charAt(0);
                if (board[i][j] == '1') numOfCheese++;
            }
        }
        int answer = 0;
        initOutside();
        total = 0;
        while (total < numOfCheese) {
            removeCheese();
            findHole();
            answer++;
        }
        System.out.println(answer);
    }
}
