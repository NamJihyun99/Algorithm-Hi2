import java.util.*;
import java.io.*;

// 백준 13460번 구슬 탈출 2
class Main {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static class State {
        int redX, redY, blueX, blueY, level;

        State( int redX, int redY, int blueX, int blueY, int level) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.level = level;
        }

        @Override
        public boolean equals(Object o) {
            State state = (State) o;
            return this.redX==state.redX 
                && this.redY==state.redY 
                && this.blueX==state.blueX 
                && this.blueY==state.blueY; 
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.redX, this.redY, this.blueX, this.blueY);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        char[][] board = new char[N][M];
        Set<State> visited = new HashSet<>();
        int rx=0, ry=0, bx=0, by=0;
        for (int i=0; i<N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                if (board[i][j]=='R') {
                    rx = i;
                    ry = j;
                    board[i][j] = '.';
                }
                if (board[i][j]=='B') {
                    bx = i;
                    by = j;
                    board[i][j] = '.';
                }
            }
        }
        queue.offer(new State(rx, ry, bx, by, 0));
        
        while (!queue.isEmpty()) {
            State state = queue.poll();

            if (visited.contains(state)) continue;
            visited.add(state);

            // 상하좌우로 보드를 기울인다. 
            for (int i=0; i<4; i++) {
                rx = state.redX;
                ry = state.redY;
                bx = state.blueX;
                by = state.blueY;

                int redDist = 0;
                while(board[rx][ry] == '.') {
                    rx += dx[i];
                    ry += dy[i];
                    redDist++;
                }
                if (board[rx][ry] == '#') {
                    rx -= dx[i]; 
                    ry -= dy[i];
                    redDist--;
                }

                int blueDist = 0;
                while(board[bx][by] == '.') {
                    bx += dx[i];
                    by += dy[i];
                    blueDist++;
                }
                if (board[bx][by] == '#') {
                    bx -= dx[i]; 
                    by -= dy[i];
                    blueDist--;
                }

                // 두 구슬이 만나면 먼저 도착한 구슬을 해당 위치에 두고 늦게 온 구슬을 1 되돌린다.
                if (rx == bx && ry == by && board[rx][ry] != 'O') {
                    if (redDist > blueDist) { // 파란색이 먼저 온 경우
                        rx -= dx[i];
                        ry -= dy[i];
                    } else {      // 빨간색이 먼저 온 경우
                        bx -= dx[i];
                        by -= dy[i];
                    }
                }
                
                // 파란 구슬이 구멍에 빠지는 경우에는 가지치기를 멈춘다. (동시에 빠져도 마찬가지이므로 먼저 체크한다)
                if (board[bx][by] == 'O') {
                    continue;
                }
                
                // 빨간 구슬이 구멍에 빠지면 답 print하고 return
                if (board[rx][ry] == 'O') {
                    System.out.println(state.level+1);
                    return;
                }
                
                // 10번을 넘어도 return
                if (state.level+1 > 10) {
                    System.out.println(-1);
                    return;
                }

                queue.offer(new State(rx, ry, bx, by, state.level+1));
            }
        }
        // 더 움직일 수 없는 경우
        System.out.println(-1);
    }
}
