import java.util.Scanner;

// 도영이가 만든 맛있는 음식(실버 2)
// O(N * 2^N)
public class BaekJoon2961 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] ingredients = new int[2][n];
		for (int i = 0; i < n; i++) {
			ingredients[0][i] = sc.nextInt();
			ingredients[1][i] = sc.nextInt();
		}
		int ans = Integer.MAX_VALUE;
		for (int ing = 1; ing < (1 << n); ing++) {
			int sour = 1;
			int bitter = 0;
			for (int i = 0; i < n; i++) {
				if ((ing & (1 << i)) != 0) {
					System.out.print((1 << i) + " ");
					sour *= ingredients[0][i];
					bitter += ingredients[1][i];
				}
			}
			System.out.println();
			int diff = Math.abs(sour - bitter);
			ans = Math.min(ans, diff);
		}
		System.out.println(ans);
	}
}
