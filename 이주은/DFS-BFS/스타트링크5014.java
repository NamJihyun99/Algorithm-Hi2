//https://www.acmicpc.net/problem/5014

import java.util.*;
import java.io.*;

class Main {
    static final int INF = 1_000_000_0;
    
    static int F, S, G, U, D;
    static int[] dp; //dp[i]는 i번째 층에 도착하기 위해 버튼을 누른 최소 횟수
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());    
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        dp = new int[F+1];
        for(int i=1; i<=F; i++) {
            dp[i] = INF;
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {S, 0}); //[층, 횟수];
        dp[S] = 0;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            if(now[0] == G)
                break;
            
            if(dp[now[0]] < now[1])
                continue;

            //위로
            int up = now[0] + U;
            if(up <= F && dp[up] > now[1] + 1) {
                dp[up] = now[1] + 1;
                queue.add(new int[] {up, now[1]+1});
            }

            //아래로
            int down = now[0] - D;
            if(down > 0 && dp[down] > now[1] + 1) {
                dp[down] = now[1] + 1;
                queue.add(new int[] {down, now[1]+1});
            }
        }

        if(dp[G] == INF)
            System.out.print("use the stairs");
        else
            System.out.print(dp[G]);
    }
}
