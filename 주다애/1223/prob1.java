import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// 예산(실버 1)
// 이분탐색
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Integer> cost = new ArrayList<>();
		long sum = 0;
		for (int i = 0; i < n; i++) {
			int c = sc.nextInt();
			cost.add(c);
			sum += c;
		}
		Collections.sort(cost);
		long m = sc.nextLong();

		// 모두 배정
		if(sum <= m) {
			System.out.println(cost.get(n - 1));
			return;
		}

		int l = 0;
		int r = cost.get(n - 1);
		int ans = 0;
		while(l <= r) {
			long s = 0;
			int mid = (l + r) / 2;
			for(int c : cost) {
				if(c <= mid) {
					s += c;
				}
				else s += mid;
			}
			// System.out.println(mid + " " + s + " " + l + " " + r);
			if(s > m) {
				r = mid - 1;
				ans = r;
			}
			else {
				l = mid + 1;
			}
		}
		System.out.println(ans);
	}
}
