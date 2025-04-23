import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[] {-1, -1, -1, -1, -1};
        for (int place = 0; place < 5; place++) {
            List<int[]> pLocs = new ArrayList<>();
            char[][] stats = new char[5][5];
            
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    stats[i][j] = places[place][i].charAt(j);
                    // 사람이 있는 위치 정보를 pLocs에 저장
                    if (stats[i][j] == 'P') {
                        pLocs.add(new int[] {i, j});
                    }
                }
            }
            
            // 모든 사람 위치에 대해 반복
            for (int[] pLoc : pLocs) {
                if (answer[place] == 0) {
                    break;
                }
                int[] dx = {-1, 0, 1, 0};
                int[] dy = {0, -1, 0, 1};
                
                // 맨해튼 거리가 1인 위치 확인
                for (int i = 0; i < 4; i++) {
                    int nextR = dx[i] + pLoc[0];
                    int nextC = dy[i] + pLoc[1];
                    
                    if (nextR >= 0 && nextR < 5 && nextC >= 0 && nextC < 5) {
                        // 맨해튼 거리가 1인 위치에 사람이 존재하는 경우 -> 거리두기를 지키지 않은 것
                        if (stats[nextR][nextC] == 'P') {
                            answer[place] = 0;
                            break;
                        }
                        // 맨해튼 거리가 1인 위치에 빈 테이블이 존재하는 경우
                        if (stats[nextR][nextC] == 'O') {
                            // 맨해튼 거리가 2인 위치에 사람이 있는지 확인
                            for (int j = 0; j < 4; j++) {
                                int nextNextR = nextR + dx[j];
                                int nextNextC = nextC + dy[j];
                                if ((nextNextR == pLoc[0] && nextNextC == pLoc[1])
                                    || (nextNextR == nextR && nextNextC == nextC)) {
                                    continue;
                                }
                                // 맨해튼 거리가 2인 위치에 사람이 존재하는 경우 -> 거리두기를 지키지 않은 것
                                if (nextNextR >= 0 && nextNextR < 5 && nextNextC >= 0 && nextNextC < 5) {
                                    if (stats[nextNextR][nextNextC] == 'P') {
                                        answer[place] = 0;
                                        break;
                                    }
                                }
                            }
                            
                        }
                        if (answer[place] == 0) {
                            break;
                        }
                        // 맨해튼 거리가 1인 위치에 파티션이 있으면 맨해튼 거리가 2인 위치는 확인할 필요 없음.
                    }
                }
            }
            
            // 모든 P에 대해 반복이 끝난 후 answer의 값이 0이 되어 있지 않았다면 모두가 거리두기를 지켰다는 뜻.
            if (answer[place] == -1) {
                answer[place] = 1;
            }
        }
        return answer;
    }
}
