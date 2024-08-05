//백준 15900번 나무탈출 (https://www.acmicpc.net/submit/15900/81532026)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, depthSum;
	static List<List<Integer>> tree = new ArrayList<>();
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];

		for (int i = 0; i < N; i++)
			tree.add(new ArrayList<>());

		StringTokenizer st;
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			tree.get(a).add(b);
			tree.get(b).add(a);
		}

		visited[0] = true;
		travelTree(0, 0);

		if (depthSum % 2 == 0)
			System.out.println("No");
		else
			System.out.println("Yes");
	}

	static void travelTree(int nodeNo, int depth) {
		boolean isLeaf = true;

		for (int to : tree.get(nodeNo)) {
			if (visited[to])
				continue;

			visited[to] = true;
			travelTree(to, depth + 1);
			isLeaf = false;
		}

		if (isLeaf)
			depthSum += depth;
	}

}
