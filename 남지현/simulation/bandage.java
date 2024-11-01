// 프로그래머스 붕대 감기
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int time = 0;
        int idx = 0;
        int sequence = 0;
        int now = health;
        int maxTime = attacks[attacks.length-1][0];
        while (time<=maxTime) {
            if (time == attacks[idx][0]) {
                now -= attacks[idx][1];
                if (now <= 0) break;
                sequence = 0;
                idx++;
            } else {
                now = now+bandage[1]>health? health: now+bandage[1];
                sequence++;
                if (sequence == bandage[0]) {
                    now = now+bandage[2]>health? health: now+bandage[2];
                    sequence = 0;
                }
            }
            time++;
        }
        return now<=0? -1: now;
    }
}
