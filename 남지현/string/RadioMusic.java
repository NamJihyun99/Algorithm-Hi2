import java.util.*;

// 프로그래머스 - 방금그곡

class Solution {
    
    public String solution(String m, String[] musicinfos) {
        Music[] musics = new Music[musicinfos.length];
        for (int i=0 ;i<musicinfos.length; i++) {
            musics[i] = new Music(musicinfos[i]);
        }
        Arrays.sort(musics);
        for (Music music : musics) {
            if (music.contains(m)) {
                return music.name;
            }
        }
        return "(None)";
    }
    
    static class Music implements Comparable<Music> {
        String name;
        String[] notes;
        
        Music(String arg) {
            String[] token = arg.split(",");
            this.name = token[2];
            this.notes = setSize(parseNotes(token[3]), toMinutes(token[0], token[1]));
        }
        
        private ArrayList<String> parseNotes(String sequence) {
            ArrayList<String> result = new ArrayList<>();
            int t = 0;
            for (int idx = 0; idx < sequence.length(); idx++) {
                if (sequence.charAt(idx)=='#') {
                    result.set(t-1, result.get(t-1) + "#");
                } else {
                    result.add(sequence.substring(idx, idx+1));
                    t++;
                }
            }
            return result;
        }
        
        private String[] setSize(ArrayList<String> sequence, int time) {
            String[] result = new String[time];
            int len = sequence.size();
            for (int t=0; t<time; t++) {
                result[t] = sequence.get(t%len);
            }
            return result;
        }
        
        private int toMinutes(String start, String end) {
            String[] startToken = start.split(":");
            String[] endToken = end.split(":");
            return (Integer.parseInt(endToken[0])*60+Integer.parseInt(endToken[1])) 
                - (Integer.parseInt(startToken[0])*60+Integer.parseInt(startToken[1]));
        }
        
        // KMP 알고리즘
        private boolean contains(String part) {
            List<String> target = parseNotes(part);
            int[] lps = new int[target.size()];
            int ptr = 0;
            for (int idx=1; idx<target.size(); idx++) {
                while (ptr > 0 && !target.get(ptr).equals(target.get(idx))) {
                    ptr = lps[ptr-1];
                }
                if (target.get(ptr).equals(target.get(idx))) {
                    ptr++;
                    lps[idx] = ptr;
                }
            }
            ptr = 0;
            for (int idx=0; idx<this.notes.length; idx++) {
                while (ptr > 0 && !target.get(ptr).equals(this.notes[idx])) {
                    ptr = lps[ptr-1];
                }
                if (target.get(ptr).equals(this.notes[idx])) {
                    if (ptr == target.size()-1) {
                        return true;
                    }
                    ptr++;
                }
            }
            return false;
        }
        
        public int compareTo(Music music) {
            return music.notes.length - this.notes.length;
        }
    }
}
