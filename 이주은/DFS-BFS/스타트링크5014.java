//https://www.acmicpc.net/problem/5014

import java.util.*;
import java.io.*;

class Main {    
    static int F, S, G, U, D;
    static int[] visited; //visted[i]는 i번째 층에 도착하기 위해 버튼을 누른 최소 횟수
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());    
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        if(S == G) {
            System.out.println(0);
            return;
        }
        
        visited = new int[F+1];
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {S, 0}); //[층, 횟수];

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            if(now[0] == G)
                break;

            //위로
            int up = now[0] + U;
            if(up <= F && visited[up] == 0) {
                visited[up] = now[1] + 1;
                queue.add(new int[] {up, visited[up]});
            }

            //아래로
            int down = now[0] - D;
            if(down > 0 && visited[down] == 0) {
                visited[down] = now[1] + 1;
                queue.add(new int[] {down, visited[down]});
            }
        }

        if(visited[G] == 0)
            System.out.print("use the stairs");
        else
            System.out.print(visited[G]);
    }
}
