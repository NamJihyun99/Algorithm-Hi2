//백준 1522번 문자열 교환

import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		int A = 0, B = 0;
		int len = str.length();

		for (int i = 0; i < len; i++) {
			if (str.charAt(i) == 'a')
				A++;
			else
				B++;
		}

		if (A == len || A == 0) {
			System.out.println(0);
			return;
		}

		char stand;
		int win;
		if (A > B) {
			stand = 'a';
			win = A;
		} else {
			stand = 'b';
			win = B;
		}

		int cnt = 0; //다른 글자수 카운트
		int answer = 1000;

		Queue<Character> queue = new LinkedList<>();
		for (int i = len-win; i < len; i++) {
			if (str.charAt(i) != stand)
				cnt++;
			
			queue.add(str.charAt(i));
		}

		for (int i = 0; i < len; i++) {
			answer = Math.min(answer, cnt);

			if (queue.poll() != stand)
				cnt--;

			if (str.charAt(i) != stand)
				cnt++;
			
			queue.add(str.charAt(i));
		}

		System.out.println(answer);
	}
}
