import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 작업(골드 4)
// 내 +바로+ 앞의 선행 작업만 신경 쓰면 된다!
public class BaekJoon2056 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] degree = new int[n + 1];
		int[] work = new int[n + 1];
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
		// 입력
		StringTokenizer st;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			work[i] = time;
			int before = Integer.parseInt(st.nextToken());
			for (int j = 0; j < before; j++) {
				int t = Integer.parseInt(st.nextToken());
				list.get(t).add(i);
				degree[i]++;
			}
		}

		Queue<Integer> q = new LinkedList<>();
		int[] cost = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			if(degree[i] == 0) {
				q.offer(i);
				cost[i] = work[i];
			}
		}

		while (!q.isEmpty()) {
			int t = q.poll();
			for (int c : list.get(t)) {
				cost[c] = Math.max(cost[c], work[c] + cost[t]);
				degree[c]--;
				if(degree[c] == 0) q.offer(c);
			}
		}

		int maxCost = 0;
		for (int i = 1; i <= n; i++) {
			maxCost = Math.max(maxCost, cost[i]);
		}
		System.out.println(maxCost);
	}
}
