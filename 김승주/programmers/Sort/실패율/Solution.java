import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        float[] reachStatus = new float[N + 1];
        float[] unclearStatus = new float[N + 1];
        for (int stage : stages) {
            for (int i = 1; (stage == N + 1)? i < stage : i <= stage ; i++) {
                reachStatus[i]++;
            }
            if (stage < N + 1) {
                unclearStatus[stage]++;
            }
        }
        Map<Integer, Float> failureRatio = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            failureRatio.put(i, (reachStatus[i] == 0L)? 0L : unclearStatus[i] / reachStatus[i]);
        }
        List<Map.Entry<Integer, Float>> entryList = new LinkedList<>(failureRatio.entrySet());
        entryList.sort((Map.Entry<Integer, Float> o1, Map.Entry<Integer, Float> o2) -> {
            if (Objects.equals(o1.getValue(), o2.getValue())) {
                return o1.getKey() - o2.getKey();
            }
            return Float.compare(o2.getValue(), o1.getValue());
        });
        int i = 0;
        int[] answer = new int[N];
        for (Map.Entry<Integer, Float> entry : entryList) {
            answer[i] = entry.getKey();
            i++;
        }
        return answer;
    }
}