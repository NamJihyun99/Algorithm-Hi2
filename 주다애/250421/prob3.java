import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동전 뒤집기(골드 1)
// 메모리 초과 -> 최대 2^20개의 char 이차원 배열 때문
// 진짜 뒤집는 것이 아니라 행이 뒤집힐 때를 가정하고 구하기
public class BaekJoon1285 {
	static int n;
	static char[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		arr = new char[n][n];
		// 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for (int j = 0; j < n; j++) {
				arr[i][j] = (line.charAt(j));
			}
		}
		int answer = (int) Math.pow(n, 2);
		for (int bit = 0; bit < (1 << n); bit++) {
			int sum = 0;
			for (int j = 0; j < n; j++) {
				int cntT = 0;
				for (int i = 0; i < n; i++) {
					// i번째 열
					char now = arr[i][j];
					// i번째 행이 뒤집히면 -> T, H 바꿔주기
					if ((bit & (1 << i)) != 0) {
						now = (now == 'H') ? 'T' : 'H';
					}
					if (now == 'T') cntT += 1;
				}
				int cntH = n - cntT;
				if (cntH < cntT) sum += cntH;
				else sum += cntT;
			}
			answer = Math.min(answer, sum);
		}
		System.out.println(answer);
	}
}
