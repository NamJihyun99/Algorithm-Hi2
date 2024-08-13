//백준 2195번 문자열 복사

import java.util.*;
import java.io.*;

class Main {
    static String S, P;
    static int answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        P = br.readLine();

        int sLen = S.length();
        int pLen = P.length();

        for(int p=0; p<pLen;) {
            int max = 1;
            
            for(int s=0; s<sLen; s++) {
                int cnt = 0;
                
                while(s+cnt < sLen && p+cnt < pLen && S.charAt(s+cnt) == P.charAt(p+cnt)){
                    cnt ++;
                }

                max = Math.max(max, cnt);
            }
            p += max;
            answer++;
        }

        System.out.println(answer);
    }
}
