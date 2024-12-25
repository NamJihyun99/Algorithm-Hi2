import java.util.*;

class Solution {
    int n, win, aWin;
    int[] sol;
    boolean[] visit;
    List<Integer> aSum, bSum;

    public int[] solution(int[][] dice) {
        n = dice.length;
        sol = new int[n / 2];
        visit = new boolean[n];
        win = 0;

        // 조합을 실행하여 최적의 A 주사위 선택
        run(0, 0, dice);
        Arrays.sort(sol); // 결과를 오름차순 정렬
        return sol;
    }

    public void run(int level, int start, int[][] dice) {
        if (level == n / 2) {
            aWin = 0;
            expectGame(dice);

            // A가 이긴 경우의 수가 더 크면 업데이트
            if (aWin > win) {
                int idx = 0;
                for (int i = 0; i < n; i++) {
                    if (visit[i]) sol[idx++] = i + 1; // 1부터 시작하는 인덱스
                }
                win = aWin;
            }
            return;
        }

        // 조합을 통해 A의 주사위를 선택
        for (int i = start; i < n; i++) {
            visit[i] = true;
            run(level + 1, i + 1, dice);
            visit[i] = false;
        }
    }

    public void expectGame(int[][] dice) {
        int[] da = new int[n / 2];
        int[] db = new int[n / 2];
        int aIdx = 0, bIdx = 0;

        for (int i = 0; i < n; i++) {
            if (visit[i]) da[aIdx++] = i;
            else db[bIdx++] = i;
        }

        aSum = new ArrayList<>();
        bSum = new ArrayList<>();

        // 각 조합의 주사위 합 계산
        calc(0, 0, da, dice, aSum);
        calc(0, 0, db, dice, bSum);

        game(aSum, bSum);
    }

    public void calc(int level, int sum, int[] nowDice, int[][] dice, List<Integer> sumList) {
        if (level == nowDice.length) {
            sumList.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            calc(level + 1, sum + dice[nowDice[level]][i], nowDice, dice, sumList);
        }
    }

    public void game(List<Integer> aSumCombi, List<Integer> bSumCombi) {
        Collections.sort(bSumCombi);

        // A의 각 합에 대해 B에서 이길 수 있는 경우의 수 계산
        for (Integer i : aSumCombi) {
            aWin += lowerBound(bSumCombi, i);
        }
    }

    public int lowerBound(List<Integer> arr, int target) {
        int start = 0, end = arr.size();
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr.get(mid) < target) start = mid + 1;
            else end = mid;
        }
        return start; // target 이상의 첫 번째 인덱스 반환
    }
}
