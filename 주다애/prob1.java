import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int end = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] at : attacks) {
            map.put(at[0], at[1]);
            end = at[0];
        }
        int cont = 0;
        int max = health;
        for(int i = 1; i <= end; i++) {
            // 공격 일어난 경우
            if(map.containsKey(i)) {
                cont = 0; // 연속 성공 초기화
                health -= map.get(i);
                if(health <= 0) return -1; // 더 이상 진행할 수 없음
                continue;
            }
            // 일단 x만큼 더해주어야 함
            health += bandage[1];
            cont++;
            // 연속 붕대 감기 성공한 경우
            if(cont == bandage[0]) {
                health += bandage[2];
                cont = 0;
            }
            if(health <= 0) return -1; // 더 이상 진행할 수 없음
            if(health > max) health = max;
        }
        return health;
    }
}
