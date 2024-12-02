import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        List<Applicant> applicants = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            applicants.add(new Applicant(info[i].split(" ")));
        }
        
        for (int i = 0; i < query.length; i++) {
            String[] splited = query[i].split(" ");
            answer[i] = (int) applicants.stream()
                .filter(a -> (splited[0].equals("-") || splited[0].equals(a.language))
                        && (splited[2].equals("-") || splited[2].equals(a.field))
                        && (splited[4].equals("-") || splited[4].equals(a.career))
                        && (splited[6].equals("-") || splited[6].equals(a.food))
                        && (Integer.parseInt(splited[7]) <= a.score))
                .count();
        }  
        return answer;
    }
    
    
    static class Applicant {
        String language;
        String field;
        String career;
        String food;
        int score;
        
        public Applicant(String[] splited) {
            this.language = splited[0];
            this.field = splited[1];
            this.career = splited[2];
            this.food = splited[3];
            this.score = Integer.parseInt(splited[4]);
        }
    }
}