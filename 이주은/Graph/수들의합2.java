//https://www.acmicpc.net/problem/2003

import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] A;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0, i = 0, j = 0, sum = A[0];

        while(true) {
            if(sum == M) {
                cnt ++;
            }
            
            if(sum < M || i == j) {
                j++;
                
                if(j == N)
                    break;
                sum += A[j];
            }
            else {
                
                
                sum -= A[i];
                i++;
            }
        }

        System.out.print(cnt);
    }
}
