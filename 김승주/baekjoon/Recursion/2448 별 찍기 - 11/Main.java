import java.io.*;
import java.util.Arrays;

class Main {
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new char[N][N / 3 * 6 - 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(arr[i], ' ');
        }

        addStars(0, N - 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N / 3 * 6 - 1; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void addStars(int row, int col, int lines) {
        if (lines == 3) {
            arr[row][col] = '*';
            arr[row + 1][col - 1] = '*';
            arr[row + 1][col + 1] = '*';
            for (int i = col - 2; i <= col + 2; i++) {
                arr[row + 2][i] = '*';
            }
            return;
        }

        addStars(row, col, lines / 2);
        addStars(row + lines / 2, col - lines / 2, lines / 2);
        addStars(row + lines / 2, col + lines / 2, lines / 2);   
    }
}