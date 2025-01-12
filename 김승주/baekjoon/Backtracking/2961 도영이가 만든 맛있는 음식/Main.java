import java.io.*;

class Main {
    static int[][] taste;
    static int minGap = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        taste = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            taste[i][0] = Integer.parseInt(input[0]);
            taste[i][1] = Integer.parseInt(input[1]);
        }        
        backtracking(0, 0, 1, 0);
        System.out.println(minGap);
    }

    private static void backtracking(int selection, int depth, int sourness, int bitterness) {
        if (depth == taste.length && selection > 0) {
            minGap = Math.min(minGap, Math.abs(sourness - bitterness));
        }
        for (int i = depth; i < taste.length; i++) {
            // 재료를 선택
            selection |= (1 << i);
            backtracking(selection, i + 1, sourness * taste[i][0], bitterness + taste[i][1]);
            // 재료를 선택하지 않음
            selection &= ~(1 << i);
            backtracking(selection, i + 1, sourness, bitterness);
        }
    }
}