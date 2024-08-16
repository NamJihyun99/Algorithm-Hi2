import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 스타트와 링크(실버 1)
public class Main {
	static int n;
	static int[][] power;
	static int[] team;
	static boolean[] used;
	static boolean[] choice;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		power = new int[n + 1][n + 1];
		team = new int[n + 1];
		used = new boolean[n + 1];
		choice = new boolean[n + 1];
		StringTokenizer st;
		// 입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		run(0, 1);
		System.out.println(min);
	}

	private static void run(int level, int start) {
		if(level == n / 2) {
			// for (int i = 0; i < n / 2; i++) {
			// 	System.out.print(team[i]);
			// }
			// System.out.println();
			int startSum = getSum(team);
			int linkSum = getSum(getNotChoice());
			min = Math.min(min, Math.abs(startSum - linkSum));
			return;
		}
		for (int i = start; i <= n; i++) {
			if(used[i]) continue;
			team[level] = i;
			choice[i] = true;
			used[i] = true;
			run(level + 1, i + 1);
			team[level] = 0;
			used[i] = false;
			choice[i] = false;
		}
	}

	private static int[] getNotChoice() {
		int[] now = new int[n / 2];
		int idx = 0;
		for (int i = 1; i <= n; i++) {
			if(!choice[i]) now[idx++] = i;
		}
		return now;
	}

	private static int getSum(int[] team) {
		int sum = 0;
		int s = n / 2;
		for (int i = 0; i < s; i++) {
			int t = team[i];
			for (int j = 0; j < s; j++) {
				if(i == j) continue;
				int m = team[j];
				sum += power[t][m];
			}
		}
		return sum;
	}
}
