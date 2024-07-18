import java.io.*;

class Main {
    static char[] ciphertext;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ciphertext = br.readLine().toCharArray();
        dp = new long[ciphertext.length][ciphertext.length];
        System.out.println(findPlaintexts(0, ciphertext.length - 1) % 1000000);
    }

    private static long findPlaintexts(int start, int end) {
        if (start >= ciphertext.length) return 1; // 암호문의 끝까지 모두 확인한 경우

        if (dp[start][end] == 0) {
            if (ciphertext[start] == '0') { // 맨 앞이 0으로 시작하는 경우는 대응되는 알파벳이 없음. -> 존재하지 않는 경우이므로 0 반환.
                dp[start][end] = 0;
            } else if (start == end) { // 변환해야 할 암호문이 1자만 남았고, 0이 아닌 경우 해당하는 하나의 알파벳에 대응시키고 재귀 호출 종료.
                dp[start][end] = 1;
            
            // 변환해야 할 암호문이 2자 이상인 경우
            } else if (ciphertext[start + 1] == '0') { // ?0인 경우
                if ((ciphertext[start] - '0') * 10 + (ciphertext[start + 1] - '0') > 26) {
                    dp[start][end] = 0; // ?가 3 이상인 경우 26을 초과하므로 대응되는 알파벳이 없음.
                } else {
                    dp[start][end] = findPlaintexts(start + 2, end) % 1000000; // ?가 1 또는 2인 경우 뒤에 있는 0까지(10, 20)를 하나의 알파벳으로 대응해야 함.
                }
            } else { // 맨 앞 두 자리 ?!가 둘 다 0이 아닌 경우
                if ((ciphertext[start] - '0') * 10 + (ciphertext[start + 1] - '0') > 26) { // ?!가 26을 초과하면 전체가 하나의 알파벳에 대응될 수 없음.
                    dp[start][end] = findPlaintexts(start + 1, end) % 1000000; // ? 한 자리만 알파벳에 대응하고 나머지 암호문에 대해 재귀 호출.
                } else { // ?!가 26을 초과하지 않으면 전체가 하나의 알파벳에 대응될 수도, ?와 !가 각각 하나의 알파벳에 대응될 수도 있음.
                    dp[start][end] = (findPlaintexts(start + 1, end) % 1000000) + (findPlaintexts(start + 2, end) % 1000000);
                }
            }
        }
        return dp[start][end];        
    }

    private static long dfs(int start, int end) {
        if (start >= ciphertext.length) return 1;
        if (ciphertext[start] == '0')   return 0;
        if (start == end)   return 1;

        if (ciphertext[start + 1] == '0') {
            if ((ciphertext[start] - '0') * 10 + (ciphertext[start + 1] - '0') > 26) {
                return 0;
            } else {
                return dfs(start + 2, end) % 1000000;
            }
        } else {
            if ((ciphertext[start] - '0') * 10 + (ciphertext[start + 1] - '0') > 26) {
                return dfs(start + 1, end) % 1000000;
            } else {
                return dfs(start + 1, end) % 1000000 + dfs(start + 2, end) % 1000000;
            }
        }
    }
}