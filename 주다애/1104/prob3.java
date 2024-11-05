import java.util.*;

class Solution {
    static String[] words = {"A", "E", "I", "O", "U"};
    static int answer;
    static StringBuilder sb;
    static boolean flag;
    public int solution(String word) {
        answer = 0;
        sb = new StringBuilder();
        run(0, word);
        flag = false;
        return answer;
    }
    
    static void run(int level, String word) {
        if(sb.toString().equals(word)) {
            flag = true;
            return;
        }
        
        if(level == 5) return; // stack에 쌓이는 것 방지
        
        for(int i = 0; i < 5; i++) {
            sb.append(words[i]);
            answer++;
            run(level + 1, word);
            if(flag) return;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
