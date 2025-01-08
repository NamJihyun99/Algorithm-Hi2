import java.util.*;

// 프로그래머스 광물 캐기
class Solution {
    
    static final int SEQ = 5;
    static final int types = 3;
    static final Map<String, Integer> mineralPriority = Map.of("diamond", 0, "iron", 1, "stone", 2);
    
    int[] pickCount;
    
    public int solution(int[] picks, String[] minerals) {
        int N = minerals.length;
        pickCount = picks;
        List<int[]> weights = new ArrayList<>();
        int sum = pickSum();
        for (int i=0; i<N; i+=SEQ) {
            int[] e = new int[]{0, 0, 0, i};
            for (int j=0; j<SEQ; j++) {
                if (i+j >= N) break;
                e[mineralPriority.get(minerals[i+j])]++;
            }
            weights.add(e);
            if (--sum == 0) {
                break;
            }
        }
        Collections.sort(weights, (arr1, arr2) -> {
            if (arr1[0]==arr2[0]) {
                if (arr1[1]==arr2[1]) {
                    return arr2[2]-arr1[2];
                } else {
                    return arr2[1]-arr1[1];
                }
            } else {
                return arr2[0]-arr1[0];
            }
        });
        int answer = 0;
        for (int[] group: weights) {
            int pick = getEnablePick();
            if (pick == -1) break;
            for (int i=0; i<SEQ; i++) {
                if (group[3]+i >= N) {
                    break;
                }
                if (mineralPriority.get(minerals[group[3]+i]) >= pick) {
                    answer += 1;
                } else if (pick==2 && mineralPriority.get(minerals[group[3]+i])==0) {
                    answer += 25;
                } else {
                    answer += 5;
                }
            }
        }
        return answer;
    }
    
    private int pickSum() {
        int sum = 0;
        for (int i=0; i<types; i++) {
            sum += pickCount[i];
        }
        return sum;
    }
    
    private int getEnablePick() {
        for (int i=0; i<types; i++) {
            if (pickCount[i] > 0) {
                pickCount[i]--;
                return i;
            }
        }
        return -1;
    }
}
