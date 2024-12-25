import java.util.*;

// 프로그래머스 도넛과 막대 그래프

class Solution {
    Map<Integer, int[]> counts;
    int[][] edgeInfo;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        edgeInfo = edges;
        counts = new HashMap<>();
        initCounts();
        int center=0, stick=0, eight=0;
        for (Map.Entry<Integer, int[]> entry: counts.entrySet()) {
            int in = entry.getValue()[0];
            int out = entry.getValue()[1];
            if (in==0 && out>=2) {
                center = entry.getKey();
            } else if (in>0 && out==0) {
                stick++;
            } else if (in>=2 && out>=2) {
                eight++;
            }
        }
        return new int[]{center, counts.get(center)[1]-stick-eight, stick, eight};
    }
    
    private void initCounts() {
        for (int[] edge: edgeInfo) {
            if (!counts.containsKey(edge[0])) {
                counts.put(edge[0], new int[2]);
            }
            if (!counts.containsKey(edge[1])) {
                counts.put(edge[1], new int[2]);
            }
            counts.get(edge[0])[1]++; // out
            counts.get(edge[1])[0]++; // in
        }
    }
}
