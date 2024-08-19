//백준 17140번 이차원 배열과 연산

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static int r, c, k;
	static int[][] A = new int[100][100];
	static int row = 3, col = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int t = 0;
		for (; t <= 100; t++) {
			if (A[r][c] == k)
				break;

			if (row >= col) {
				R();
			} else {
				C();
			}

		}

		if (t == 101)
			System.out.println(-1);
		else
			System.out.println(t);
	}

	public static void R() {
		Map<Integer, Integer> cntMap = new HashMap<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[1] != a2[1] ? a1[1] - a2[1] : a1[0] - a2[0]);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (A[i][j] == 0)
					continue;

				cntMap.put(A[i][j], cntMap.getOrDefault(A[i][j], 0) + 1);
			}

			for (Entry<Integer, Integer> entry : cntMap.entrySet()) {
				pq.add(new int[] { entry.getKey(), entry.getValue() });
			}

			int temp = Math.min(100, pq.size() * 2);
			col = Math.max(col, temp);

			for (int j = 0; j < 100; j++) {
				if (pq.isEmpty()) {
					Arrays.fill(A[i], j, 100, 0);
					break;
				}

				int[] arr = pq.poll();
				A[i][j] = arr[0];
				A[i][++j] = arr[1];
			}
			pq.clear();
			cntMap.clear();
		}
	}

	public static void C() {
		Map<Integer, Integer> cntMap;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[1] != a2[1] ? a1[1] - a2[1] : a1[0] - a2[0]);

		for (int i = 0; i < col; i++) {
			cntMap = new HashMap<>();
			for (int j = 0; j < row; j++) {
				if (A[j][i] == 0)
					continue;

				cntMap.put(A[j][i], cntMap.getOrDefault(A[j][i], 0) + 1);
			}

			for (Entry<Integer, Integer> entry : cntMap.entrySet()) {
				pq.add(new int[] { entry.getKey(), entry.getValue() });
			}

			int temp = Math.min(100, pq.size() * 2);
			row = Math.max(row, temp);

			for (int j = 0; j < row; j++) {
				if (pq.isEmpty()) {
					A[j][i] = 0;
				} else {
					int[] arr = pq.poll();
					A[j][i] = arr[0];
					A[++j][i] = arr[1];
				}
			}
			
			pq.clear();
			cntMap.clear();
		}
	}
}
