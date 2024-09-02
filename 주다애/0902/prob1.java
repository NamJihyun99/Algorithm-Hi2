import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분 합(골드 4)
// 1. 71%에서 실패
// 2. 75%에서 실패 -> 중간중간 return
public class BaekJoon1806 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n + 1];
		int[] sum = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		// 입력 및 누적 합
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] >= s) {
				System.out.println(1);
				return;
			}
			sum[i] = sum[i - 1] + arr[i];
		}
		if (sum[n] < s) {
			System.out.println(0);
			return;
		}
		// 5 6 9 14 24 31 35 44 46 54
		int min = n + 1; // 디폴트 값
		int left = 0;
		int right = 1;
		while (right <= n && left < right) {
			int cur_v = sum[right] - sum[left];
			if (cur_v == s) {
				min = Math.min(min, right - left);
				right += 1;
			}
			else if (cur_v < s) {
				right += 1;
			}
			else {
				min = Math.min(min, right - left);
				left += 1;
			}
		}
		System.out.println(min == n + 1 ? 0 : min);
	}
}
