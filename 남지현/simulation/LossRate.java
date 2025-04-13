import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[][] result = new int[N][3]; // 번호, 도달, 클리어
        for (int i=0; i<N; i++) {
            result[i][0] = i+1;
        }
        for (int stage : stages) {
            for (int i=1; i<stage; i++) {
                result[i-1][1] += 1;
                result[i-1][2] += 1;
            }
            if (stage == N+1) continue;
            result[stage-1][1] += 1; 
        }
        Arrays.sort(result, (arr1, arr2) -> {
            double rate1 = 1.0*arr1[2]/arr1[1];
            double rate2 = 1.0*arr2[2]/arr2[1];
            if (rate1 < rate2) {
                return -1;
            } else if (rate1 > rate2) {
                return 1;
            } else {
                return arr1[0] - arr2[0];
            }
        });
        return Arrays.stream(result).map(r -> r[0]).mapToInt(Integer::intValue).toArray();
    }
}
