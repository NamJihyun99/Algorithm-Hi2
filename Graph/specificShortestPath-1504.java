//백준 1504번 특정한 최단경로 (https://www.acmicpc.net/problem/1504)

import java.util.*;
import java.io.*;

class Main {
    static int N, E;
    static int v1, v2;
    static List<List<int[]>> edges = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        getInput();

        //1 -> v1 -> v2 -> N : v1 -> v2 + v1 -> 1 + v2 -> N
        //1 -> v2 -> v1 -> N : v1 -> v2 + v2 -> 1 + v1 -> N
        int[] dist1 = new int[N+1];
        int[] dist2 = new int[N+1];

        dijkstra(v1, dist1);

        if(dist1[v2] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        
        dijkstra(v2, dist2);

        int route1 = dist1[1] + dist1[v2] + dist2[N];
        int route2 = dist2[1] + dist1[v2] + dist1[N];

        if((dist1[1] == Integer.MAX_VALUE || dist2[N] == Integer.MAX_VALUE) && (dist2[1] == Integer.MAX_VALUE || dist1[N] == Integer.MAX_VALUE))
            System.out.println("-1");
        else if(dist1[1] != Integer.MAX_VALUE || dist2[N] != Integer.MAX_VALUE || dist2[1] != Integer.MAX_VALUE || dist1[N] != Integer.MAX_VALUE)
            System.out.println(Math.min(route1, route2));
        else if(dist1[1] == Integer.MAX_VALUE || dist2[N] == Integer.MAX_VALUE)
            System.out.println(route2);
        else if(dist2[1] == Integer.MAX_VALUE || dist1[N] == Integer.MAX_VALUE)
            System.out.println(route1);
        
    }

    public static void getInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++) {
            edges.add(new ArrayList<>());
        }
        
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.get(a).add(new int[] {b, c});
            edges.get(b).add(new int[] {a, c});
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }

    public static void dijkstra (int s, int[] dist) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]);
        
        for(int i=1; i<=N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        pq.add(new int[] {s, 0});

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();

            if(dist[curr[0]] < curr[1])
                continue;
            
            for(int[] edge : edges.get(curr[0])) {
                int cost = curr[1] + edge[1];

