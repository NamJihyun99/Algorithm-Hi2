import java.util.*;
import java.io.*;

// 백준 1918번 후위 표기식
class Main {

    static Map<Character, Integer> pr;
    static Set<Character> operators;

    private static boolean isOperand(char token) {
        return !operators.contains(token);
    }

    private static void init() {
        pr = new HashMap<>();
        operators = new HashSet<>();
        pr.put('+', 1); pr.put('-', 1); pr.put('*', 0); pr.put('/', 0);
        operators.add('+'); operators.add('-'); operators.add('*'); operators.add('/');
        operators.add('('); operators.add(')');
    }
    
    public static void main(String[] args) throws Exception {
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char token : str) {
            if (isOperand(token)) {
                sb.append(token);
            } else {
                if (stack.isEmpty()) {
                    stack.addLast(token);
                } else {
                    if (token == ')') {
                        Character ch;
                        while ((ch = stack.pollLast()) != '(') {
                            sb.append(ch);
                        }
                    }  
                    else if (token == '(' || stack.peekLast()=='(') {
                        stack.addLast(token);
                    } else if (pr.get(stack.peekLast()) <= pr.get(token)) {
                        Character top = stack.peekLast();
                        while (top!=null && top!='(' && pr.get(top)<=pr.get(token)) {
                            sb.append(stack.pollLast());
                            top = stack.peekLast();
                        }
                        stack.addLast(token);
                    } else {
                        stack.addLast(token);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        System.out.println(sb);
    }
}
