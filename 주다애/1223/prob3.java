import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 빗물(골드 5)
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] t = new int[2];
		for(int i = 0; i < 2; i++) {
			t[i] = sc.nextInt();
		}
		int n = t[0];
		int m = t[1];
		int[][] arr = new int[n][m];
		for(int i = 0; i < m; i++) {
			int r = sc.nextInt();
			if(r == 0) continue;
			for(int j = n - r; j < n; j++) {
				arr[j][i] = 1;
			}
		}
		int ans = 0;
		for(int i = 0; i < n; i++) {
			List<Integer> temp = new ArrayList<>();
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 1) temp.add(j);
			}
			int size = temp.size();
			// 빗물 안 채워짐
			if(size == 1 || size == 0) continue;
			for (int k = 0; k < size - 1; k++) {
				int s = temp.get(k);
				int e = temp.get(k + 1);
				ans += (e - s - 1);
			}
		}
		System.out.println(ans);
	}
}
