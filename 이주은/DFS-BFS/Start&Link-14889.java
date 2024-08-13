//백준 14889번 스타트와 링크

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, sum;
	static int answer = Integer.MAX_VALUE;
	static int[][] skills;
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		skills = new int[N][N];
		isSelected = new boolean[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			
			for(int j=0; j<N; j++) {
				skills[i][j] = Integer.parseInt(st.nextToken());
				sum += skills[i][j];
			}
		}
		
		dfs(0, 0);
		
		System.out.println(answer);
	}
	
	private static void dfs(int idx, int cnt) {
		if(cnt == N/2) {
			calc();
			return;
		}
		if(idx == N || (idx == 2 && cnt == 0)) {
			return;
		}
		
		isSelected[idx] = true;
		dfs(idx+1, cnt+1);
		isSelected[idx] = false;
		
		dfs(idx+1, cnt);
	}
	
	private static void calc() {
		int teamS = 0;
		int teamL = 0;
		
		for(int i=0; i<N; i++) {
			if(isSelected[i])
				for(int j=0; j<N; j++) {
					if(isSelected[j])
						teamS += skills[i][j];
				}
			else
				for(int j=0; j<N; j++) {
					if(!isSelected[j])
						teamL += skills[i][j];
				}
		}
		
		
		answer = Math.min(answer, Math.abs(teamL - teamS));
	}
}
