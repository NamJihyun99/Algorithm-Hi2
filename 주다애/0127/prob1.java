import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// 서울 지하천 2호선(골드 3)
public class Main {
	static int n;
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static boolean flag;
	static List<Integer> cycle = new ArrayList<>();
	static int[] dist;
	static int cnt = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		dist = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		for (int i = 1; i <= n; i++) {
			visited = new boolean[n + 1];
			flag = false;
			hasCycle(i, i);
			if (flag) cycle.add(i);
		}
		bfs(1);
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= n; i++) {
			if (cycle.contains(i)) sb.append(0).append(" ");
			else sb.append(bfs(i)).append(" ");
		}
		System.out.println(sb);
	}

	private static int bfs(int sub) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{sub, 0});
		boolean[] checked = new boolean[n + 1];
		checked[sub] = true;
		int min = 3000;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (cycle.contains(now[0])) {
				min = now[1];
				break;
			}
			for (int c : graph.get(now[0])) {
				if (!checked[c]) {
					q.offer(new int[]{c, now[1] + 1});
					checked[c] = true;
				}
			}
		}
		return min;
	}

	private static void hasCycle(int sub, int before) {
		if (flag) return;
		visited[sub] = true;
		for (int next : graph.get(sub)) {
			if (!visited[next]) {
				hasCycle(next, sub);
			}
			else {
				if (sub == before) {
					flag = true;
					return;
				}
			}
		}
	}
}
