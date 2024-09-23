import java.util.*;
import java.io.*;

// 백준 14391번 - 종이 조각

class Main {

    static int N, M, answer;
    static int[][] board;
    static boolean[][] visited;

    private static void dfs(int depth, int sum) {
        if (depth == N*M) {
            answer = Math.max(answer, sum);
            return;
        }
        int row = depth / M;
        int col = depth % M;
        if (visited[row][col]) {
            dfs(depth+1, sum);
        } else {
            visited[row][col] = true;
            int num = board[row][col];
            dfs(depth+1, sum+num);
            int i, temp = num;
            for (i=row+1; i<N; i++) {
                if (visited[i][col]) break;
                visited[i][col] = true;
                temp = temp*10+board[i][col];
                dfs(depth+1, sum+temp);
            }
            for (int j=row+1; j<i; j++) {
                visited[j][col] = false;
            }
            
            temp = num;
            for (i=col+1; i<M; i++) {
                if (visited[row][i]) break;
                visited[row][i] = true;
                temp = temp*10+board[row][i];
                dfs(depth+i-col+1, sum+temp);
            }
            for (int j=col+1; j<i; j++) {
                visited[row][j] = false;
            }
            visited[row][col] = false;
        }
    }

    private static void bitMasking() {
        // 0은 가로, 1은 세로
        for (int now=0; now<(1<<(N*M)); now++) {
            int sum = 0;
            for (int i=0; i<N; i++) {
                int num = 0;
                for (int j=0; j<M; j++) {
                    int k = i*M+j;
                    if ((1<<k&now)==0) {
                        num = num*10+board[i][j];
                    } else {
                        sum += num;
                        num = 0;
                    }
                }
                sum += num;
            }
            for (int j=0; j<M; j++) {
                int num=0;
                for (int i=0; i<N; i++) {
                    int k = i*M+j;
                    if ((1<<k&now)!=0) {
                        num = num*10+board[i][j];
                    } else {
                        sum += num;
                        num = 0;
                    }
                }
                sum += num;
            }
            answer = Math.max(answer, sum);
        }
    }
  
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i=0; i<N; i++) {
            String input = bf.readLine();
            for (int j=0; j<M; j++) {
                board[i][j] = input.charAt(j)-'0';
            }
        }
        
        answer = 0;
        dfs(0, 0);
        System.out.println(answer);
        
        answer = 0;
        bitMasking();
        System.out.println(answer);
    }
}
