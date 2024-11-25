class Solution {
    static int[] max = {0, 0}; // 이모티콘 플러스 가입자 수, 이모티콘 매출액
    static int[][] userInfo;
    static int[] emoticonsInfo;
    public int[] solution(int[][] users, int[] emoticons) {
        userInfo = users;
        emoticonsInfo = emoticons;
        
        int[] initRatios = new int[emoticons.length];
        for (int i = 0; i < emoticons.length; i++) {
            initRatios[i] = 10;
        }
        
        backtracking(initRatios);
        return max;
    }
    
    private static void backtracking(int[] discountRatios) {
        int emoticonPlusCount = 0;
        int profit = 0;
        for (int i = 0; i < userInfo.length; i++) {
            int userPurchase = 0;
            for (int j = 0; j < discountRatios.length; j++) {
                if (discountRatios[j] >= userInfo[i][0]) {
                    userPurchase += emoticonsInfo[j] * (1 - 0.01 * discountRatios[j]);
                }
            }
            if (userPurchase >= userInfo[i][1]) {
                emoticonPlusCount++;
            } else {
                profit += userPurchase;
            }
        }        
        if (max[0] < emoticonPlusCount || (max[0] == emoticonPlusCount && max[1] < profit)) {
            max = new int[] {emoticonPlusCount, profit};
        }
        
        int i = discountRatios.length - 1;
        for (; i >= 0; i--) {
            if (discountRatios[i] < 40) {
                discountRatios[i] += 10;
                break;
            }
            discountRatios[i] = 10;
        }
        if (i > -1) {
            backtracking(discountRatios);
        }
        
    }
}