import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int len = genres.length;
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> cmap = new HashMap<>();
        for(int i = 0; i < len; i++) {
            String g = genres[i];
            int p = plays[i];
            int v = map.getOrDefault(g, 0);
            map.put(g, v + p);
            cmap.put(g, 0);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int i = 0; i < len; i++) {
            int sum = map.get(genres[i]);
            pq.add(new Node(genres[i], sum, plays[i], i));
        }
        
        int idx = 0;
        List<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            String g = node.g;
            cmap.put(g, cmap.get(g) + 1);
            if(cmap.get(g) > 2) continue;
            list.add(node.num);
        }
        int[] answer = new int[list.size()];
        for(int i : list) answer[idx++] = i;
        return answer;
    }
    
    static class Node implements Comparable<Node> {
        String g;
        int sum;
        int p;
        int num;
        
        Node(String g, int sum, int p, int num) {
            this.g = g;
            this.sum = sum;
            this.p = p;
            this.num = num;
        }
        
        public int compareTo(Node n) {
            if(this.sum == n.sum) {
                if(this.p == n.p) {
                    return this.num - n.num;
                }
                return n.p - this.p;
            }
            return n.sum - this.sum;
        }
    }
}
