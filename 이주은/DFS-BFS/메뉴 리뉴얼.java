//https://school.programmers.co.kr/learn/courses/30/lessons/72411

import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();
    int[] max = new int[11];
    int[] course;
    
    public String[] solution(String[] orders, int[] course) {
        this.course = course;
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<orders.length; i++) {
            char[] order = orders[i].toCharArray();
            Arrays.sort(order);
            
            sb.setLength(0);
            comb(sb, order, 0);
        }        
        
        List<String> list = new ArrayList<>();
        for(int cnt : course) {
            if(max[cnt] < 2)
                continue;
            
            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                if(entry.getKey().length() != cnt || entry.getValue() != max[cnt])
                    continue;

                list.add(entry.getKey());
                continue;
            }
        }
        
        Collections.sort(list);
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    private void comb(StringBuilder sb, char[] order, int idx) {
        if(idx == order.length) {
            
            if(sb.length() >= 2) {
                int value = map.getOrDefault(sb.toString(), 0) + 1;
                map.put(sb.toString(), value);

                max[sb.length()] = Math.max(value, max[sb.length()]);
            }
            
            return;
        }
   
        sb.append(order[idx]);
        comb(sb, order, idx+1);
        
        sb.deleteCharAt(sb.length()-1);
        comb(sb, order, idx+1);
    }
}
