import java.util.*;

// 프로그래머스 주사위 고르기

class Solution {
    boolean[] team;
    int[] answer;
    int[][] numbers;
    List<Integer> scoresA, scoresB, result;
    int max;
    
    public int[] solution(int[][] dice) {
        numbers = dice;
        team = new boolean[numbers.length];
        max = -1;
        makeTeam(-1, 0);
        return answer;
    }
    
    private void makeTeam(int index, int depth) {
        if (depth == numbers.length/2) {
            List<Integer> A = new ArrayList<>();
            List<Integer> B = new ArrayList<>();
            for (int i=0; i<team.length; i++) {
                if (team[i]) A.add(i);
                else B.add(i);
            }
            scoresA = new ArrayList<>(); 
            scoresB = new ArrayList<>();
            makeScores(0, 0, A, scoresA);
            makeScores(0, 0, B, scoresB);
            int winCount = countWin(scoresA, scoresB);
            if (winCount > max) {
                max = winCount;
                answer = new int[A.size()];
                for (int i=0; i<A.size(); i++) {
                    answer[i] = A.get(i)+1;
                }
            }
            return;
        }
        for (int i=index+1; i<numbers.length; i++) {
            team[i] = true;
            makeTeam(i, depth+1);
            team[i] = false;
        }
    }
    
    private int countWin(List<Integer> scoresA, List<Integer> scoresB) {
        int count = 0;
        Collections.sort(scoresB);
        for (int i = 0; i < scoresA.size(); i++) {
            int numA = scoresA.get(i); // 더 커야 하는 값.
            int left = 0, right = scoresB.size()-1;
            int idx = -1;
            while (left <= right) {
                int mid = (left+right)/2;
                if (scoresB.get(mid) < numA) {
                    left = mid+1;
                    idx = Math.max(idx, mid);
                } else {
                    right = mid-1;
                }
            }
            if (idx != -1) {
                count += idx+1;
            }
        }
        return count;
    }
    
    private void makeScores(int depth, int sum, List<Integer> dices, List<Integer> scores) {
        if (depth == dices.size()) {
            scores.add(sum);
            return;
        }
        for (int number : numbers[dices.get(depth)]) {
            makeScores(depth+1, sum+number, dices, scores);
        }
    }
}
