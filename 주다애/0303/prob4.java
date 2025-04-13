import java.util.*;

class Solution {
    public String solution(String p) {
        // 1
        if(p.equals("")) return "";
        // 2
        int len = p.length();
        int l = 0;
        int r = 0;
        String temp = "";
        int idx = 0;
        for(int i = 0; i < len; i++) {
            if(p.charAt(i) == '(') l++;
            else r++;
            temp += String.valueOf(p.charAt(i));
            if(l == r) {
                idx = i;   
                break;
            }
        }
        String u = temp;
        String v = p.substring(idx + 1);
        // System.out.println("U " + u + " V " + v + " P " + p);
        // 3
        if(proper(u)) {
            // 3-1
            return u + solution(v);
        }
        // 4
        return "(" + solution(v) + ")" + reverse(u);
    }
    
    // 4-1 ~ 4-5
    static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for(int i = 1; i < len - 1; i++) {
            if(s.charAt(i) == '(') sb.append(")");
            else sb.append("(");
        }
        return sb.toString();
    }
    
    static boolean proper(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for(int i = 0; i < len; i++) {
            if(s.charAt(i) == '(') stack.add('(');
            else if(!stack.isEmpty() && s.charAt(i) == ')') stack.pop();
        }
        if(!stack.isEmpty()) return false;
        return true;
    }
}
