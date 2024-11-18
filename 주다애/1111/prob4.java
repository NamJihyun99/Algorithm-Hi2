package Algorithm.day_1111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 텀 프로젝트 (백준 9466번)
public class BaekJoon9466 {
	static int[] students; // 학생들이 선택한 번호
	static boolean[] visited; // DFS 방문 여부
	static boolean[] finished; // 사이클 완료 여부
	static int count; // 팀을 이루지 못한 학생 수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine()); // 학생 수
			students = new int[n + 1];
			visited = new boolean[n + 1];
			finished = new boolean[n + 1];
			count = 0;

			// 학생이 선택한 번호 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}

			// 모든 학생에 대해 DFS 수행
			for (int i = 1; i <= n; i++) {
				if (!visited[i]) {
					dfs(i);
				}
			}

			// 팀을 이루지 못한 학생 수 출력
			sb.append(n - count).append("\n");
		}
		System.out.println(sb);
	}

	// visited가 true인데 finished가 false이면 현재 상태에서 사이클이 존재한다는 의미
	// finished가 true이면 이미 사이클을 포함한 노드이므로 다시 탐색할 필요 없음
	private static void dfs(int current) {
		visited[current] = true; // 현재 노드 방문

		int next = students[current]; // 다음 노드
		if (!visited[next]) {
			dfs(next); // 다음 노드 방문
		} else if (!finished[next]) {
			// 사이클 발견 (next가 아직 처리되지 않은 경우)
			count += 1; // 사이클의 시작점 포함
			for (int temp = next; temp != current; temp = students[temp]) {
				count++; // 사이클에 포함된 모든 노드 수 세기
			}
		}

		finished[current] = true; // 현재 노드 처리 완료
	}
}
