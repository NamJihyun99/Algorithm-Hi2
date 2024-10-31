import java.util.*;
import java.io.*;

// 백준 1446번 - 지름길

class Main {

    static int N, D;
    static Map<Integer, List<Route>> routes;
    static int[] dist;

    static void dp() {
        dist = new int[D+1];
        for (int i=0; i<=D; i++) {
            dist[i] = i;
        }
        for (int i=1; i<=D; i++) {
            dist[i] = Math.min(dist[i], dist[i-1]+1);
            if (routes.containsKey(i)) {
                for (Route route: routes.get(i)) {
                    dist[i] = Math.min(dist[i], dist[route.node]+route.len);
                }
            }
        }
    }

    static class Route {
        int node;
        int len;

        Route(int node, int len) {
            this.node = node;
            this.len = len;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        routes = new HashMap<>();
        for (int i=0; i<N; i++) {
            st =  new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (!routes.containsKey(end)) {
                routes.put(end, new ArrayList<>());
            }
            routes.get(end).add(new Route(start, cost));
        }
        dp();
        System.out.println(dist[D]);
    }
}
