import java.util.*;
import java.io.*;

class Main {
    static int N, M, L;
    static int[][] paper;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = 1 << N*M;

        paper = new int[N][M];

        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<M; j++) {
                paper[i][j] = line.charAt(j) - '0';
            }
        }

        int max = 0;
        for(int bit=0; bit<L; bit++) {
            int sum = 0;

            //가로
            for(int i=0; i<N; i++) {
                int temp = 0;
                for(int j=0; j<M; j++) {
                    if((bit&(1<<(i*M + j))) > 0) {
                        temp = temp*10 + paper[i][j];
                    }
                    else {
                        sum += temp;
                        temp = 0;
                    }
                }

                sum += temp;
            }
            
            //세로
            for(int j=0; j<M; j++) {
                int temp = 0;
                for(int i=0; i<N; i++) {
                    if((bit & (1<<(i*M + j))) == 0) {
                        temp = temp*10 + paper[i][j];
                    }
                    else {
                        sum += temp;
                        temp = 0;
                    }
                }
                sum += temp;
            }
            
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
