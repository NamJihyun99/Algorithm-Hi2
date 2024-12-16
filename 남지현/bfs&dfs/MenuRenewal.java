import java.util.*;

// 프로그래머스 - 메뉴 리뉴얼

class Solution {
    
    Map<String, Integer> menuCount;
    int max, len;
    
    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        for (int number: course) {
            menuCount = new HashMap<>();
            max = 0;
            len = number;
            for (String order : orders) {
                char[] orderArray = order.toCharArray();
                Arrays.sort(orderArray);
                dfs(orderArray, "", -1, 0);
            }
            for (Map.Entry<String, Integer> entry : menuCount.entrySet()) {
                if (entry.getValue()>=2 && max==entry.getValue()) {
                    result.add(entry.getKey());
                }
            }
        }
        Collections.sort(result);
        String[] answer = new String[result.size()];
        for (int i=0; i<result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    private void dfs(char[] array, String menu, int idx, int depth) {
        if (depth == len) {
            menuCount.put(menu, menuCount.getOrDefault(menu, 0)+1);
            max = Math.max(max, menuCount.get(menu));
        }
        for(int i = idx+1; i<array.length; i++) {
            dfs(array, menu+array[i], i, depth+1);
        }
    }
}
