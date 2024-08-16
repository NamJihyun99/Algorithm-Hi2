import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 거짓말(골드 4)
// union find
public class BaekJoon1043 {
	static List<List<Integer>> graph;
	static List<Integer> list;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		list = new ArrayList<>();
		parent = new int[n + 1];
		// 진실을 아는 사람들
		for (int i = 0; i < t; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		// 부모 배열 초기화
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		// 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < num; j++) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			graph.add(temp);
		}
		// 부모 업데이트
		for (List<Integer> sub : graph) {
			int p = sub.get(0);
			for (int c : sub) {
				if(p == c) continue;
				if(findParent(p) != findParent(c)) union(p, c);
			}
		}
		int cnt = 0;
		// 찾기
		for (List<Integer> sub : graph) {
			boolean flag = true;
			for (int l : sub) {
				// findParent 함수에 넘겨주어야 함(최상위 부모를 찾아야 하므로)
				if(list.contains(findParent(parent[l]))) {
					flag = false;
					break;
				}
			}
			if(flag) cnt++;
		}
		System.out.println(cnt);
	}

	private static void union(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		if(a != b) {
			// 진실을 아는 사람이 있으면 부모로 만들어 줌
			if(list.contains(b)) parent[a] = b;
			else parent[b] = a;
		}
	}

	static int findParent(int a) {
		if(a == parent[a]) {
			return a;
		}
		return findParent(parent[a]);
	}
}
