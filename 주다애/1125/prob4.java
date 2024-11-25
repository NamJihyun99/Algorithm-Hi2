class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String t = Integer.toString(n, k);
        String[] temp = t.split("0");
        for(String s : temp) {
            if(s.equals("1")) continue;
            if(s.equals("")) continue;
            if(defineDecimal(s)) answer++;
        }
        return answer;
    }
    
    static boolean defineDecimal(String s) {
        long t = Long.parseLong(s);
        int sq = (int) Math.sqrt(t);
        for(int i = 2; i <= sq; i++) {
            if(t % i == 0) return false;
        }
        return true;
    }
}
