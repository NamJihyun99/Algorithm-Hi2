import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] answer = solution("2009.12.28", new String[] {"A 13"}, new String[] {"2008.11.03 A"});
        for (int a : answer) {
            System.out.print(a + " ");
        }
     }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Date todaysDate = transferIntoDate(today);
        Map<Character, Integer> termsMap = new HashMap<>();

        for (String term : terms) {
            String[] splited = term.split(" ");
            termsMap.put(splited[0].charAt(0), Integer.valueOf(splited[1]));
        }

        for (int i = 0; i < privacies.length; i++) {
            Character privacyTerm = privacies[i].charAt(privacies[i].length() - 1);
            Date privacyDate = transferIntoDate(privacies[i].substring(0, privacies[i].length() - 2));
            Date expiringDate = getExpiringDate(privacyDate, termsMap.get(privacyTerm));

            if (checkWhetherPrivacyExpiredOrNot(todaysDate, expiringDate)) {
                answer.add(i + 1);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Date transferIntoDate(String s) {
        String[] splited = s.split("\\.");
        return new Date(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));
    }

    private static Date getExpiringDate(Date privacyDate, int term) {
        int expiringYear = (privacyDate.month + term) / 12 + privacyDate.year;
        int expiringMonth = (privacyDate.month + term) % 12;
        if (expiringMonth == 0) {
            expiringYear--;
            expiringMonth = 12;
        }

        if (privacyDate.day == 1) {
            if (expiringMonth == 1) {
                return new Date(expiringYear - 1, 12, 28);
            }
            return new Date(expiringYear, expiringMonth - 1, 28);
        }
        return new Date(expiringYear, expiringMonth, privacyDate.day - 1);
    }

    private static boolean checkWhetherPrivacyExpiredOrNot(Date todaysDate, Date expiringDate) {
        if (todaysDate.year == expiringDate.year) {
            if (todaysDate.month == expiringDate.month) {
                return todaysDate.day > expiringDate.day;
            } else {
                return todaysDate.month > expiringDate.month;
            }

        } else {
            return todaysDate.year > expiringDate.year;
        }
    }
    
    static class Date {
        int year;
        int month;
        int day;

        Date(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
}