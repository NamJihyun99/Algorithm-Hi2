import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        int len = speeds.length;
        int k = 0;
        Queue<Integer> q = new LinkedList<>();
        int[] arr = new int[len];
        for(int i = 0; i < len; i++) {
            int re = 100 - progresses[i];
            int t = re / speeds[i];
            if(re % speeds[i] != 0) t += 1;
            arr[i] = t;
            q.offer(t);
        }
        // for(int i = 0; i < len; i++) {
        //     int cnt = 0;
        //     k = 0;
        //     for(int j = i + 1; j < len; j++) {
        //         cnt++;
        //         if(arr[i] <= arr[j]) {
        //             list.add(cnt);
        //             k++;
        //             break;
        //         }
        //     }
        // }
        int f = q.poll();
        int cnt = 1;
        while(!q.isEmpty()) {
            if(f < q.peek()) {
                list.add(cnt);
                cnt = 1; // cnt 초기화
                f = q.poll();
            }
            else {
                cnt += 1; // cnt 증가
                q.poll();
            }
        }
        list.add(cnt);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
