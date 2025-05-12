import java.util.*;

// 프로그래머스 - 수식 최대화

class Solution {
    ArrayList<Long> operands = new ArrayList<>();
    ArrayList<Character> operators = new ArrayList<>();
    
    char[] ops = {'+', '-', '*'};
    boolean[] visited;
    long answer;
    
    public long solution(String expression) {
        visited = new boolean[3];
        initTokens(expression);
        answer = 0L;
        dfs(0, new char[3]);
        return answer;
    }
    
    private void dfs(int depth, char[] pr) {
        if (depth == 3) {
            List<Long> copyOperands = new LinkedList<>(operands);
            List<Character> copyOperators = new LinkedList<>(operators);
            for (int i=0; i<3; i++) {
                for (int j=0; j<copyOperators.size(); j++) {
                    if (pr[i] == copyOperators.get(j)) {
                        Long result = calculate(copyOperands.remove(j), copyOperands.remove(j), pr[i]);
                        copyOperands.add(j, result);
                        copyOperators.remove(j);
                        j--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(copyOperands.get(0)));
            return;
        }
        for (int i=0; i<3; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            pr[depth] = ops[i];
            dfs(depth+1, pr);
            visited[i] = false;
        }
    }
    
    private Long calculate(Long num1, Long num2, char op) {
        if (op == '+') {
            return num1+num2;
        } else if (op == '-') {
            return num1-num2;
        } else if (op == '*') {
            return num1*num2;
        }
        return 0L;
    }
    
    private void initTokens(String expression) {
        String operand = "";
        for (char c : expression.toCharArray()) {
            if (c=='+' || c=='-' || c=='*') {
                operands.add(Long.valueOf(operand));
                operand = "";
                operators.add(c);
            } else {
                operand += c;
            }
        }
        operands.add(Long.valueOf(operand));
    }
}
