import java.util.*;
import java.io.*;

// 백준 15683번 - 감시

class Main {

    static List<Cctv> cctvs;
    static int[][] room;
    static int N, M, K, answer;
    
    static final int[][][] dx = {{}, 
                                 {{-1}, {1}, {0}, {0}}, 
                                 {{0, 0}, {-1, 1}}, 
                                 {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}, 
                                 {{-1, 1, 0}, {-1, 1, 0}, {-1, 0, 0}, {1, 0, 0}}, 
                                 {{-1, 1, 0, 0}}
                                };
    static final int[][][] dy = {{}, 
                                 {{0}, {0}, {-1}, {1}}, 
                                 {{-1, 1}, {0, 0}}, 
                                 {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}, 
                                 {{0, 0, -1}, {0, 0, 1}, {0, -1, 1}, {0, -1, 1}}, 
                                 {{0, 0, -1, 1}}
                                };
    
    private static void dfs(int idx, int[][] marked) {
        if (idx==K) {
            answer = Math.min(answer, countBlindSpots(marked));
            return;
        }
        Cctv cctv = cctvs.get(idx);
        for (int i=0; i<dx[cctv.number].length; i++) {
            int[][] nextBoard = copiedBoard(marked);
            for (int j=0; j<dx[cctv.number][i].length; j++) {
                int nx = cctv.row; int ny = cctv.col;
                while (nx>=0 && nx<N && ny>=0 && ny<M && nextBoard[nx][ny]!=6) {
                    nextBoard[nx][ny] = -1;
                    nx += dx[cctv.number][i][j];
                    ny += dy[cctv.number][i][j];
                }
            }
            dfs(idx+1, nextBoard);
        }
    }

    private static int[][] copiedBoard(int[][] board) {
        int[][] copied = new int[N][M];
        for (int i=0; i<N; i++) {
            copied[i] = Arrays.copyOf(board[i], M);
        }
        return copied;
    }

    private static int countBlindSpots(int[][] marked) {
        int count = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (marked[i][j]==0) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        cctvs = new ArrayList<Cctv>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j]!=0 && room[i][j]!=6) {
                    cctvs.add(new Cctv(room[i][j], i, j));
                }
            }
        }
        K = cctvs.size();
        answer = 65;
        dfs(0, room);
        System.out.println(answer);
    }

    static class Cctv {
        int number;
        int row;
        int col;

        Cctv(int number, int row, int col) {
            this.number = number;
            this.row = row;
            this.col = col;
        }
    }
}
