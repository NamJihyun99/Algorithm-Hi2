//백준 24993번 탑 (https://www.acmicpc.net/problem/2493)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] towers;
	static int[] answer;
	static Deque<Integer> stack = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		getInput();

		for(int i=N-1; i>=0; i--) {
			while(!stack.isEmpty() && towers[i] >= towers[stack.peekLast()]) {
				answer[stack.pollLast()] = i;
			}
			stack.addLast(i);
		}
		
		for(int i=0; i<N; i++) {
			sb.append(answer[i] + 1).append(" ");
		}
		System.out.println(sb);
	}
	
	public static void getInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		towers = new int[N];
		answer = new int[N];
		
		Arrays.fill(answer, -1);
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			towers[i] = Integer.parseInt(st.nextToken());
		}
	}
}
