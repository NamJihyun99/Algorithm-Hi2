class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int lenSec = toSec(video_len);
        int curr = toSec(pos);
        int startSec = toSec(op_start);
        int endSec = toSec(op_end);
        
        if (startSec <= curr && curr <= endSec) {
            curr = endSec;
        }
        for (String command : commands) {
            if (command.equals("next")) {
                if (curr + 10 > lenSec) {
                    curr = lenSec;
                } else {
                    curr += 10;
                }
            } else {
                if (curr - 10 < 0) {
                    curr = 0;
                } else {
                    curr -= 10;
                }
            }
            
            if (startSec <= curr && curr <= endSec) {
                curr = endSec;
            }
        }
        
        return resultBuilder(curr);
    }
    
    private static int toSec(String time) {
        String[] minAndSec = time.split(":");
        return Integer.parseInt(minAndSec[0]) * 60 + Integer.parseInt(minAndSec[1]);
        
    }
    
    private static String resultBuilder(int curr) {
        StringBuilder sb = new StringBuilder();
        if (curr / 60 < 10) {
            sb.append(0);
        }
        sb.append(curr / 60).append(":");
        if (curr % 60 < 10) {
            sb.append(0);
        }
        sb.append(curr % 60);
        return sb.toString();
    }
}