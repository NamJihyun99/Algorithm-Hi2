import java.util.Scanner;
import java.util.Stack;

// 오큰수(골드 5)
// 1. 시간 초과 -> O(n^2)
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		// 입력
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
				arr[stack.peek()] = arr[i];
				stack.pop();
			}
			stack.add(i); // 인덱스 삽입
		}
		while (!stack.isEmpty()) {
			arr[stack.pop()] = -1;
		}
		for (int t : arr) {
			sb.append(t).append(" ");
		}
		System.out.println(sb);
	}
}
