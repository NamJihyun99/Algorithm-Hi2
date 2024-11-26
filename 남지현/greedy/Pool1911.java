import java.util.*;
import java.io.*;

// 백준 1911번 - 흙길 보수하기

class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] pools = new int[N][2];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            pools[i][0]= Integer.parseInt(st.nextToken());
            pools[i][1]= Integer.parseInt(st.nextToken());
        }
        Arrays.sort(pools, (arr1, arr2) -> arr1[0]-arr2[0]);
        int count = 0;
        int pointer = 0;
        for (int i=0; i<N; i++) {
            if (pointer < pools[i][0]) {
                pointer = pools[i][0];
            }
            while(pointer < pools[i][1]) {
                pointer += L;
                count++;
            } 
        }
        System.out.println(count);
    }
}
