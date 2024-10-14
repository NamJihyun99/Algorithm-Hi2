import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 겹치는 건 싫어(실버 1)
// 이중 for문 -> 시간 초과
// two pointer
// 1. 메모리 초과
public class BaekJoon20922 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[] num = new int[100001]; // 0~9까지 아님을 주의해라
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int l = 0;
		int r = 0;
		int cnt = 0;
		// 투 포인터
		while (l <= r) {
			if(r > n - 1) break;
			int rn = arr[r];
			if(num[rn] < k) {
				r += 1; // 오른쪽 포인터 한 칸 이동
				num[rn] += 1;
			}
			else if(num[rn] == k) {
				num[arr[l]] -= 1;
				l += 1;
			}
			cnt = Math.max(cnt, r - l);
		}
		System.out.println(cnt);
	}
}
