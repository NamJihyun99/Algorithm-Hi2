class Solution {
    public static void main(String[] args) {
        System.out.println(solution(30, new String[] {"d", "e", "bb", "aa", "ae"}));
    }

    public static String solution(long n, String[] bans) {
        long order = 1;
        int length = 1;
        for (int l = 1; l <= 11; l++) {
            long temp = findLowerByLength(0, 1, l);
            if (temp > n) {
                length = l - 1;
                break;
            }
            order = temp;
        }
        for (String ban : bans) {
            if (findOrderByString(ban) <= order) {
                order--;
            }   
        }
        return findOrder(length, order + 1, n);
    }
    
    private static long findLowerByLength(long order, int depth, int length) {
        if (depth == length) {
            return order + 1;
        }
        
        long lower = 1;
        for (int i = 0; i < depth; i++) {
            lower *= 26;
        }
        
        return findLowerByLength(order + lower, depth + 1, length);
    }
    
    private static String findOrder(int length, long currO, long targetO) {
        StringBuilder sb = new StringBuilder();
        long order = 1;
        long temp = 1;
        for (int i = 1; i < length; i++) {
            temp *= 26;
        }
        for (int i = 0; i < length; i++, temp /= 26) {
            char t = 'a';
            if (order + currO > targetO) {
                order += temp;
                sb.append(t);
                continue;
            } 
            while (order + currO + (t - 'a') <= targetO) {
                order += temp;
                t++;
            }
            sb.append(--t);
        }
        return sb.toString();
    }
    
    private static long findOrderByString(String s) {
        long order = 1;
        int length = 1;
        for (int l = 1; l <= 11; l++) {
            long temp = findLowerByLength(0, 1, l);
            if (temp > s.length()) {
                length = l - 1;
                break;
            }
            order = temp;
        }
        long temp = 1;
        for (int i = 1; i < length; i++) {
            temp *= 26;
        }
        
        for (int c = 0; c < length; c++, temp /= 26) {
            for (char t = 'a'; t <= 'z'; t++) {
                if (s.charAt(c) == t) {
                    order += (t - 'a');
                    break;
                } else {
                    order += temp;
                }   
            }
        }
        return order;   
    }
}