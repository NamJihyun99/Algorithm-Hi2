import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
        str1 = str1.toLowerCase();      
        str2 = str2.toLowerCase();
        
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        
        int len1 = str1.length();
        int len2 = str2.length();
        
        for(int i = 0; i < len1 - 1; i++) {
            String s = str1.substring(i, i + 2);
            if(s.charAt(0) < 'a' || s.charAt(0) > 'z' || s.charAt(1) < 'a' || s.charAt(1) > 'z') continue;
            list1.add(s);
        }
        for(int i = 0; i < len2 - 1; i++) {
            String s = str2.substring(i, i + 2);
            if(s.charAt(0) < 'a' || s.charAt(0) > 'z' || s.charAt(1) < 'a' || s.charAt(1) > 'z') continue;
            list2.add(s);
        }
        
        int union = 0; // 합
        int intersection = 0; // 교

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        Set<String> set = new HashSet<>();
        
        for(String s : list1) {
            int v = map1.getOrDefault(s, 0);
            map1.put(s, v + 1);
            set.add(s);
        }
        for(String s : list2) {
            int v = map2.getOrDefault(s, 0);
            map2.put(s, v + 1);
            set.add(s);
        }
        
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            String s = it.next();
            if(list1.contains(s) && list2.contains(s)) {
                intersection += Math.min(map1.get(s), map2.get(s));
                union += Math.max(map1.get(s), map2.get(s));
                }
            else if(list1.contains(s)) {
                union += map1.get(s);
            }
            else if(list2.contains(s)) {
                union += map2.get(s);
            }
        }
        
        double jacc = 0.0;
        if(union == 0 && intersection == 0) jacc = 65536;
        else jacc = (intersection * 65536 / union);
        answer = (int) Math.floor(jacc);
        return answer;
    }
}
