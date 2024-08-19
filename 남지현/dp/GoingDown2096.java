import java.util.*;
import java.io.*;

// 백준 2096 - 내려가기

class Main {
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[][] min = new int[N][3];
        int[][] max = new int[N][3];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i=0; i<3; i++) {
            int num = Integer.parseInt(st.nextToken());
            min[0][i] = num;
            max[0][i] = num;
        }
        for (int i=1; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j=0; j<3; j++) {
                min[i][j] = Integer.MAX_VALUE;
                int num = Integer.parseInt(st.nextToken());
                if (j-1>=0) {
                    min[i][j] = Math.min(min[i][j], min[i-1][j-1]+num);
                    max[i][j] = Math.max(max[i][j], max[i-1][j-1]+num);
                }
                min[i][j] = Math.min(min[i][j], min[i-1][j]+num);
                max[i][j] = Math.max(max[i][j], max[i-1][j]+num);
                if (j+1<3) {
                    min[i][j] = Math.min(min[i][j], min[i-1][j+1]+num);
                    max[i][j] = Math.max(max[i][j], max[i-1][j+1]+num);
                }
            }
        }
        int[] answer = new int[2];
        answer[1] = Integer.MAX_VALUE;
        for (int i=0; i<3; i++) {
            answer[0] = Math.max(answer[0], max[N-1][i]);
            answer[1] = Math.min(answer[1], min[N-1][i]);
        }
        StringBuilder sb = new StringBuilder().append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb);
    }
}
