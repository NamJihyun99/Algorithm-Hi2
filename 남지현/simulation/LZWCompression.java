import java.util.*;

// 프로그래머스 압축

class Solution {
    
    public int[] solution(String msg) {
        int L = msg.length(), index = 'Z'-'A'+2, maxLen = 1, ptr = 0;
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        
        for (char i='A'; i<='Z'; i++) {
            dict.put(""+i, i-'A'+1);
        }
        
        while (ptr < L) {
            // 1. 출력한다.
            int len = maxLen;
            String tmp = "";
            for (; len>=1; len--) {
                if (ptr+len > L) continue;
                tmp = msg.substring(ptr, ptr+len);
                if (dict.containsKey(tmp)) {
                    result.add(dict.get(tmp));
                    ptr += len;
                    break;
                }
            }
            // 2. 등록한다.
            if (ptr < L) {
                dict.put(tmp + msg.charAt(ptr), index++);
                maxLen = Math.max(maxLen, len+1);
            }
        }
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}
