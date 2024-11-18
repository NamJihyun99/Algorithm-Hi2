import java.util.Scanner;

public class Main {
    static int[][] matches = new int[15][2]; // 총 15개의 경기 (팀 간 매치 조합)
    static int[][] results = new int[6][3]; // 6개의 팀의 결과 (승, 무, 패)
    static boolean isPossible; // 결과가 가능한지 여부

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 미리 경기 조합 초기화 (6팀의 모든 쌍)
        int index = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                matches[index][0] = i;
                matches[index][1] = j;
                index++;
            }
        }

        for (int test = 0; test < 4; test++) { // 4개의 테스트 케이스
            isPossible = false;
            int totalMatches = 0;

            // 입력 받기
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    results[i][j] = sc.nextInt();
                    totalMatches += results[i][j];
                }
            }

            // 경기 수가 총 30경기가 아닌 경우 불가능
            if (totalMatches != 30) {
                System.out.print(0 + " ");
                continue;
            }

            // DFS 탐색 시작
            dfs(0);
            System.out.print((isPossible ? 1 : 0) + " ");
        }
    }

    // DFS를 통해 가능한 경기 결과 조합을 탐색
    static void dfs(int matchIndex) {
        // 모든 경기를 처리한 경우
        if (matchIndex == 15) {
            // 남은 승/무/패가 없는 경우에만 유효한 결과로 판단
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (results[i][j] != 0) {
                        return;
                    }
                }
            }
            isPossible = true;
            return;
        }

        int teamA = matches[matchIndex][0];
        int teamB = matches[matchIndex][1];

        // 팀 A 승리, 팀 B 패배
        if (results[teamA][0] > 0 && results[teamB][2] > 0) {
            results[teamA][0]--;
            results[teamB][2]--;
            dfs(matchIndex + 1);
            results[teamA][0]++;
            results[teamB][2]++;
        }

        // 팀 A 무승부, 팀 B 무승부
        if (results[teamA][1] > 0 && results[teamB][1] > 0) {
            results[teamA][1]--;
            results[teamB][1]--;
            dfs(matchIndex + 1);
            results[teamA][1]++;
            results[teamB][1]++;
        }

        // 팀 A 패배, 팀 B 승리
        if (results[teamA][2] > 0 && results[teamB][0] > 0) {
            results[teamA][2]--;
            results[teamB][0]--;
            dfs(matchIndex + 1);
            results[teamA][2]++;
            results[teamB][0]++;
        }
    }
}
