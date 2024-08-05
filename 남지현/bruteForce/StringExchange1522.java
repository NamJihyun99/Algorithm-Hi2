import java.util.*;
import java.io.*;

// 백준 1522번 - 문자열 교환

class Main {

    static int len;

    private static int index(int idx) {
        if (idx < 0) {
            return idx+len;
        } else if (idx >= len) {
            return idx-len;
        }
        return idx;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        int window = 0; len = input.length();
        for (int i=0; i<len; i++) {
            if (input.charAt(i) == 'a') window++;
        }
        int aCount = 0;
        for (int i=0; i<window; i++) {
            if (input.charAt(i) == 'a') aCount++;
        }
        int max = aCount, start = 1;
        while (start < len) {
            if (input.charAt(index(start-1)) == 'a') aCount--;
            if (input.charAt(index(start+window-1)) == 'a') aCount++;
            max = Math.max(max, aCount);
            start++;
        }
        System.out.println(window-max);
    }
}
