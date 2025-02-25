import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 주식(실버 2)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (t --> 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(getBenefit(arr, n)).append("\n");
		}
		System.out.println(sb);
	}

	private static long getBenefit(int[] arr, int n) {
		long benefit = 0;
		int start = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			int now = arr[i];
			if (start > now) benefit += start - now;
			else start = now;
		}
		return benefit;
	}
}
