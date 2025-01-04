import java.io.*;

class Main {
	static int maxScore = 0;
	static int N;
	static int M;
	static int[][] paper;
	static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		paper = new int[N][M];
		visited = new boolean[N][M];

		boolean hasZero = false;
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				paper[i][j] = Integer.parseInt(row.substring(j, j + 1));
				if (paper[i][j] == 0 && (i == 0 || j == 0)) {
					hasZero = true;
				}
			}
		}

		if (hasZero) {
			findMaxScore(0);
		} else {
			if (N < M) {
				maxScore = cutHorizontally();
			} else if (N > M) {
				maxScore = cutVertically();
			} else {
				maxScore = Math.max(cutHorizontally(), cutVertically());
			}
		}
		
		System.out.println(maxScore);
	}

	private static void findMaxScore(int currScore) {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (paper[row][col] == 0 || visited[row][col]) {
					continue;
				}

				// paper[row][col]부터 시작해 세로로 자르기
				int temp = 0;
				int idx = row;
				for (; idx < N && !visited[idx][col]; idx++) {
					visited[idx][col] = true;
					temp = temp * 10 + paper[idx][col];
					findMaxScore(currScore + temp);
				}
				for (--idx ; idx >= row + 1; idx--) {
					visited[idx][col] = false;
				}

				// paper[row][col]부터 시작해 가로로 자르기
				temp = paper[row][col];
				idx = col + 1;
				for (; idx < M && !visited[row][idx]; idx++) {
					visited[row][idx] = true;
					temp = temp * 10 + paper[row][idx];
					findMaxScore(currScore + temp);
				}
				for (--idx; idx >= col; idx--) {
					visited[row][idx] = false;
				}

				return;
			}
		}
		maxScore = Math.max(maxScore, currScore);
	}

	private static int cutHorizontally() {
		int sum = 0;
		for (int row = 0; row < N; row++) {
			int temp = 0;
			for (int col = 0; col < M; col++) {
				temp = temp * 10 + paper[row][col];
			}
			sum += temp;
		}
		return sum;
	}

	private static int cutVertically() {
		int sum = 0;
		for (int col = 0; col < M; col++) {
			int temp = 0;
			for (int row = 0; row < N; row++) {
				temp = temp * 10 + paper[row][col];
			}
			sum += temp;
		}
		return sum;
	}
}