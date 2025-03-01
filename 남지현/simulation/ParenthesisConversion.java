import java.util.*;

// 프로그래머스 괄호 변환

class Solution {
    
    public String solution(String p) {
        return make(p);
    }
    
    private String make(String token) {
        int len = token.length();
        if (len == 0) return "";
        String u = "", v = "";
        for (int i=2; i<=len; i+=2) {
            if (isBalanced(token.substring(0, i))) {
                u = token.substring(0, i);
                v = token.substring(i);
                break;
            }
        }
        if (isCorrect(u)) {
            return u + make(v);
        }
        String result = "(" + make(v) + ")";
        result += reverse(u.substring(1, u.length()-1));
        return result;
    }
    
    private String reverse(String token) {
        StringBuilder sb = new StringBuilder();
        for (char c : token.toCharArray()) {
            if (c=='(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }
    
    private boolean isCorrect(String token) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : token.toCharArray()) {
            if (c=='(') {
                stack.addLast(c);
            } else if (!stack.isEmpty()) {
                stack.pollLast();
            } else {
                return false;
            }
        }
        return true;
    }
    
    private boolean isBalanced(String token) {
        int len = token.length();
        int count = 0;
        for (char c : token.toCharArray()) {
            if (c=='(') count++;
        }
        return count+count == len;
    }
}
