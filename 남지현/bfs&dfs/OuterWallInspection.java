import java.util.*;

// 프로그래머스 - 외벽 점검

class Solution {
    
    static final int MAX = 9;
    
    boolean[] cut;
    int[] dists, gaps;
    int answer, count;
    
    public int solution(int n, int[] weak, int[] dist) {
        cut = new boolean[weak.length];
        gaps = new int[weak.length];
        dists = dist;
        for (int i=0; i<weak.length; i++) { // gaps[i]: weak[i]와 weak[i+1] 사이의 간격
            if (i == weak.length-1) {
                gaps[i] = n-weak[i]+weak[0];
                break;
            }
            gaps[i] = weak[i+1]-weak[i];
        }
        Arrays.sort(dists);
        count = 0;
        for (int i=0; i<weak.length; i++) {
            if (dists[dists.length-1] < gaps[i]) {
                cut[i] = true;
                count++;
            }
        }
        answer = MAX;
        for (int i = count>0 ? 0 : 1; i<=dists.length; i++) { // 투입할 친구 수 = i+count
            dfs(-1, i, 0);
            if (answer != MAX) return answer;
        }
        
        return -1;
    }
    
    private void dfs(int idx, int end, int depth) {
        if (answer != MAX) {
            return;
        }
        if (depth == end) {
            List<Integer> intervals = sortedIntervals();
            boolean success = true;
            // 투입 구간 개수가 친구 수보다 많으면 불가능
            if (dists.length >= intervals.size()) {
                for (int i=0; i<intervals.size(); i++) {
                    if (dists[dists.length-i-1] < intervals.get(i)) {
                        success = false;
                        break;
                    }
                }
                if (success) {
                    answer = Math.min(answer, end+count);
                }
            }
            return;
        }
        for (int i=idx+1; i<cut.length; i++) {
            if (!cut[i]) {
                cut[i] = true; //cut[i]==true: weak[i]와 weak[i+1] 사이를 나눈다
                dfs(i, end, depth+1);
                cut[i] = false;
            }
        }
    }
    
    private List<Integer> sortedIntervals() {
        List<Integer> distances = new ArrayList<>(); // 투입 구간의 길이를 담을 리스트
        int sum = 0;
        for (int i=0; i<gaps.length; i++) {
            if (cut[i]) { // 절단 경계일 때
                distances.add(sum);
                sum = 0;
            } else {
                sum += gaps[i];
                if (i == gaps.length-1) { // 마지막 취약점과 첫번째 취약점 사이의 gap을 연결
                    if (distances.size()>0) {
                    distances.add(1, distances.get(0)+sum);
                    distances.remove(0);
                    }
                }
            }
        }
        Collections.sort(distances, Collections.reverseOrder());
        return distances;
    }
}
