import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 두 배열의 합(골드 3)
// 부분합 -> 투 포인터 / 이진 탐색
// 시간 복잡도?
public class BaekJoon2143 {
	static int T, n, m;
	static int[] a;
	static int[] b;
	static List<Integer> aList = new ArrayList<>();
	static List<Integer> bList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		a = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		b = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) b[i] = Integer.parseInt(st.nextToken());

		// 부분 합 구하기
		aList = partialSum(a);
		bList = partialSum(b);

		// 정렬
		Collections.sort(aList);
		Collections.sort(bList);

		 // 투 포인터 탐색
		long ans = twoPointer();
		System.out.println(ans);
	}

	private static long twoPointer() {
		int left = 0;
		int right = bList.size() - 1;
		long cnt = 0;

		// 투 포인터 탐색
		while (left < aList.size() && right >= 0) {
			long aNum = aList.get(left);
			long bNum = bList.get(right);
			long sum = aNum + bNum;
			if (sum == T) {
				long sameA = 0;
				long sameB = 0;
				while (left < aList.size() && aList.get(left) == aNum) {
					sameA += 1;
					left += 1;
				}
				while (right >= 0 && bList.get(right) == bNum) {
					sameB += 1;
					right -= 1;
				}
				cnt += (sameA * sameB);
			}
			else if (sum < T) {
				left += 1;
			}
			else if (sum > T) {
				right -= 1;
			}
		}
		return cnt;
	}

	static List<Integer> partialSum(int[] arr){
		List<Integer> list = new ArrayList<>();
		int len = arr.length;
		for(int i = 0; i < len; i++){
			int sum = 0;
			for(int j = i; j >= 0; j--){
				sum += arr[j];
				list.add(sum);
			}
		}
		return list;
	}
}
