import java.util.*;
import java.io.*;

// 백준 1043번 - 거짓말

class Main {

    static int[] parent;

    private static int findParent(int child) {
        if (parent[child] == child) return child;
        return parent[child] = findParent(parent[child]);
    }

    private static void union(int childA, int childB) {
        int parentA = findParent(childA);
        int parentB = findParent(childB);
        if (parentA != parentB) {
            parent[parentB] = parentA;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        int K = Integer.parseInt(st.nextToken());
        if (K==0) {
            System.out.println(M);
            return;
        }
        parent = new int[N+1];
        for (int i=1; i<=N; i++) {
            parent[i] = i;
        }
        // p: 진실을 아는 사람 중 대표
        int p = Integer.parseInt(st.nextToken());
        for (int i=1; i<K; i++) {
            int e = Integer.parseInt(st.nextToken());
            parent[e] = p;
        }
        List<int[]> parties = new ArrayList<>();
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            int[] e = new int[num];
            // 특정 파티에 참석하는 사람들 반복문 돌면서 첫번째 사람(e[0])과 그래프 연결
            e[0] = Integer.parseInt(st.nextToken());
            for (int j=1; j<num; j++) {
                e[j] = Integer.parseInt(st.nextToken());
                union(e[0], e[j]);
            }
            parties.add(e);
        }
        int count = 0;
        // 진실을 아는 사람들을 연결하는 하나의 root
        int root = findParent(p);
        for (int[] party: parties) {
            for (int participant: party) {
                if (findParent(participant)==root) {
                    count++;
                    break;
                }
            }
        }
        // count: 진실을 말해야만 하는 파티의 수
        System.out.println(M-count);
    }
}
