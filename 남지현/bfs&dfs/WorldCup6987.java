import java.util.*;
import java.io.*;

// 백준 6987번 월드컵
class Main {

    static final int T = 4;
    static final int TEAM = 6;

    static int[][] result;
    static int answer;

    private static void dfs(int now, int count, int gap) {
        if (answer == 1) return;
        if (count == 15) {
            answer = 1;
            return;
        }

        if (now+gap == TEAM) {
            now++;
            gap = 1;
        }

        int next = now+gap;
        if (result[now][0]>0 && result[next][2]>0) {
            // now가 이기는 경우
            result[now][0] -= 1;
            result[next][2] -= 1;
            dfs(now, count+1, gap+1);
            result[now][0] += 1;
            result[next][2] += 1;
        }
        if (result[now][1]>0 && result[next][1]>0) {
            // 비기는 경우
            result[now][1] -= 1;
            result[next][1] -= 1;
            dfs(now, count+1, gap+1);
            result[now][1] += 1;
            result[next][1] += 1;
        }
        if (result[now][2]>0 && result[next][0]>0) {
            // next가 이기는 경우
            result[now][2] -= 1;
            result[next][0] -= 1;
            dfs(now, count+1, gap+1);
            result[now][2] += 1;
            result[next][0] += 1;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t=1; t<=T; t++) {
            result = new int[TEAM][3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int win=0; int lose=0; int tie=0;
            for (int i=0; i<TEAM; i++) {
                for (int j=0; j<3; j++) {
                    result[i][j] = Integer.parseInt(st.nextToken());
                    if (j==0) win += result[i][j];
                    else if (j==1) tie += result[i][j];
                    else lose += result[i][j];
                }
            }
            answer = 0;
            if (win==lose && win+lose+tie==30) {
                dfs(0, 0, 1);
            }
            sb.append(answer).append(" ");
        }
        System.out.println(sb);
    }
}
