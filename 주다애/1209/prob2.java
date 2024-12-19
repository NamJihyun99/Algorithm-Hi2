import java.util.*;

// 나가는 간선, 들어오는 간선 map으로 저장
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, Integer> out = new HashMap<>();
        Map<Integer, Integer> in = new HashMap<>();

        for(int[] e : edges) {
            int ov = out.getOrDefault(e[0], 0);
            out.put(e[0], ov + 1);
            int iv = in.getOrDefault(e[1], 0);
            in.put(e[1], iv + 1);
        }
        
        // 정점 구하기 -> 나가는 간선 2개 이상, 들어오는 간선은 없다.
        for(int ok : out.keySet()) {
            if(out.get(ok) >= 2) {
                if(!in.containsKey(ok)) {
                    answer[0] = ok;
                    break;
                }
            }
        }

        // 막대 그래프 -> 나가는 간선 없다. 들어오는 간선 1개 이상
        for(int ik : in.keySet()) {
            if(in.get(ik) >= 1) {
                if(!out.containsKey(ik)) {
                    answer[2]++;
                }
            }
        }

        
        // 8자 그래프 -> 나가는 간선 2개, 들어오는 간선 2개 이상
        for(int ok : out.keySet()) {
            if(out.get(ok) == 2) {
                if(in.containsKey(ok) && in.get(ok) >= 2) {
                    answer[3]++;
                }
            }
        }
        
        // 정점에서 out개수 - (8자 + 막대) = 도넛
        answer[1] = out.get(answer[0]) - (answer[2] + answer[3]);
        return answer;
    }
}
