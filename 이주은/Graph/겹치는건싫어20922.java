import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] cnt = new int[100_001];
		int[] A = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0, end = 0;
		int len = 0, answer = 1;

		while (start < N) {
			int s = A[start];
			
			while (end <= start && cnt[s] >= K) {
				int e = A[end];
				
				cnt[e]--;
				len--;
				end++;
			}

			
			cnt[s]++;
			len++;
			start++;
			
			answer = Math.max(answer, len);
		}

		System.out.println(answer);
	}
}
