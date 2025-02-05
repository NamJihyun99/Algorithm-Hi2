import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> adjR = new ArrayList<>();

        for(int i=0; i<=N; i++) {
            adj.add(new ArrayList<>());
            adjR.add(new ArrayList<>());
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj.get(from).add(to);
            adjR.get(to).add(from);
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        //어떤 노드가 S로부터 도달 가능하고 T로 도달 가능하면 S->T의 경로가 성립한다.
        boolean[] fromS = new boolean[N+1];
        fromS[T] = true; //목적지 정점 방문 시 더이상 진행 X
        dfs(S, adj, fromS);

        boolean[] toT = new boolean[N+1];
        dfs(T, adjR, toT);


        boolean[] fromT = new boolean[N+1];
        fromT[S] = true;
        dfs(T, adj, fromT);
        
        boolean[] toS = new boolean[N+1];
        dfs(S, adjR, toS);

        int answer = 0;
        for(int i=1; i<=N; i++) {
            if(fromS[i] && toS[i] && fromT[i] && toT[i])
                answer ++;
        }

        System.out.println(answer-2);
    }

    private static void dfs(int now, List<List<Integer>> adj, boolean[] visited) {
        visited[now] = true;

        for(int next : adj.get(now)) {
            if(!visited[next])
                dfs(next, adj, visited);
        }
    }
}
