import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int[][] stamina = new int[Math.min(picks[0] + picks[1] + picks[2], minerals.length / 5 + 1)][3];
        for (int i = 0; i < stamina.length; i++) {
            for (int j = i * 5; j < i * 5 + 5 && j < minerals.length; j++) {
                if (minerals[j].equals("diamond")) {
                    stamina[i][0]++;
                    stamina[i][1] += 5;
                    stamina[i][2] += 25;
                } else if (minerals[j].equals("iron")) {
                    stamina[i][0]++;
                    stamina[i][1]++;
                    stamina[i][2] += 5;
                } else {
                    stamina[i][0]++;
                    stamina[i][1]++;
                    stamina[i][2]++;
                }
            }
            
        }
        
        Arrays.sort(stamina, (o1, o2) -> (o2[2] - o1[2]));
        
        int answer = 0;
        for (int i = 0; i < stamina.length; i++) {
            if (picks[0] > 0) {
                picks[0]--;
                answer += stamina[i][0];
            } else if (picks[1] > 0) {
                picks[1]--;
                answer += stamina[i][1];
            } else {
                picks[2]--;
                answer += stamina[i][2];
            }
        }
        
        return answer;
    }
}