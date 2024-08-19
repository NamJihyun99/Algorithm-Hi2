//백준 20040번 사이클 게임

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		
		for(int i=0; i<N; i++) {
			parents[i] = i;
		}
		
		for(int t=1; t<=M; t++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(findParent(a) == findParent(b)) {
				System.out.println(t);
				return;
			}
			
			int aP = findParent(a);
			int bP = findParent(b);
			
			parents[bP] = aP;
		}
		
		System.out.println(0);
	}
	
	private static int findParent(int p) {
		if(p == parents[p])
			return p;

		return findParent(parents[p]);	
	}
}
