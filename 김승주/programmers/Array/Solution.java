class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int unit = s.length() / 2; unit >= 1; unit--) {
            int consecutiveCount = 1;
            int sum = 0;
            int i = 0;
            for (String prev = ""; unit * (i + 1) <= s.length(); i++) {
                // unit개 단위로 문자열 분할
                String substring = s.substring(unit * i, unit * (i + 1));

                // 분할된 문자열이 이전 문자열과 동일하다면 압축 횟수 증가
                if (prev.equals(substring)) {
                    consecutiveCount++;
                } else {
                    if (consecutiveCount > 1) {
                        // 압축된 횟수를 누적
                        sum += getNumberLength(consecutiveCount);
                        consecutiveCount = 1;
                    }
                    // 현재 분할된 문자열의 길이를 누적
                    sum += unit;
                    // 다음 분할 문자열과 비교하기 위해 현재 문자열 임시 저장
                    prev = substring;
                }
            }
            
            if (consecutiveCount > 1) {
                sum += getNumberLength(consecutiveCount);
            }
            
            // 분할 이뤄지고 남은 문자의 개수 합산
            if (s.length() % unit != 0) {
                sum += s.length() % unit;
            }
            answer = Math.min(sum, answer);
        }
        return answer;
        
    }
    
    private static int getNumberLength(int num) {
        if (num < 10) {
            return 1;
        }
        if (num < 100) {
            return 2;
        }
        if (num < 1000) {
            return 3;
        }
        return 4;
    }
}
