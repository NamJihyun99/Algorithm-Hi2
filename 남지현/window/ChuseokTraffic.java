import java.util.*;

// 프로그래머스 추석 트래픽

class Solution {
    public int solution(String[] lines) {
        int N = lines.length;
        List<Job> jobs = new ArrayList<>(N);
        for (int i=0; i<N; i++) {
            String[] token = lines[i].split(" ");
            jobs.add(new Job(parseTimeToMilli(token[1]), parseSecToMilli(token[2])));
        }
        int answer = 1;
        for (int i=0; i<N; i++) {
            int count = 0;
            int end = jobs.get(i).end;
            for (Job job : jobs) {
                // end부터 1초 크기의 window
                // 01:00:03.003 ~ 01:00:04.002 -> 1초
                if (job.start <= end+1000-1 && job.end >= end) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }
        return answer;
    }
    
    private int parseSecToMilli(String secStr) {
        return (int) (1000.0*Double.parseDouble(secStr.substring(0, secStr.length()-1)));
    }
    
    private int parseTimeToMilli(String timeStr) {
        String[] token = timeStr.split(":");
        int milli = Integer.parseInt(token[0])*3600000 + Integer.parseInt(token[1])*60000;
        milli += (int) (1000.0*Double.parseDouble(token[2]));
        return milli;
    }
    
    static class Job {
        int start;
        int end;
        
        Job(int end, int duration) {
            this.end = end;
            this.start = end-duration+1 < 0? 0 : end-duration+1;
        }
    }
}
