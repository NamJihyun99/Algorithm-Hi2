import java.util.*;

// 프로그래머스 - 양과 늑대

class Solution {
    
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int[] values;
    int max = 0;
    
    public int solution(int[] info, int[][] edges) {
        values = info;
        for (int i=0; i<info.length; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        List<Integer> tmp = new ArrayList<>();
        tmp.add(0);
        dfs(0, 0, 0, tmp);
        return max;
    }
    
    private void dfs(int num, int wolf, int sheep, List<Integer> candidates) {
        if (values[num] == 0) { 
            sheep++; 
        } else { 
            wolf++; 
        }
        max = Math.max(max, sheep);
        if (sheep <= wolf) { return; }
        List<Integer> tmp = new ArrayList<>(candidates);
        if (! graph.get(num).isEmpty()) {
            tmp.addAll(graph.get(num));
        }
        tmp.remove(Integer.valueOf(num));
        for (int next: tmp) {
            dfs(next, wolf, sheep, tmp);
        }
    }
}
