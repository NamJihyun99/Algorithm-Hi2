import java.util.Scanner;

// 카드 구매하기(실버 1)
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n + 1];
		// 입력
		for(int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
		}
		int[] dp = new int[n + 1];
		dp[1] = arr[1];
        if(n == 1) {
			System.out.println(arr[1]);
			return;
		}
		for(int i = 2; i <= n; i++) {
			int now = arr[i];
			int max = 0;
			for(int j = 1; j <= i / 2; j++) {
				max = Math.max(max, dp[j] + dp[i - j]);
			}
			dp[i] = Math.max(max, now);
		}

		System.out.println(dp[n]);
	}
}
