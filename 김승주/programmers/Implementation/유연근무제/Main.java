class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        for (int i = 0; i < schedules.length; i++) {
            int j = 0;
            for (; j < timelogs[i].length; j++) {
                int dayOfWeek = (startday + j) % 7;
                if (dayOfWeek == 6 || dayOfWeek == 0) {
                    continue;
                }
                int deadline = schedules[i] / 100 * 60 + schedules[i] % 100 + 10;
                int todaysAttendTime = timelogs[i][j] / 100 * 60 + timelogs[i][j] % 100;
                if (deadline < todaysAttendTime) {
                    break;
                }
            }
            if (j == timelogs[i].length) {
                answer++;
            }
        }
        return answer;
    }
}