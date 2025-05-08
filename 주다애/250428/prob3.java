import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이진 검색 트리(골드 4)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int root = Integer.parseInt(st.nextToken());
		Node node = new Node(root);
		while (true) {
			String num = br.readLine();
			if (num == null || num.equals("")) break;
			// 이진 탐색 트리 만들기
			node.insert(Integer.parseInt(num));
		}
		// 후위 연산 : 왼쪽 -> 오른쪽 -> 루트
		postOrder(node);
	}

	private static void postOrder(Node node) {
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.num);
	}

	static class Node {
		int num;
		Node left;
		Node right;

		public Node(int num) {
			this.num = num;
		}

		public void insert(int num) {
			if (num < this.num) {
				if (this.left == null) {
					this.left = new Node(num);
				}
				else this.left.insert(num);
			}
			else {
				if (this.right == null) {
					this.right = new Node(num);
				}
				else {
					this.right.insert(num);
				}
			}
		}
	}
}
