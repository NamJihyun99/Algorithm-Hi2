//백준 11559번 Puyo Puyo

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static char[][] map = new char[6][12];
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[j][11 - i] = line.charAt(j);
			}
		}

		boolean isPopped;
		int answer = -1;

		do {
			isPopped = false;
			visited = new boolean[6][12];
			// check - pop
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 12; j++) {
					if (map[i][j] != '.' && !visited[i][j]) {
						Queue<int[]> temp = new LinkedList<>();
						temp.add(new int[] { i, j });

						if (findPop(i, j, map[i][j], 1, temp) < 4)
							continue;

						while (!temp.isEmpty()) {
							int[] pop = temp.poll();
							map[pop[0]][pop[1]] = '.';
						}

						isPopped = true;
					}
				}
			}

			// down
			for (int i = 0; i < 6; i++) {
				Queue<Character> queue = new LinkedList<>();
				for (int j = 0; j < 12; j++) {
					if (map[i][j] != '.') {
						queue.add(map[i][j]);
						map[i][j] = '.';
					}
				}
				int idx = 0;
				while (!queue.isEmpty()) {
					map[i][idx] = queue.poll();
					idx++;
				}
			}
			answer++;

		} while (isPopped);

		System.out.println(answer);
	}

	public static int findPop(int x, int y, char color, int cnt, Queue<int[]> temp) {
		visited[x][y] = true;

		for (int[] d : delta) {
			int nx = x + d[0];
			int ny = y + d[1];

			if (nx < 0 || nx >= 6 || ny < 0 || ny >= 12 || visited[nx][ny] || map[nx][ny] != color)
				continue;

			temp.add(new int[] { nx, ny });
			cnt = findPop(nx, ny, color, cnt + 1, temp);
		}
		return cnt;
	}
}
