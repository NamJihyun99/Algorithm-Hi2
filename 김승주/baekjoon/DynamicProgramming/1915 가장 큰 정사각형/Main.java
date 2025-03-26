import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] arr = new char[n][m];
        int[][] dp = new int[n][m];
        List<int[]> oneLoc = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = input.charAt(j);
                if (arr[i][j] == '1') {
                    dp[i][j] = 1;
                    oneLoc.add(new int[] {i, j});
                }
            }
        }

        if (oneLoc.isEmpty()) {
            System.out.println(0);
            return;
        }

        if (oneLoc.size() == n * m) {
            if (n > m) {
                System.out.println(m * m);
            } else {
                System.out.println(n * n);
            }
            return;
        }

        int answer = 1;

        for (int[] loc : oneLoc) {
            int length = dp[loc[0]][loc[1]] + 1;
            while (true) {
                if (loc[0] + length - 1 >= n || loc[1] + length - 1 >= m) {
                    break;
                }
                boolean flag = false;
                
                for (int j = loc[1], width = 1; width < length; j++, width++) {
                    if (arr[loc[0] + length - 1][j] == '0') {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }

                for (int j = loc[0], width = 1; width < length; j++, width++) {
                    if (arr[j][loc[1] + length - 1] == '0') {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }

                length++;
            }

            answer = Math.max(answer, --length);

            for (int i = loc[0]; length - i > 0; i++) {
                for (int j = loc[1]; length - j > 0; j++) {
                    dp[i][j] = Math.min(loc[0] + length - i, loc[1] + length - j);
                }
            }
        }

        System.out.println(answer * answer);
    }
}