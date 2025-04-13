import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        List<String> ans = new ArrayList<>();
        List<Music> res = new ArrayList<>();
        int num = 0;
        for(String music : musicinfos) {
            String[] sp = music.split(",");
            int start = convertToMinutes(sp[0]);
            int end = convertToMinutes(sp[1]);
            int term = end - start;
            int len = sp[3].length();
            List<String> temp = new ArrayList<>();
            int i = 0;
            while(temp.size() < term) {
                if((sp[3].charAt(i % len) == 'C' || sp[3].charAt(i % len) == 'D' || 
                   sp[3].charAt(i % len) == 'F' || sp[3].charAt(i % len) == 'G' || 
                    sp[3].charAt(i % len) == 'A' || sp[3].charAt(i % len) == 'B'|| 
                    sp[3].charAt(i % len) == 'E') && sp[3].charAt((i + 1) % len) == '#') {
                    temp.add(sp[3].charAt(i % len) + "#");
                    i += 2;
                    continue;
                }
                temp.add(sp[3].charAt(i % len) + "");
                i += 1;
            }
            if(calc(m, temp)) res.add(new Music(term, num, temp, sp[2]));
            num += 1;
        }
        Collections.sort(res);
        if(res.size() == 0) return "(None)";
        return res.get(0).name;
    }
    
    static boolean calc(String m, List<String> temp) {
        int len = m.length();
        int i = 0;
        List<String> t = new ArrayList<>();
        while(i < len) {
            if( (m.charAt(i % len) == 'C' || m.charAt(i % len) == 'D' || 
            m.charAt(i % len) == 'F' || m.charAt(i % len) == 'G' || 
            m.charAt(i % len) == 'A' || m.charAt(i % len) == 'B'|| 
            m.charAt(i % len) == 'E') && (m.charAt((i + 1) % len) == '#')) {
                t.add(m.charAt(i % len) + "#");
                i += 2;
                continue;
            }
            t.add(m.charAt(i % len) + "");
            i += 1;
        }
        int tempLen = temp.size();
        int tLen = t.size();
        int diff = tempLen - tLen;
        // if(diff < 0) return false;
        for(int k = 0; k <= diff; k++) {
            String str = "";
            for(int j = k; j < k + tLen; j++) {
                str += temp.get(j);
            }
            if(str.equals(m)) return true;
        }
        return false;
    }
    static int convertToMinutes(String h) {
         String[] parts = h.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
    
    static class Music implements Comparable<Music> {
        int time;
        int num;
        List<String> temp;
        String name;
        
        Music(int time, int num, List<String> temp, String name) {
            this.time = time;
            this.num = num;
            this.temp = temp;
            this.name = name;
        }
        
        public int compareTo(Music m) {
            if(this.time == m.time) return this.num - m.num;
            return m.time - this.time;
        }
    }
}
