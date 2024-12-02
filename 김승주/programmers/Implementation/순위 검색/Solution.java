import java.util.*;

class Solution {

    public static void main(String[] args) {
        String[] info = {
            "java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"
        };
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        int[] result = solution(info, query);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int r : result) {
            sb.append(r).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        System.out.println(sb.toString());
    }
    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        List<Applicant> applicants = new ArrayList<>();
        for (String info1 : info) {
            applicants.add(createApplicant(info1.split(" ")));
        }
        
        for (int i = 0; i < query.length; i++) {
            String[] splited = query[i].split("( and )|( )");
            Applicant standard = createApplicant(splited);
            answer[i] = (int) applicants.stream()
                .filter(a -> (standard.language == 0 || standard.language == a.language)
                        && (standard.field == 0 || standard.field == a.field)
                        && (standard.career == 0 || standard.career == a.career)
                        && (standard.food == 0 || standard.food == a.food)
                        && (standard.score <= a.score))
                .count();
        }  
        return answer;
    }
    
    private static Applicant createApplicant(String[] splited) {
        int language = switch (splited[0]) {
            case "cpp" -> 1;
            case "java" -> 2;
            default -> 0;
        };
        int field = switch (splited[1]) {
            case "backend" -> 1;
            case "frontend" -> 2;
            default -> 0;
        };
        int career = switch (splited[2]) {
            case "junior" -> 1;
            case "senior" -> 2;
            default -> 0;
        };
        int food = switch (splited[3]) {
            case "chicken" -> 1;
            case "pizza" -> 2;
            default -> 0;
        };
        int score = Integer.parseInt(splited[4]);
        return new Applicant(language, field, career, food, score);
    }
    
    
    static class Applicant {
        int language;
        int field;
        int career;
        int food;
        int score;
        
        public Applicant(int language, int field, int career, int food, int score) {
            this.language = language;
            this.field = field;
            this.career = career;
            this.food = food;
            this.score = score;
        }
    }
}