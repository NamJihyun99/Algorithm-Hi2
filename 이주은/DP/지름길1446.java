import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static final class Road {
		int start, end, cost;
		
		private Road(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Road> pq = new PriorityQueue<>((r1, r2) -> r1.end - r2.end);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			pq.add(new Road(start, end, cost));
		}
		
		
		int[] dp = new int[D+1];
		
		for(int i=1; i<=D; i++) {
			dp[i] = dp[i-1]+1;
			
			while(!pq.isEmpty() && pq.peek().end == i) {
				Road road = pq.poll();
				int cost = dp[road.start] + road.cost;
				
				dp[i] = Math.min(dp[i], cost);
			}
		}
		
		System.out.println(dp[D]);
	}

}
