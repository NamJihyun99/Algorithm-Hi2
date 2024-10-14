class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int hp = health;
        int consecutiveSuccess = 0;
        int attackIdx = 0;
        for (int sec = 0; sec <= attacks[attacks.length - 1][0]; sec++) {
            if (attacks[attackIdx][0] == sec) {
                hp -= attacks[attackIdx][1];
                consecutiveSuccess = 0;
                attackIdx++;
                if (hp <= 0) {
                    return -1;
                }
            } else {
                hp += bandage[1];
                consecutiveSuccess++;
                if (consecutiveSuccess == bandage[0]) {
                    hp += bandage[2];
                    consecutiveSuccess = 0;
                }
                if (hp > health) {
                    hp = health;
                }
            }
        }
        return hp;
    }
}