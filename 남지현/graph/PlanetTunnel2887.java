import java.util.*;
import java.io.*;

// 백준 2887번 행성 터널
class Main {

    private static int[] parent;
    private static Planet[] x, y, z;
    private static PriorityQueue<Distance> pq;
    private static int N;

    private static int solve() {
        int answer = 0;
        pq = new PriorityQueue<Distance>();
        parent = new int[N];
        for (int i=0; i<N; i++) {
            parent[i] = i;
        }
        saveDistances();
        while (!pq.isEmpty()) {
            Distance dist = pq.poll();
            if (union(dist.s, dist.e)) {
                answer += dist.cost;
            }
        }
        return answer;
    }

    private static void saveDistances() {
        Arrays.sort(x);
        Arrays.sort(y);
        Arrays.sort(z);

        for (int i=0; i<N-1; i++) {
            pq.add(new Distance(x[i].idx, x[i+1].idx, x[i+1].pt-x[i].pt));
            pq.add(new Distance(y[i].idx, y[i+1].idx, y[i+1].pt-y[i].pt));
            pq.add(new Distance(z[i].idx, z[i+1].idx, z[i+1].pt-z[i].pt));
        }
    }

    private static int findParent(int id) {
        if (parent[id] == id) {
            return parent[id];
        }
        return parent[id] = findParent(parent[id]);
    }

    private static boolean union(int planet1, int planet2) {
        int parent1 = findParent(planet1);
        int parent2 = findParent(planet2);
        if (parent1 == parent2) {
            return false;
        }
        parent[parent2] = parent1;
        return true;
    }

    private static class Planet implements Comparable<Planet> {
        int idx, pt;

        Planet(int idx, int pt) {
            this.idx = idx;
            this.pt = pt;
        }

        @Override
        public int compareTo(Planet p) {
            return this.pt - p.pt;
        }
    }

    private static class Distance implements Comparable<Distance> {
        int s, e, cost;

        Distance(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Distance d) {
            return this.cost - d.cost;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        x = new Planet[N];
        y = new Planet[N];
        z = new Planet[N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = new Planet(i, Integer.parseInt(st.nextToken()));
            y[i] = new Planet(i, Integer.parseInt(st.nextToken()));
            z[i] = new Planet(i, Integer.parseInt(st.nextToken()));
        }
        System.out.println(solve());
    }
}
