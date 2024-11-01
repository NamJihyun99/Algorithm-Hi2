import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String[] vt = video_len.split(":");
        String[] posT = pos.split(":");
        String[] st = op_start.split(":");
        String[] et = op_end.split(":");
        int vl = convertToSec(vt);
        int now = convertToSec(posT);
        int os = convertToSec(st);
        int oe = convertToSec(et);
        // 명령 실행
        int ans = 0;
        for(String c : commands) {
            if(c.equals("prev")) {
                if(now >= os && now <= oe) now = oe;
                if(now < 10) {
                    now = 0;
                    continue;
                }
                now -= 10;
            }
            else if(c.equals("next")) {
                if(now >= os && now <= oe) now = oe;
                if(vl - now < 10) {
                    now = vl;
                    continue;
                }
                now += 10;
            }
            if(now >= os && now <= oe) now = oe;
        }
        return convertToTime(now);
    }
    static String convertToTime(int now) {
        int m = now / 60;
        int s = now % 60;
        String sm = m + "";
        String ss = s + "";
        if(sm.length() == 1) sm = "0" + sm;
        if(ss.length() == 1) ss = "0" + ss;
        String res = sm + ":" + ss;
        return res;
    }
    static int convertToSec(String[] temp) {
        String hour = temp[0];
        String min = temp[1];
        if(hour.charAt(0) == '0') hour = hour.charAt(1) + "";
        if(min.charAt(0) == '0') min = min.charAt(1) + "";
        int res = Integer.parseInt(hour) * 60 + Integer.parseInt(min);
        return res;
    }
}
