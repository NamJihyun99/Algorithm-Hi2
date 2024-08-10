import java.util.*;
import java.io.*;

// 백준 20040번 - 사이클 게임

class Main {

    static int[] parent;

    static int findParent(int n) {
        if (parent[n] == n) return n;
        return parent[n] = findParent(parent[n]);
    }

    static boolean union(int a, int b) {
        int aParent = findParent(a);
        int bParent = findParent(b);
        if (aParent != bParent) 
            parent[bParent] = aParent;
        return aParent != bParent;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n];
        int count = 0; 
        int answer = 0;
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!union(a, b)) {
                answer = i+1;
                break;
            }
        }
        System.out.println(answer);
    }
}
