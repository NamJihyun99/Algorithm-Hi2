import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> splitedStr1 = makeSet(str1);
        Map<String, Integer> splitedStr2 = makeSet(str2);
        if (splitedStr1.isEmpty() && splitedStr2.isEmpty()) {
            return 65536;
        }
        float union = 0f;
        float intersection = 0f;
        for (String s : splitedStr1.keySet()) {
            if (splitedStr2.keySet().contains(s)) {
                intersection += Math.min(splitedStr1.get(s), splitedStr2.get(s));
                union += Math.max(splitedStr1.get(s), splitedStr2.get(s));
            } else {
                union += splitedStr1.get(s);
            }
        }
        for (String s : splitedStr2.keySet()) {
            if (!splitedStr1.keySet().contains(s)) {
                union += splitedStr2.get(s);
            }
        }

        return (int) (intersection / union * 65536);
    }
    
    private static Map<String, Integer> makeSet(String str) {
        Map<String, Integer> result = new HashMap<>();
        str = str.toLowerCase();
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z'
               && str.charAt(i + 1) >= 'a' && str.charAt(i + 1) <= 'z') {
                result.put(str.substring(i, i + 2), result.getOrDefault(str.substring(i, i + 2), 0) + 1);
            }
        }
        return result;
    }
}