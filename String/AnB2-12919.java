//백준 12919번 A와 B 2(https://www.acmicpc.net/problem/12919)

import java.util.*;
import java.io.*;

public class Main {
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		
		solve(S, T);
		
		System.out.println(answer);
	}
	
	public static void solve(String s, String t) {
		if(s.equals(t)) {
			answer = 1;
			return;
		}
		
		if(t.startsWith("B")) {
			StringBuilder sb = new StringBuilder(t);
			sb.reverse();
			sb.setLength(t.length()-1);
			
			solve(s, sb.toString());
		}
		if(t.endsWith("A")) {
			solve(s, t.substring(0, t.length()-1));
		}
			
	}
}
