import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static Map<String, Integer> orderCnt = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        for (int courseNum : course) {
            for (String order : orders) {
                char[] charArr = order.toCharArray();
                Arrays.sort(charArr);
                order = new String(charArr);
                
                if (order.length() < courseNum) {
                    continue;
                }
                for (int i = 0; i < order.length(); i++) {
                    backtracking(courseNum, new StringBuilder().append(order.charAt(i)), order, i);
                }
            }
            
            long max = orderCnt.values().stream().max(Comparator.naturalOrder()).orElse(0);
            if (max < 2)    continue;
            answer.addAll(orderCnt.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
            orderCnt.clear();
        }
        Collections.sort(answer);
        return answer.toArray(new String[answer.size()]);
    }
    
    private static void backtracking(int target, StringBuilder selected, String order, int idx) {
        if (target == selected.length()) {
            orderCnt.put(selected.toString(), orderCnt.getOrDefault(selected.toString(), 0) + 1);
            return;
        }
        for (int i = idx + 1; i < order.length(); i++) {
            StringBuilder next = new StringBuilder(selected).append(order.charAt(i));
            backtracking(target, next, order, i);
        }
    }
}