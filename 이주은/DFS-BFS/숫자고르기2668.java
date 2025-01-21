import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] num;
    static boolean[] visited;
    static List<Integer> answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        num = new int[N+1];
        visited = new boolean[N+1];
        answer = new ArrayList<>();
        
        for(int i=1; i<=N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=N; i++) {
            dfs(i, i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append('\n');

        for(int i=0; i<answer.size(); i++) {
            sb.append(answer.get(i)).append('\n');
        }

        System.out.print(sb);
    }

    private static void dfs(int start, int curr) {
        visited[curr] = true;

        //사이클
        if(num[curr] == start) {
            answer.add(start);
        }

        if(!visited[num[curr]]) {
            dfs(start, num[curr]);
        }

        visited[curr] = false;
    }
}
