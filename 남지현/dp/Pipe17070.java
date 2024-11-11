import java.util.*;
import java.io.*;

// 백준 17070번 파이프 옮기기

class Main {
  
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][][] sum = new int[N+1][N+1][3];     // 가로 0, 세로 1, 대각선 2
        boolean[][] isWall = new boolean[N+1][N+1];
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                isWall[i][j] = Integer.parseInt(st.nextToken())==1;
            }
        }
        sum[1][2][0] = 1;
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if ((i==1 && j==1) || (i==1 && j==2)) continue;
                if (!isWall[i][j-1] && !isWall[i][j]){
                    sum[i][j][0] = sum[i][j-1][0] + sum[i][j-1][2];
                }
                if (!isWall[i-1][j] && !isWall[i][j]){
                    sum[i][j][1] = sum[i-1][j][1] + sum[i-1][j][2];
                }
                if (!isWall[i-1][j-1] && !isWall[i-1][j] && !isWall[i][j-1] && !isWall[i][j]){
                    sum[i][j][2] = sum[i-1][j-1][0] + sum[i-1][j-1][1] + sum[i-1][j-1][2];
                }
            }
        }
        System.out.println(sum[N][N][0] + sum[N][N][1] + sum[N][N][2]);
    }
}
