import java.util.*;
import java.io.*;

// 백준 2531번 회전 초밥 (종류 x인 초밥을 몇 개 먹게 되는지 selected[x]에 저장하는 방법)

class Main {
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); // window_size
        int c = Integer.parseInt(st.nextToken());
        int[] sushi = new int[N];
        int[] selected = new int[d+1];
        int count = 0;
        for (int i=0; i<N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
            if (i < k) {
                if (selected[sushi[i]]++ == 0) {
                    count++;
                }
            }
        }
        int answer = 0;
        for (int left=0; left<N; left++) {
            if (selected[c]++ == 0) {
                count++;
            }
            answer = Math.max(answer, count);
            if (--selected[sushi[left]] == 0) {
                count--;
            }
            if (selected[sushi[(left+k)%N]]++ == 0) {
                count++;
            }
        }
        System.out.println(answer);
    }
}
