import java.util.*;

// 프로그래머스 - 이모티콘 할인행사 복습

class Solution {
    
    final static int[] rates = {10, 20, 30, 40};
    
    int subscriber, sale;
    int[] discount, emoticonInfo;
    int[][] userInfo;
    
    public int[] solution(int[][] users, int[] emoticons) {
        subscriber = 0; sale = 0;
        emoticonInfo = emoticons;
        userInfo = users;
        discount = new int[emoticonInfo.length];
        dfs(0);
        return new int[]{subscriber, sale};
    }
    
    private void dfs(int depth) {
        if (depth == emoticonInfo.length) {
            int count = 0, total = 0;
            for (int[] user: userInfo) {
                int sum = 0;
                for (int i=0; i<emoticonInfo.length; i++) {
                    if (discount[i] >= user[0])
                        sum += emoticonInfo[i]*(100-discount[i])/100;
                }
                if (sum < user[1]) {
                    total += sum;
                } else {
                    count++;
                }
            }  
            if (count > subscriber) {
                subscriber = count;
                sale = total;
            } else if (count == subscriber) {
                sale = Math.max(sale, total);
            }
            return;
        }
        for (int i=0; i<4; i++) {
            discount[depth] = rates[i];
            dfs(depth+1);
            discount[depth] = 0;
        }
    }
}
