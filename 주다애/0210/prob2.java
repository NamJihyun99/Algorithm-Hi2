import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수 나누기 게임(골드 4)
// 시간 복잡도
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		int[] score = new int[1000001];
		boolean[] existed = new boolean[1000001];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			existed[arr[i]] = true;
		}
		
		for (int i = 0; i < n; i++) {
			// 같은 숫자는 없으므로 arr[i] * 2부터 시작
			for (int j = arr[i] * 2; j < 1000001; j += arr[i]) {
				if (existed[j]) {
					score[arr[i]] += 1;
					score[j] -= 1;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(score[arr[i]]).append(" ");
		}
		System.out.println(sb);
	}
}
