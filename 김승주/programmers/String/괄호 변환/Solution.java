import java.util.*;

class Solution {
    public String solution(String p) {        
        return makeRightBracketString(p);
    }
    
    private static String makeRightBracketString(String p) {
        if (p.isEmpty()) {
            return p;
        }
        
        String[] splited = splitString(p);
        if (isItRightBracketString(splited[0])) {
            return splited[0] + makeRightBracketString(splited[1]);
        }
        StringBuilder sb1 = new StringBuilder("(");
        sb1.append(makeRightBracketString(splited[1])).append(")");
        StringBuilder sb2 = new StringBuilder(splited[0].substring(1, splited[0].length() - 1));
        for (int i = 0; i < sb2.length(); i++) {
            if (sb2.charAt(i) == '(') {
                sb2.setCharAt(i, ')');
            } else {
                sb2.setCharAt(i, '(');
            }
        }
        sb1.append(sb2);
        return sb1.toString();
    }
    
    private static String[] splitString(String s) {
        String[] result = new String[2];
        int length = s.length();
        int left = 0;
        int right = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                result[0] = s.substring(0, i + 1);
                result[1] = (i == length - 1)? "" : s.substring(i + 1, length);
                break;
            }
        }
        
        return result;
    }
    
    private static boolean isItRightBracketString(String u) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (stack.isEmpty() || stack.peek() == ')') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}