import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 감소하는 수
public class BaekJoon1038 {
	static int cnt = 0;
	static int[] path = {9,8,7,6,5,4,3,2,1,0};
	static boolean[] used = new boolean[1000000];
	static List<String> temp = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<String> result = new ArrayList<>();
		if (n >= 1023) {
			System.out.println(-1);
			return;
		}
		for (int i = 1; i <= 10; i++) {
			run(0, 0, i);
			for (int j = temp.size() - 1; j >= 0; j--) {
				result.add(temp.get(j));
			}
			temp.clear();
		}
		System.out.println(result.get(n));
	}

	private static void run(int level, int start, int depth) {
		if (level == depth) {
			temp.add(sb.toString());
			return;
		}
		for (int i = start; i < 10; i++) {
			if(used[i]) continue;
			sb.append(path[i]);
			used[i] = true;
			run(level + 1, i + 1, depth);
			sb.deleteCharAt(sb.length() - 1);
			used[i] = false;
		}
	}
}
