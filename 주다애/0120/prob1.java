import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// 정수 삼각형(실버 1)
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<List<Integer>> triangle = new ArrayList<>();
		List<List<Integer>> dp = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			triangle.add(new ArrayList<>());
			dp.add(new ArrayList<>());
		}
		// 입력
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				triangle.get(i).add(sc.nextInt());
			}
		}
		if (n == 1) {
			System.out.println(triangle.get(1).get(0));
			return;
		}
		dp.get(1).add(triangle.get(1).get(0));
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (j == 0) {
					dp.get(i).add(dp.get(i - 1).get(j) + triangle.get(i).get(j));
					continue;
				}
				if (j == i - 1) {
					dp.get(i).add(dp.get(i - 1).get(j - 1) + triangle.get(i).get(j));
					continue;
				}
				dp.get(i).add(Math.max(dp.get(i - 1).get(j - 1), dp.get(i - 1).get(j))  + triangle.get(i).get(j));
			}
		}
		Collections.sort(dp.get(n));
		System.out.println(dp.get(n).get(n - 1));
	}
}
