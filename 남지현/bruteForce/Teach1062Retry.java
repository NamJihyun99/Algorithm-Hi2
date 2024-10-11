import java.util.*;
import java.io.*;

// 백준 1062번 - 가르침 (재풀이)

class Main {

    static int[] words;
    static int N, K, answer; 

    static void search(int value, int depth, int start) {
        if (depth == K) {
            int count = 0;
            for (int word: words) {
                if ((~value & word) == 0) count++;
            }
            answer = Math.max(answer, count);
            return;
        }
        for (int i=start; i<26; i++) {
            if ((value&1<<i)==0)
                search(value|(1<<i), depth+1, i);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new int[N];
        for (int i=0; i<N; i++) {
            String word = br.readLine();
            int tmp = 0;
            for (int j=0; j<word.length(); j++) {
                tmp |= 1<<word.charAt(j)-'a';
            }
            words[i] = tmp;
        }
        if (K<5) {
            System.out.println(0);
            return;
        } else if (K==26) {
            System.out.println(N);
            return;
        }
        int know = 1<<'a'-'a' | 1<<'c'-'a' | 1<<'i'-'a' | 1<<'n'-'a' | 1<<'t'-'a';
        answer = 0;
        search(know, 5, 0);
        System.out.println(answer);
    }
}
