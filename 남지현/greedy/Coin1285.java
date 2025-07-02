import java.util.*;
import java.io.*;

// 백준 1285번 동전 뒤집기
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] coins = new char[N][N];
        for (int i=0; i<N; i++) {
            coins[i] = br.readLine().toCharArray();
        }
        int answer = 401;
        for (int t=0; t<(1<<N); t++) {
            int sum = 0;
            for (int j=0; j<N; j++) {
                int count = 0;
                for (int i=0; i<N; i++) {
                    char ch = coins[i][j];
                    if ((t & (1<<i)) > 0) {
                        ch = (ch=='T') ? 'H' : 'T';
                    }
                    if (ch == 'T') count++;
                }
                sum += Math.min(count, N-count);
            }
            answer = Math.min(answer, sum);
        }
        System.out.println(answer);
    }
}
