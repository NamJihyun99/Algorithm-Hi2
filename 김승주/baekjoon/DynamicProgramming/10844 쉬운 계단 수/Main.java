import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static long[][] cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        cache = new long[10][N + 1]; // cache[a][b]: a로 시작하는 b자릿수의 개수
        long result = 0;

        for (int i = 0; i <= 9; i++) {
            cache[i][1] = 1;
        }

        for (int i = 1; i < 10; i++) {
            result += (dp(i, N) % 1000000000);
            result %= 1000000000;
        }

        System.out.println(result);
    }

    private static long dp(int start, int length) {
        if (length <= 0)    return 0;

        if (cache[start][length] == 0) {
            if (start + 1 < 10) {
                cache[start][length] += (dp(start + 1, length - 1) % 1000000000);
            }
            if (start - 1 > -1) {
                cache[start][length] += (dp(start - 1, length - 1) % 1000000000);
            }
        } 
        return cache[start][length];
    }
}