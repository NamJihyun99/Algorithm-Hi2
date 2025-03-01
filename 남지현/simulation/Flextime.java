// 프로그래머스 유연근무제

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;
        startday--;
        for (int i=0; i<n; i++) {
            boolean success = true;
            int end = time(schedules[i]+10);
            for (int j=0; j<7; j++) {
                int day = (startday + j) % 7;
                if (day==5 || day==6) continue;
                if (timelogs[i][j] > end) {
                    success = false;
                    break;
                }
            }
            if (success) answer++;
        }
        return answer;
    }
    
    private int time(int log) {
        int time = log/100;
        int minute = log%100;
        if (minute >= 60) {
            minute -= 60;
            time += 1;
        }
        return time*100 + minute;
    }
}
