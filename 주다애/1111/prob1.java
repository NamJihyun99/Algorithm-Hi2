package Algorithm.day_1111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 에디터
// 1. StringBuilder -> 시간 초과
// 2. LinkedList -> 시간 초과
public class BaekJoon1406 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int m = Integer.parseInt(br.readLine());
		Stack<String> left = new Stack<>();
		Stack<String> right = new Stack<>();
		for(char c : str.toCharArray()) left.push(c + "");
		for (int i = 0; i < m; i++) {
			String command = br.readLine();
			String[] temp = command.split(" ");
			if(temp.length > 1) {
				char t = temp[1].charAt(0);
				left.push(t + "");
			}
			else {
				if(command.equals("L")) {
					if(!left.isEmpty()) right.push(left.pop());
				}
				if(command.equals("D")) {
					if(!right.isEmpty()) left.push(right.pop());
				}
				if(command.equals("B")) {
					if(!left.isEmpty()) left.pop();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!left.isEmpty()) right.push(left.pop());
		while (!right.isEmpty()) sb.append(right.pop());
		System.out.println(sb);
	}
}
