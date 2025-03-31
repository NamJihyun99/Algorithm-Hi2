import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 공유기 설치(골드 4)
public class BaekJoon2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		long left = 1;
		long right = arr[n - 1];
		while (left <= right) {
			long mid = (left + right) / 2;
			int cnt = 1;
			int now = 0;
			for (int i = 1; i < n; i++) {
				if (arr[i] - arr[now] >= mid) {
					now = i;
					cnt++;
				}
			}
			// 설치된 수가 가진 공유기 개수보다 적을 때
			if (cnt < c) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		System.out.println(left - 1);
	}
}
