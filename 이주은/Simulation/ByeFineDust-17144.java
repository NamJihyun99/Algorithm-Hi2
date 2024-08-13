//백준 17144번 미세먼지 안녕!

import java.util.*;
import java.io.*;

class Main {
    static int R, C, T;
    static int air1 = -1, air2=-1;
    static int[][] room;
    static int[][] delta = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                
                if(room[i][j] != -1) {
                    continue;
                }

                if(air1 == -1)
                    air1 = i;
                else
                    air2 = i;
            }
        }

        for(int i=0; i<T; i++) {
            spread();
            operate();
        }

        int answer = 0;
        for(int i=0; i<R; i++) 
            for(int j=0; j<C; j++)
                if(room[i][j] != 0 && room[i][j] != -1)
                    answer += room[i][j];    

        System.out.println(answer);
    }

    private static void spread() {
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                int out = room[i][j]/5;

                for(int[] d: delta) {
                    int nextX = i + d[0];
                    int nextY = j + d[1];
    
                    if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C || room[nextX][nextY] == -1)
                        continue;
    
                    room[i][j] -= out;
                    queue.add(new int[] {nextX, nextY, out});
                }
            }   
        }

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();

            room[curr[0]][curr[1]] += curr[2];
        }
    }

    private static void operate() {
        int temp;
        
        int prev = 0;
        
        for(int i=1; i<C; i++) {
            temp = room[air1][i];
            room[air1][i] = prev;
            prev = temp;
        }
        for(int i=air1-1; i>=0; i--) {
            temp = room[i][C-1];
            room[i][C-1] = prev;
            prev = temp;
        }
        for(int i=C-2; i>=0; i--) {
            temp = room[0][i];
            room[0][i] = prev;
            prev = temp;
        }
        for(int i=1; i<air1; i++) {
            temp = room[i][0];
            room[i][0] = prev;
            prev = temp;
        }

        prev = 0;
        for(int i=1; i<C; i++) {
            temp = room[air2][i];
            room[air2][i] = prev;
            prev = temp;
        }
        for(int i=air2+1; i<R; i++) {
            temp = room[i][C-1];
            room[i][C-1] = prev;
            prev = temp;
        }
        for(int i=C-2; i>=0; i--) {
            temp = room[R-1][i];
            room[R-1][i] = prev;
            prev = temp;
        }
        for(int i=R-2; i>air2; i--) {
            temp = room[i][0];
            room[i][0] = prev;
            prev = temp;
        }
    }
}
