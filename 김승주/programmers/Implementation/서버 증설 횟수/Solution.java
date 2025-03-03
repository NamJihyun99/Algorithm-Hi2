class Solution {
    public int solution(int[] players, int m, int k) {
        int[] counts = new int[24];
        int answer = 0;
        int curr = 0;
        for (int i = 0; i < players.length; i++) {
            if (i - k >= 0) {
                curr -= counts[i - k];
            }
            if (players[i] / m <= curr) {
                continue;
            }
            counts[i] = players[i] / m - curr;
            curr += counts[i];
            answer += counts[i];
        }
        return answer;
    }
}