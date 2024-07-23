//백준 1005번 ACM Craft(https://www.acmicpc.net/problem/1005)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			sb.append(solve()).append("\n");
		}
        System.out.print(sb);
    }
	
	private static int solve() throws Exception {
		List<List<Integer>> list = new ArrayList<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2)-> a1[1] - a2[1]);//{건물번호, 완료 시간}
		int N, K, W=0;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] times = new int[N];
		int[] dependCnt = new int[N];
			
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()) - 1;
			int Y = Integer.parseInt(st.nextToken()) - 1;
			
			list.get(X).add(Y);
			dependCnt[Y]++;
		}
		
		W = Integer.parseInt(br.readLine()) - 1;
		
		for(int i=0; i<N; i++) {
			if(dependCnt[i] == 0)
				pq.add(new int[] {i, times[i]});
		}
		
		int now = 0;
		while(!pq.isEmpty()) {

			int[] x = pq.poll();

			if(x[0] == W)
				return x[1];
			
			if(now < x[1])
				now = x[1];
			
			for(int y: list.get(x[0])) {
				dependCnt[y] -= 1;
				if(dependCnt[y] == 0)
					pq.add(new int[] {y, x[1] + times[y]});
			}
		}
		
		return 0;
	}
}
