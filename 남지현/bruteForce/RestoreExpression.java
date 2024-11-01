import java.util.*;

// 프로그래머스 - 수식 복원하기

class Solution {
    public String[] solution(String[] expressions) {
        List<Operation> identities = new ArrayList<>();
        List<Operation> equations = new ArrayList<>();
        List<String> result = new ArrayList<>();
        int max = 0, count = 0;
        
        for (String input: expressions) {
            String[] tokens = input.split(" ");
            max = Math.max(max, maxDigit(tokens));
            
            Operation operation = new Operation(tokens);
            if (operation.X.equals("X")) {
                equations.add(operation);
                result.add(input);
                count++;
            } else {
                identities.add(operation);
            }
        }
        
        List<Integer> bases = new ArrayList<>();
        for (int base = max+1; base<=9; base++) {
            boolean success = true;
            for (Operation identity: identities) {
                if (identity.solve(base) != Integer.parseInt(identity.X, base)) {
                    success = false;
                    break;
                }
            }
            if (success) {
                bases.add(base);
            }
        }
        
        String[] answer = new String[count];
        
        for (int i=0; i<equations.size(); i++) {
            Operation equation = equations.get(i);
            Set<String> set = new HashSet<>();
            for (int base: bases) {
                set.add(Integer.toString(equation.solve(base), base));
            }
            if (set.size() == 1) {
                for (String x: set) {
                    answer[i] = result.get(i).replace("X", x);
                }
            } else {
                answer[i] = result.get(i).replace("X", "?");
            }
        }
        
        return answer;
    }
    
    private int maxDigit(String[] expressions) {
        int max = 0;
        for (int i=0; i<=4; i+=2) {
            if (expressions[i].equals("X")) continue;
            for (char c: expressions[i].toCharArray()) {
                max = Math.max(max, c-'0');
            }
        }
        return max;
    }
    
    static class Operation {
        
        String A;
        String B;
        String X;
        boolean plus;
        
        Operation(String[] tokens) {
            if (tokens[0].equals("X")) {
                X = tokens[0];
                if (tokens[1].equals("+")) {    // X + B = A
                    A = tokens[4];
                    B = tokens[2];
                    plus = false;
                } else {                        // X - B = A
                    A = tokens[2];
                    B = tokens[4];
                    plus = true;
                }
            } else if (tokens[2].equals("X")) {
                X = tokens[2];
                if (tokens[1].equals("+")) {    // B + X = A
                    A = tokens[4];
                    B = tokens[0];
                    plus = false;
                } else {                        // A - X = B
                    A = tokens[0];
                    B = tokens[4];
                    plus = false;
                }
            } else {
                X = tokens[4];
                if (tokens[1].equals("+")) {    // A + B = X
                    A = tokens[0];
                    B = tokens[2];
                    plus = true;
                } else {                        // A - B = X
                    A = tokens[0];
                    B = tokens[2];
                    plus = false;
                }
            }
        }
        
        int solve(int radix) {
            if (this.plus) {
                return Integer.parseInt(A, radix) + Integer.parseInt(B, radix);
            } else {
                return Integer.parseInt(A, radix) - Integer.parseInt(B, radix);
            }
        }
    }
}
