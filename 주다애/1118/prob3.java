import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 체인(실버 1)
// 그리디
// 문제가 잘 이해가 안가요..
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 기본적 정수는 오름차순 정렬
//		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		List<Integer> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		int res = 0;
		Collections.sort(list);
		while(list.size() > 1) {
			res += 1;
			list.set(0, list.get(0) - 1); // 가장 짧은 체인 길이 -1
			list.remove(list.size() - 1);
			if(list.get(0) == 0) list.remove(0); // 가장 짧은 체인 제거 -> 그 다음 짧은 체인으로 고리 만들기
		}
		System.out.println(res);
	}
}
