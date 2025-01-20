import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon2470 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int[] answer = new int[2];
		answer[0] = 1000000000;
		answer[1] = 1000000000;
		int left = 0;
		int right = n - 1;
		while (left < right) {
			int temp = arr[left] + arr[right];
			if (Math.abs(temp) < Math.abs(answer[0] + answer[1])) {
				answer[0] = arr[left];
				answer[1] = arr[right];
				if (temp == 0) break;
			}
			if (temp > 0) right -= 1;
			else left += 1;
		}
		System.out.println(answer[0] + " " + answer[1]);
	}
}
