import java.util.*;

// 프로그래머스 - 다단계 칫솔 판매 (반복문)
// while문에서 10% 계산 결과(divide)가 1보다 크거나 같다는 조건을 제거하면 시간 초과 발생
// 재귀를 활용한 방법보다 전반적으로 빨랐음.

class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> idx = new HashMap<>();
        int[] answer = new int[enroll.length];
        
        for (int i=0; i<enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
            idx.put(enroll[i], i);
        }
        for (int i=0; i<seller.length; i++) {
            String node = seller[i];
            int divide = amount[i]*100; 
            while (! node.equals("-") && divide >= 1) {
                int total = divide;
                divide = divide*10/100;
                int remain = total - divide;
                answer[idx.get(node)] += remain;
                node = parent.get(node);
            }
        }
        return answer;
    }
}
