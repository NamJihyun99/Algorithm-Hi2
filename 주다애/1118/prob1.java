package test;

import java.util.*;
import java.io.FileInputStream;
public class Swea11315 {
	static int[][] dir = {{0,1}, {1,0}, {1,1}, {1,-1}};
	static int n;
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("src/test/sample1.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			n = sc.nextInt();
			sc.nextLine();
			char[][] map = new char[n][n];
			for(int i = 0; i < n; i++) {
				String[] line = sc.nextLine().split("");
				for(int j = 0; j < n; j++) {
					map[i][j] = line[j].charAt(0);
				}
			}
			String res = solve(map);
			System.out.println("#" + test_case + " " + res);
		}
	}
	
	static String solve(char[][] map) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 'o') {
					for(int k = 0; k < 4; k++) {
						boolean f = run(i, j, map, k);
						if(f) return "YES";
					}
				}
			}
		}
		return "NO";
	}
	
	static boolean run(int x, int y, char[][] map, int d) {
		for(int i = 0; i < 4; i++) {
			x += dir[d][0];
			y +=  dir[d][1];
			if(!canMove(x, y, n)) return false;
			if(map[x][y] != 'o') return false;
		}
		return true;
	}
	
	static boolean canMove(int x, int y, int n) {
		if(x < 0 || x >= n || y < 0 || y >= n) return false;
		return true;
	}
}
