import java.io.*;
import java.util.*;

class Main {
    static int[] input = new int[100002];
    static int[][][] dp = new int[5][5][100002];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; ; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            if (input[i] == 0) {
                break;
            }
        }
        System.out.println(ddr(0, 0, 1));
    }
    private static int ddr(int left, int right, int cnt) {
        if (input[cnt] == 0) {
            return 0;
        }

        if (dp[left][right][cnt] == 0) {
            dp[left][right][cnt] = Math.min(energy(left, input[cnt]) + ddr(input[cnt], right, cnt + 1), 
            energy(right, input[cnt]) + ddr(left, input[cnt], cnt + 1));
        }

        return dp[left][right][cnt];
    }

    private static int energy(int pos, int des) {
		int num = Math.abs(pos - des);
		if (pos == 0) return 2;
		else if (num == 0) return 1;
		else if (num == 1 || num == 3) return 3;
		else return 4;
	}
}