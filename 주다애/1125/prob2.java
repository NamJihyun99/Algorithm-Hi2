import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        List<Integer> lotto = Arrays.stream(lottos)
                        .boxed() // 기본 타입 -> 래퍼 타입
                        .collect(Collectors.toList());
        List<Integer> win = Arrays.stream(win_nums)
                        .boxed() // 기본 타입 -> 래퍼 타입
                        .collect(Collectors.toList());
        
        Collections.sort(lotto);
        Collections.sort(win);
        
        int t = 0;
        List<Integer> target = new ArrayList<>();
        
        for(int w : win) {
            if(lotto.contains(w)) t += 1;
            else target.add(w);
        }
        // 최소한 당첨인 경우가 최저 순위
        answer[1] = rank[t];
        
        // 무조건 1등만 가능한 경우
        if(t == 6) {
            answer[0] = 1;
            return answer;
        }
        
        // 알아볼 수 없는 번호(0으로 표시된)가 모두 당첨인 경우가 최고 순위
        int zl = 0;
        for(int w : lotto) if(w == 0) zl += 1;
        answer[0] = rank[t + zl];
        return answer;
    }
}
