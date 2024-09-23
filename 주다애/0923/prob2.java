import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 퇴사(실버 3)
public class BaekJoon14501 {
	static int[] t;
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		t = new int[n + 1];
		p = new int[n + 1];
		StringTokenizer st;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[n + 2];
		// i일에 상담 -> i + t[i]까지 상담 못함 / p[i] 수익 얻음 -> dp[i] = dp[i+t[i]] + p[i]
		// i일에 상담 X -> dp[i + 1]
		for(int i = n; i >= 0; i--) {
			// 상담 끝나는 다음날
			int next = i + t[i];
			// 다음날까지 상담이 가능하면
			if(next <= n + 1) {
				dp[i] = Math.max(dp[i + 1], dp[i + t[i]] + p[i]);
			}
			else {
				dp[i] = dp[i + 1];
			}
		}
		System.out.println(dp[0]);
	}
}
