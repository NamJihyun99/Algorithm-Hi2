import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 랜선 자르기(실버 2)
// 이분탐색
// 1. 시간초과
/*
	2-1. min은 1에서 시작해야함
*/
 /*
 	2-2. 최소 길이를 찾는 것이므로 cnt == n일 때 바로 return X
 	if(cnt >= k) 로 if문 만들어서 다시 탐색할 수 있으면 탐색해야 함
 */
public class BaekJoon1654 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		long[] lan = new long[k];
		// 입력
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			lan[i] = Long.parseLong(st.nextToken());
		}
		long min = 1;
		long res = 0;
		long max = Arrays.stream(lan).max().getAsLong();
		while (min <= max) {
			long mid = (min + max) / 2;
			long cnt = 0; // 개수
			for (int i = 0; i < k; i++) {
				cnt += (lan[i] / mid);
			}
			if (cnt >= n) {
				min = mid + 1;
				res = mid;
			}
			else {
				max = mid - 1;
			}
		}
		System.out.println(res);
	}
}
