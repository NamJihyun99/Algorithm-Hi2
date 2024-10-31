import java.util.*;

class Solution {
    static List<List<Integer>> graph;
    static int[] parent;
    public int solution(int n, int[][] wires) {
        int answer = 1000;
        int len = wires.length;
        
        for(int i = 0; i < len; i++) {
            graph = new ArrayList<>();
            makeGraph(i, wires, len, n);
            unionFind(n);
            answer = Math.min(answer, getDiff(n));
        }
        
        return answer;
    }
    
    static void makeGraph(int idx, int[][] wires, int len, int n) {
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < len; i++) {
            if(i == idx) continue;
            graph.get(wires[i][0]).add(wires[i][1]);
            graph.get(wires[i][1]).add(wires[i][0]); // 양방향
        }
    }
    
    static void unionFind(int n) {
        parent = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        for(int i = 1; i <= n; i++) {
            for(int v : graph.get(i)) {
                if(getParent(i) != getParent(v)) {
                    makeParent(i, v);
                }
            }
        }
    }
    
    static int getParent(int a) {
        if(a == parent[a]) return parent[a];
        return getParent(parent[a]);
    }
    
    static void makeParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if(a != b) parent[b] = a;
    }
    
    static int getDiff(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            int v = map.getOrDefault(getParent(i), 0);
            map.put(getParent(parent[i]), v += 1);
        }
        List<Integer> res = new ArrayList<>();
        for(int k : map.keySet()) {
            res.add(map.get(k));
        }
        return Math.abs(res.get(0) - res.get(1));
    }
}
