import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int len = stages.length;
        Arrays.sort(stages);
        List<Fail> failure = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            double cnt = 0;
            double t = 0;
            for(int j = 0; j < len; j++) {
                if(stages[j] >= i) {
                    if(stages[j] == i) cnt += 1;
                    t += 1;
                }
            }
            if(t == 0) failure.add(new Fail(i, 0.0));
            else failure.add(new Fail(i, (cnt / t)));
        }
        Collections.sort(failure, (o1, o2) -> Double.compare(o2.point, o1.point));
        for(int i = 0; i < N; i++) {
            answer[i] = failure.get(i).n;
        }
        return answer;
    }
    
    static class Fail{
        int n;
        double point;
        
        Fail(int n, double point) {
            this.n = n;
            this.point = point;
        }
        
        // public double compareTo(Fail f) {
        //     if(this.point == f.point) return this.n - f.n;
        //     return f.point - this.point;
        // }
    }
}
