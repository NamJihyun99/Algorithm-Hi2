import java.util.*;

class Solution {
    static int[] arr = {10, 20, 30, 40};
    static int size;
    static int[] path;
    static List<Info> list;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        size = emoticons.length;
        path = new int[size];
        list = new ArrayList<>();
        run(0, users, emoticons);
        Collections.sort(list);
        answer[0] = list.get(0).sign;
        answer[1] = list.get(0).total;
        return answer;
    }
    
    static void run(int level, int[][] users, int[] emoticons) {
        if(level == size) {
            calc(users, emoticons);
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            path[level] = arr[i];
            run(level + 1, users, emoticons);
            path[level] = 0;
        }
    }
    
    static void calc(int[][] users, int[] emoticons) {
        int sign = 0;
        int total = 0;
        for(int[] u : users) {
            int dis = u[0];
            int price = u[1];
            int s = 0;
            int t = 0;
            for(int i = 0; i < size; i++) {
                int d = path[i];
                int now = emoticons[i];
                // 구매
                if(d >= dis) t += (now * (100 - d)) / 100;
            }
            // 이모티콘 구매 취소하고 플러스 서비스에 가입
            if(t >= price) sign += 1;
            // 구매 확정
            else total += t;
        }
        Info info = new Info(sign, total);
        list.add(info);
    }
    
    static class Info implements Comparable<Info> {
        int sign;
        int total;
        
        Info(int sign, int total) {
            this.sign = sign;
            this.total = total;
        }
        
        public int compareTo(Info o) {
            if(this.sign == o.sign) return o.total - this.total;
            return o.sign - this.sign;
        }
    }
}
