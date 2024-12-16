class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for(int unit = 1; unit <= s.length()/2; unit++) { 
            int cnt = 0;
            
            int repeat = 1;
            int i = 0, j = i + unit;
            while(j+unit <= s.length()) {
                if(!check(s, unit, i, j)) {
                    if(repeat != 1) {
                        cnt += String.valueOf(repeat).length();
                    }
                         
                    repeat = 1;
                    cnt += unit;
                }
                else {
                    repeat++;
                }

                i = j;
                j += unit;
            }

            if(repeat != 1) {
                cnt += String.valueOf(repeat).length();
            }
            cnt += s.length() - i;
                
            answer = Math.min(answer, cnt);
        }

        
        return answer;
    }
    
    private static boolean check(String s, int unit, int i, int j) {
        for(int m = 0; m < unit; m++) {
            if(s.charAt(i+m) != s.charAt(j+m))
                return false;
        }
        
        return true;
    }
}
