import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 버블 소트(플래티넘 5)
// 원래는 O(N^2)
// 합병 정렬 구현
public class Main {
	static int n;
	static int[] arr;
	static int[] sorted;
	static long swap = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		sorted = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		divide(0, n - 1);
		System.out.println(swap);
	}

	private static void divide(int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;

			divide(left, mid);
			divide(mid + 1, right);
			merge(left, mid, right);
		}
	}

	private static void merge(int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int k = left;
		while (i <= mid && j <= right) {
			// 여기서 swap 일어남
			if (arr[i] > arr[j]) {
				swap += (mid - i + 1); // 왜??
				sorted[k++] = arr[j++];
			}
			// 안일어남
			else {
				sorted[k++] = arr[i++];
			}
		}

		while (i <= mid) {
			sorted[k++] = arr[i++];
		}
		while (j <= right) {
			sorted[k++] = arr[j++];
		}
		// for (int t = 0; t < (k - left); t++) {
		// 	arr[t + left] = sorted[t];
		// }
		for (int t = left; t < right + 1; t++) {
			arr[t] = sorted[t];
		}
	}
}
