import java.util.*;

// 프로그래머스 - 순위 검색

class Solution {
    
    static final Map<String, Integer> codes = Map.of (
        "cpp", 0, "java", 1, "python", 2, 
        "backend", 0, "frontend", 1, 
        "junior", 0, "senior", 1, 
        "chicken", 0, "pizza", 1
    );
    Map<String, List<Integer>> infos;
    String[] token1, token2;
    int sum;
    
    public int[] solution(String[] info, String[] query) {
        infos = new HashMap<>();
        for (String arg: info) {
            String[] tokens = arg.split(" ");
            String key = getKey(tokens);
            List<Integer> values = infos.getOrDefault(key, new ArrayList<Integer>());
            values.add(Integer.valueOf(tokens[4]));
            infos.put(key, values);
        }
        for (Map.Entry<String, List<Integer>> entry: infos.entrySet()) {
            Collections.sort(entry.getValue());
        }
        int[] answer = new int[query.length];
        for (int i=0; i<query.length; i++) {
            token1 = query[i].split(" and ");
            token2 = token1[3].split(" ");
            token1[3] = token2[0];
            sum = 0;
            combination(0, "");
            answer[i] = sum;
        }
        return answer;
    }
    
    private void combination(int depth, String key) {
        if (depth == 4) {
            if (!infos.containsKey(key)) return;
            List<Integer> scores = infos.get(key);
            int left = 0, right = scores.size(), target = Integer.valueOf(token2[1]);
            while (left < right) {
                int mid = (left + right)/2;
                if (scores.get(mid) >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            sum += scores.size() - left; // scores.size() - (left-1) + 1
            return;
        }
        if (token1[depth].equals("-")) {
            combination(depth+1, key + 0);
            combination(depth+1, key + 1);
            if (depth == 0) combination(depth+1, key + 2);
        } else {
            combination(depth+1, key+codes.get(token1[depth]));
        }
    }
    
    private String getKey(String[] tokens) {
        return "" + codes.get(tokens[0]) + codes.get(tokens[1]) + codes.get(tokens[2]) + codes.get(tokens[3]);
    }
}
