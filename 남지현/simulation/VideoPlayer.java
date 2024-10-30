import java.util.*;

// 프로그래머스 - 동영상 재생기

class Solution {
  
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
      
        int len = convertStringToSeconds(video_len);
        int now = convertStringToSeconds(pos);
        int start = convertStringToSeconds(op_start);
        int end = convertStringToSeconds(op_end);
      
        int idx = 0;
      
        do {
            if (now>=start && now<end) {
                now = end;
            }
            if (commands[idx].equals("prev")) {
                if (now < 10) now = 0;
                else now -= 10;
            } else {
                if (now+10 > len) now = len;
                else now += 10;
            }
            if (now>=start && now<end) {
                now = end;
            }
            idx++;
        } while (idx < commands.length);
        
        return convertSecondsToString(now);
    }
    
    private int convertStringToSeconds(String arg) {
        String[] tmp = arg.split(":");
        return 60*Integer.parseInt(tmp[0]) + Integer.parseInt(tmp[1]);
    }
    
    private String convertSecondsToString(int arg) {
        StringBuilder sb = new StringBuilder();
        String time = arg/60 < 10? "0"+(arg/60): ""+(arg/60);
        String minute = arg%60 < 10? "0"+(arg%60): ""+(arg%60);
        sb.append(time).append(":").append(minute);
        return sb.toString();
    }
}
