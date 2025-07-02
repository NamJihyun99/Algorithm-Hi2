import java.util.*;

// j == len일 때 처리해야한다.
class Solution {
    static String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static List<String> cap;
    public int[] solution(String msg) {
        cap = new ArrayList<>();
        // for(int i = 0; i < 26; i++) {
        //     cap.add(str.substring(i, i + 1));
        // }
        for(int i = 0; i < 26; i++) {
            cap.add(String.valueOf((char)('A' + i)));
        }
        List<Integer> ans = new ArrayList<>();
        int len = msg.length();
        int k = 0;
        for(int i = 0; i < len; i += k) {
            for(int j = len; j >= i; j--) {
                String temp = msg.substring(i, j);
                if(cap.contains(temp)) {
                    int idx = cap.indexOf(temp) + 1;
                    ans.add(idx);
                    String next = "";
                    if(j != len) next = msg.substring(i, j + 1);
                    else next = msg.substring(i);
                    cap.add(next);
                    int length = temp.length();
                    k = length;
                    break;
                }
            }
        }
        int size = ans.size();
        int[] answer = new int[size];
        for(int i = 0; i < size; i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
