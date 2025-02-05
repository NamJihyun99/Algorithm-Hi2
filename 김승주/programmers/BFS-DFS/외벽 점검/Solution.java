import java.util.*;

class Solution {
    static int[] extendedWeak;
    static int[] dists;
    static int N;

    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        int len = weak.length;
        extendedWeak = new int[len * 2];

        // 원형을 선형화
        for (int i = 0; i < len; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + len] = weak[i] + n;
        }

        Arrays.sort(dist); // 친구의 이동 가능 거리를 정렬

        // 친구의 수를 1명부터 dist.length까지 늘려가며 탐색
        for (int num = 1; num <= dist.length; num++) {
            if (backtracking(new boolean[len], 0, dist.length - num, len)) {
                return num;
            }
        }

        return -1;
    }

    private static boolean backtracking(boolean[] isFixed, int start, int currDist, int len) {
        if (currDist == dists.length) {
            for (boolean fixed : isFixed) {
                if (!fixed) return false;
            }
            return true;
        }

        for (int i = 0; i < len; i++) { // 시작점은 len까지만 탐색
            Arrays.fill(isFixed, false); // 초기화

            int coverage = extendedWeak[i] + dists[currDist];
            for (int j = i; j < i + len; j++) {
                if (extendedWeak[j] <= coverage) {
                    isFixed[j % len] = true;
                } else {
                    break;
                }
            }

            if (backtracking(isFixed, i + 1, currDist + 1, len)) {
                return true;
            }
        }

        return false;
    }
}
