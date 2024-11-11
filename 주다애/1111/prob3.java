package Algorithm.day_1111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 흙길 보수하기
// 정렬 + 우선순위큐 + 그리디
public class BaekJoon1911 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		PriorityQueue<Point> pq = new PriorityQueue();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.add(new Point(s, e));
		}
		int total = 0;
		int start = 0;

		while(!pq.isEmpty()) {
			Point p = pq.poll();

			if(start >= p.end) continue; // 이미 이전 널빤지가 커버함

			start = Math.max(start, p.start);
			int diff = p.end - start;
			int count = diff % l == 0 ? diff / l : (diff / l) + 1;
			total += count;
			start += l * count;
		}
		System.out.println(total);
	}

	static class Point implements Comparable<Point> {
		int start;
		int end;

		public Point(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Point o) {
			return this.start - o.start;
		}
	}
}
