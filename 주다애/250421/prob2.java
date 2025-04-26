import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 후위 표기식(골드 2)
// 연산자는 넣고 피연산자는 바로바로 결과에 넣어라
public class BaekJoon1918 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();

		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);

			switch (c) {
				case '+':
				case '-':
				case '*':
				case '/':
					while (!stack.isEmpty() && getPriority(c) <= getPriority(stack.peek())) {
						sb.append(stack.pop());
					}
					stack.add(c);
					break;
				case '(':
					stack.add(c);
					break;
				case ')':
					while (!stack.isEmpty() && stack.peek() != '(') {
						sb.append(stack.pop());
					}
					stack.pop(); // '(' 빼주기
					break;
				default:
					sb.append(c); // 피연산자는 바로 추가
			}
		}
		// 남은 연산자 추가
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}

	static int getPriority(char op) {
		if(op =='(' || op ==')'){
			return 0;
		} else if (op == '+' || op == '-') {
			return 1;
		} else if (op == '*' || op == '/') {
			return 2;
		}
		return -1;
	}
}
