import java.util.Arrays;
import java.util.Scanner;

// 별 찍기 - 11
public class Main {
	static char[][] stars;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		stars = new char[N][N * 2 - 1];
		for (int i = 0; i < N; i++) {
			Arrays.fill(stars[i], ' ');
		}
		
		run(0, N-1, N);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2 * N - 1; j++) {
				sb.append(stars[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static void run(int r, int c, int N) {
		if (N == 3) {
			stars[r][c] = '*';
			stars[r + 1][c - 1] = stars[r + 1][c + 1] = '*';
			stars[r + 2][c - 2] = stars[r + 2][c - 1] = stars[r + 2][c] = stars[r + 2][c + 1] = stars[r + 2][c + 2] = '*';
			return;
		} else {
			int cut = N / 2;
			run(r, c, cut);
			run(r + cut, c - cut, cut);
			run(r + cut, c + cut, cut); 
		}
	}
}
