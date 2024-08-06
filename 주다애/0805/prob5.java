import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int len = enroll.length;
        int[] answer = new int[len];
        Map<String, String> map = new HashMap<>();
        Map<String, Integer> amountMap = new HashMap<>(); // 나, 인덱스 -> 나중에 값 더해주려고
        for(int i = 0; i < len; i++) {
            map.put(enroll[i], referral[i]); // 나, 부모
            amountMap.put(enroll[i], i);
        }
        int sLen = seller.length;
        for(int i = 0; i < sLen; i++) {
            // 다단계 이익 배분 시작
            String start = seller[i];
            // 기준이 되는 이익을 while 문 밖에서 구해놓아야 함
            int profit = amount[i] * 100;
            
            // 루트가 될 때까지
            while(!start.equals("-")) {
                String parent = map.get(start);
                int realProfit = profit / 10;
                answer[amountMap.get(start)] += profit - realProfit;
                profit /= 10; // profit 10%씩 해줘야 함
                start = map.get(start);
                if(profit <= 0) break;
            }
        }
        return answer;
    }
}
