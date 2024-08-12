import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사이클 게임(골드 4)
// union find
public class BaekJoon20040 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		int res = 0;
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(findParent(a) != findParent(b)) {
				setParent(a, b);
			}
			else {
				res = i;
				break;
			}
		}
		System.out.println(res);
	}

	private static void setParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		if(a != b) {
			parent[b] = a;
		}
	}

	private static int findParent(int a) {
		if(parent[a] == a) {
			return a;
		}
		return findParent(parent[a]);
	}
}
