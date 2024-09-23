import java.util.*;

// 한달 : 28일
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for(int i = 0; i < terms.length; i++) {
            String t = terms[i].substring(0 , 1);
            String v = terms[i].substring(2);
            map.put(t, v);
        }
        
        int todayD = calc(today);
    
        int len = privacies.length;
        for(int i = 0; i < len; i++) {
            int res = calc(privacies[i]);
            String t = privacies[i].substring(11);
            int v = Integer.parseInt(map.get(t));
            if(todayD >= res + (v * 28)) list.add(i + 1);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    static int calc(String priv) {
        // year / month / day
        String y = priv.substring(0, 4);
        String m = priv.substring(5, 7);
        String d = priv.substring(8, 10);
        
        // year -> month로
        int yy = Integer.parseInt(y) * 12;
        
        // 앞에 0이 있으면 한자릿수로 변경
        if(m.charAt(0) == '0') m = m.charAt(1)+"";
        if(d.charAt(0) == '0') d = d.charAt(1) + "";
        
        // (year + month) -> day로
        int mm = (yy + Integer.parseInt(m)) * 28;
        
        // day 형식으로 변경 
        int res =  mm + Integer.parseInt(d);
        
        return res;
    }
}
