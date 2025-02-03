import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 트리와 쿼리(골드 5)
public class Main {
	static int n;
	static int r;
	static int q;
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] size;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		size = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		Node root = new Node(r);
		// 트리 만들기
		makeTree(root, -1);
		// 서브 트리에 속하는 노드 개수 구하기
		getSubTreeNodes(root);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			sb.append(size[a]).append(" ");
		}
		System.out.println(sb);
	}

	private static void getSubTreeNodes(Node root) {
		size[root.num] = 1; // 나도 서브 트리에 포함
		for (Node child : root.child) {
			getSubTreeNodes(child);
			size[root.num] += size[child.num];
		}
	}

	private static void makeTree(Node now, int parent) {
		for (Integer child : graph.get(now.num)) {
			if (child == parent) continue;
			Node node = new Node(child);
			now.child.add(node);
			makeTree(node, now.num);
		}
	}

	static class Node {
		int num;
		List<Node> child;

		public Node(int num) {
			this.num = num;
			this.child = new ArrayList<>();
		}
	}
}
