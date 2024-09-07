import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가장 긴 바이토닉 부분 수열
public class Main {
	static int[] arr;
	static int n;
	static int[] lis_dp;
	static int[] lds_dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n];
		lis_dp = new int[n];
		lds_dp = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		lis();
		lds();
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, lis_dp[i] + lds_dp[i]);
		}
		System.out.println(max - 1);
	}

	// 오름차순
	private static void lis() {
		for (int i = 0; i < n; i++) {
			lis_dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && lis_dp[i] < lis_dp[j] + 1) {
					lis_dp[i] = lis_dp[j] + 1;
				}
			}
		}
	}

	// 내림차순
	private static void lds() {
		for (int i = n - 1; i >= 0; i--) {
			lds_dp[i] = 1;
			for (int j = n - 1; j > i; j--) {
				if (arr[j] < arr[i] && lds_dp[i] < lds_dp[j] + 1) {
					lds_dp[i] = lds_dp[j] + 1;
				}
			}
		}
	}
}
