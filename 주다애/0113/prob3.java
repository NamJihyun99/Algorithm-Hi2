import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// 숫자 고르기(2668)
// 탐색 + 사이클 찾기
public class BaekJoon2668 {
	static int n;
	static int[] arr;
	static List<Integer> ans = new ArrayList<>();
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
		}
		visited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			visited[i] = true;
			dfs(i, i);
			visited[i] = false;
		}
		Collections.sort(ans);
		ans.add(0, ans.size());
		for (int a : ans)
			System.out.println(a);
	}

	// 1(start. 출발 숫자) -> 3 -> 3 -> 1(출발 숫자)
	private static void dfs(int start, int value) {
		if (!visited[arr[start]]) {
			visited[arr[start]] = true;
			dfs(arr[start], value);
			visited[arr[start]] = false;
		}
		if (arr[start] == value) ans.add(value);
	}
}
