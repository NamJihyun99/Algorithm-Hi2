import java.util.*;
import java.io.*;

// 백준 15889번 - 호 안에 수류탄이야!!
class Main {

    static final String enable = "권병장님, 중대장님이 찾으십니다";
    static final String unable = "엄마 나 전역 늦어질 것 같아";

    private static void sweepSolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] point = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }
        if (N==1) {
            System.out.println(enable);
            return;
        }
        st = new StringTokenizer(br.readLine());
        boolean result = false;
        int end = 0;
        for (int i=0; i<N-1; i++) {
            int dist = Integer.parseInt(st.nextToken());
            if (point[i] > end) {
                break;
            } 
            end = Math.max(end, point[i]+dist);
            if (end >= point[N-1]) {
                result = true;
                break;
            }
        }
        System.out.println(result? enable : unable);
    }

    private static void pqSolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] point = new int[N];
        int[] dist = new int[N-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }
        if (N==1) {
            System.out.println(enable);
            return;
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N-1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }
        boolean[] visited = new boolean[N];
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(0, point[0]));
        while (!pq.isEmpty()) {
            State now = pq.poll();
            if (now.idx == N-1) {
                break;
            }
            int max = now.pt + dist[now.idx];
            for (int i=now.idx; i<N; i++) {
                if (point[i] <= max) {
                    if (!visited[i]) {
                        pq.add(new State(i, point[i]));
                        visited[i] = true;
                    }
                } else {
                    break;
                }
            }
        }
        System.out.println(visited[N-1]? enable : unable);
    }


    private static class State implements Comparable<State> {
        int idx;
        int pt;

        State(int idx, int pt) {
            this.idx = idx;
            this.pt = pt;
        }

        public int compareTo(State state) {
            return state.pt - this.pt;
        }
    }
    
    public static void main(String[] args) throws Exception {
        sweepSolution();
    }
}
