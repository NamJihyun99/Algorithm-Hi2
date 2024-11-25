import java.util.*;

// 프로그래머스 - 로또의 최고 순위와 최저 순위

class Solution {   
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        long lotto = 0, win = 0;
        int unknown = 0;
        for (int number: lottos) {
            if (number == 0) unknown++;
            else lotto |= 1L<<number;
        }
        for (int number: win_nums) {
            win |= 1L<<number;
        }
        int matched = numberOfOne(lotto&win);
        int max = Math.min(unknown, 6-matched);
        return new int[]{rank[matched+max], rank[matched]};
    }
    
    private int numberOfOne(long bits) {
        String binaryString = Long.toString(bits, 2);
        return binaryString.length() - binaryString.replace("1","").length();
    }
}
