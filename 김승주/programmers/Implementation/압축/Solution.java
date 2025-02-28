import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int length = msg.length();
        List<Integer> answer = new ArrayList<>();
        List<String> dic = new ArrayList<>();
        dic.add("void");
        for (char c = 'A'; c <= 'Z'; c++) {
            dic.add(Character.toString(c));
        }

        for (int start = 0; start < length;) {
            StringBuilder sb = new StringBuilder();
            int end = start;
            for (; end < length; end++) {
                sb.append(msg.charAt(end));
                if (!dic.contains(sb.toString())) {
                    dic.add(sb.toString());
                    start = end;
                    sb.deleteCharAt(sb.length() - 1);
                    answer.add(dic.indexOf(sb.toString()));
                    break;
                }
            }
            if (end == length) {
                answer.add(dic.indexOf(sb.toString()));
                break;
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}