import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 수 묶기(골드 4)
public class Main {
	static int n;
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> pq2 = new PriorityQueue<>();
		// 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if (num > 0) pq.add(num);
			else pq2.add(num);
		}
		// pq.stream().sorted(Collections.reverseOrder());
		long ans = 0;
		long plus = 0;
		long minus = 0;
		if (pq.size() + pq2.size() == 1) {
			if (pq.size() == 0)
				System.out.println(pq2.poll());
			else
				System.out.println(pq.poll());
			return;
		}
		while (pq.size() > 1) {
			int a = pq.poll();
			int b = pq.poll();
			int t = Math.max(a + b, a * b);
			plus += t;
		}
		while (pq2.size() > 1) {
			int a = pq2.poll();
			int b = pq2.poll();
			int t = Math.max(a + b, a * b);
			minus += t;
		}
		if (!pq.isEmpty()) ans += pq.poll();
		if (!pq2.isEmpty()) ans += pq2.poll();
		ans += (plus + minus);
		System.out.println(ans);
	}
}
