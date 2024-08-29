import java.util.*;
import java.io.*;

// SWEA 3421 - 수제 버거 장인

class Solution {

    static int[][] forbidden;
    static int N, count, sum;

    private static void dfs(int num, int depth) {
        if (depth == N) {
            sum++;
            for (int[] set: forbidden) {
                if ((set[0] & num) > 0 && (set[1] & num) > 0) {
                    count++;
                    break;
                }
            }
            return;
        }
        dfs((num<<1)+1, depth+1);
        dfs(num<<1, depth+1);
    }
    
    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
		
        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            forbidden = new int[M][2];
            for (int i=0; i<M; i++) {
                st = new StringTokenizer(bf.readLine());
                forbidden[i][0] = 1 << Integer.parseInt(st.nextToken())-1;
                forbidden[i][1] = 1 << Integer.parseInt(st.nextToken())-1;
            }
            count = 0; sum=0;
            dfs(0, 0);
            sb.append("#").append(test_case).append(" ").append(sum-count).append("\n");
        }
        System.out.println(sb);
    }
}
