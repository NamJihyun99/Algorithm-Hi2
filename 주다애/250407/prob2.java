import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 랜선 자르기
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		long[] lan = new long[k];
		// 입력
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			lan[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(lan);

		long left = 1;
		long right = lan[lan.length - 1];

		while (left <= right) {
			long mid = (left + right) / 2;
			long cnt = 0;
			for (int i = 0; i < k; i++) {
				long m = lan[i] / mid;
				cnt += m;
			}
			if (cnt >= n) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		System.out.println(right);
	}
}
