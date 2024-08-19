import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 색종이 만들기(실버 2)
// 같은 색인가 -> 맞으면 끝, 틀리면 1/4해서 다시 탐색
// 1. StackOverflowError -> div 지역변수 선언
public class BaekJoon2630 {
	static int map[][];
	static int white = 0;
	static int blue = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		StringTokenizer st;
		// 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 같은 색인지 판단
		checkColor(0, 0, n);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void checkColor(int r, int c, int div) {
		if(quest(r, c, div)) {
			if(map[r][c] == 1) blue++;
			else white++;
		}
		else {
			div /= 2;
			checkColor(r, c, div); // 2사분면
			checkColor(r, c + div, div); // 1사분면
			checkColor(r + div, c + div, div); // 4사분면
			checkColor(r + div, c, div); // 3사분면
		}
	}

	// 나눠진 상태에서 판단
	private static boolean quest(int r, int c, int div) {
		int target = map[r][c]; // blue or white
		// 탐색
		for (int i = r; i < r + div; i++) {
			for (int j = c; j < c + div; j++) {
				if(map[i][j] != target) return false;
			}
		}
		return true;
	}
}
