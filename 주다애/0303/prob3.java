class Solution {
    static int[] day = {0, 1, 2, 3, 4, 5, 6};
    static int[][] arr;
    static int[][] commute;
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int len = schedules.length;
        arr = new int[len + 1][7];
        commute = new int[len][2];
        // 요일 채우기
        for(int i = 0; i < 7; i++) {
            arr[0][i] = (startday + i) % 7;
        }
        // 출근 시간 채우기
        for(int i = 0; i < len; i++) {
            int start = calc(schedules[i]);
            commute[i][0] = start;
            commute[i][1] = start + 10;
            for(int j = 0; j < 7; j++) {
                int time = calc(timelogs[i][j]);
                arr[i + 1][j] = time;
            }
        }
        for(int i = 0; i < len; i++) {
            int start = commute[i][0];
            int end = commute[i][1];
            boolean flag = false;
            for(int j = 0; j < 7; j++) {
                // 주말은 고려하지 않음
                if(arr[0][j] == 6 || arr[0][j] == 0) continue;
                if(arr[i + 1][j] > end) {
                    flag = true;
                    break;
                }
            }
            if(!flag) answer += 1;
        }
        return answer;
    }
        
    static int calc(int time) {
        int h = time / 100;
        int m = time % 100;
        return (h * 60) + m;
    }
}
