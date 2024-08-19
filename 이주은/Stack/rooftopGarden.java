//백준 6189번 옥상정원꾸미기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static int N;
	static int[] h;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		h = new int[N];

		for (int i = 0; i < N; i++) {
			h[i] = Integer.parseInt(br.readLine());
		}

		long answer = 0;
		Deque<int[]> stack = new LinkedList<>();
		stack.addLast(new int[] {h[N-1], 1});
		
		for (int i = N - 2; i >= 0; i--) {
			int cnt = 0;

			while (!stack.isEmpty() && stack.peekLast()[0] < h[i]) {
				cnt+= stack.pollLast()[1];
			}
			answer += cnt;

			stack.addLast(new int[] {h[i], cnt+1});

		}

		System.out.println(answer);
	}

}
