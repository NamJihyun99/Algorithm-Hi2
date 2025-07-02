import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int answer = 1;
        List<Traffic> traffics = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String[] splited = lines[i].split(" ");
            String[] time = splited[1].split(":");
            int end = (int) (Integer.parseInt(time[0]) * 60 * 60 * 1000
                + Integer.parseInt(time[1]) * 60 * 1000
                + Double.parseDouble(time[2]) * 1000);
            int duration = (int) (Double.parseDouble(splited[2].split("s")[0]) * 1000);
            int start = end - duration + 1;
            traffics.add(new Traffic(start, end));
        }
        
        for (int i = 0; i < traffics.size(); i++) {
            int count = 0;
            for (int j = 0; j < traffics.size(); j++) {
                if (traffics.get(j).start < traffics.get(i).end + 1000 
                   && traffics.get(j).end >= traffics.get(i).end) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }
            
        return answer;
    }
    
    static class Traffic {
        int start;
        int end;
        
        Traffic(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}