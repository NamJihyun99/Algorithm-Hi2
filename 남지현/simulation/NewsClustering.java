import java.util.*;

// 프로그래머스 - 뉴스 클러스터링

class Solution {
    
    static final int MUL = 65536;
    
    public int solution(String str1, String str2) {
        List<String> set1 = makeTokens(str1);
        List<String> set2 = makeTokens(str2);
        List<String> union = new ArrayList<>(set2);
        List<String> join = new ArrayList<>();
        for (String token : set1) {
            if (set2.contains(token)) {
                join.add(token);
            } else {
                union.add(token);
            }
            set2.remove(token);
        }
        double answer = 1.0;
        if (union.size() > 0) {
            answer = (double) join.size()/union.size();
        }
        return (int) (answer * MUL);
    }
    
    private List<String> makeTokens(String str) {
        int len = str.length();
        List<String> result = new ArrayList<>();
        for (int i=0; i<len-1; i++) {
            if (enable(str.charAt(i)) && enable(str.charAt(i+1))) {
                result.add(str.substring(i, i+2).toUpperCase());
            }
        }
        return result;
    }
    
    private boolean enable(char c) {
        return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
    }
}
