import java.util.*;

// 프로그래머스 - 개인정보 수집 유효기간

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> term = new HashMap<>();
        Date todaysDate = new Date(today);
        for (String e: terms) {
            String[] tmp = e.split(" ");
            term.put(tmp[0].charAt(0), Integer.parseInt(tmp[1]));
        }
        for (int i=0; i<privacies.length; i++) {
            String[] tmp = privacies[i].split(" ");
            Date dueDate = new Date(tmp[0]).plusMonth(term.get(tmp[1].charAt(0)));
            if (todaysDate.isLaterThanOrEqualTo(dueDate)) {
                result.add(i+1);
            }
        }
        int[] answer = new int[result.size()];
        for (int i=0; i<result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    static class Date {
        int year;
        int month;
        int dayOfMonth;
        
        Date(String str) {
            String[] arg = str.split("\\.");
            this.year = Integer.parseInt(arg[0]);
            this.month = Integer.parseInt(arg[1]);
            this.dayOfMonth = Integer.parseInt(arg[2]);
        }
        
        Date plusMonth(int number) {
            int resultMonth = this.month+number;
            if (resultMonth > 12) {
                this.year += resultMonth/12;
                resultMonth %= 12;
                if (resultMonth==0) {
                    resultMonth = 12;
                    this.year -= 1;
                }
            }
            this.month = resultMonth;
            return this;
        }
        
        boolean isLaterThanOrEqualTo(Date dueDate) {
            if (this.year < dueDate.year) {
                return false;
            } else if (this.year > dueDate.year) {
                return true;
            } else {
                if (this.month < dueDate.month) {
                    return false;
                } else if (this.month > dueDate.month) {
                    return true;
                } else {
                    if (this.dayOfMonth < dueDate.dayOfMonth) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }
}
