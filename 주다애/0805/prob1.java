import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 옥상 정원 꾸미기(골드 5)
public class BaekJoon6198 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		long sum = 0;
		for (int i = 0; i < n; i++) {
			int b = Integer.parseInt(br.readLine());
			while (!stack.isEmpty()) {
				if(stack.peek() <= b) {
					stack.pop();
				}
				else break;
			}
			sum += stack.size();
			stack.add(b);
		}
		System.out.println(sum);
	}
}
