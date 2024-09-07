import java.io.*;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Element> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            // 현재 괄호열의 문자가 여는 괄호인 경우
            if (s.charAt(i) == '(' || s.charAt(i) == '[') {
                // 여는 괄호가 괄호문의 마지막 문자인 경우 닫는 괄호가 없으므로 잘못된 괄호열.
                // (s.charAt(i+1) 참조 시 StringIndexOutOfBoundsException 발생 방지.)
                if (i + 1 == s.length()) {
                    System.out.println(0);
                    return;
                }
                // 여는 괄호 바로 다음에 닫는 괄호가 있으면 상수를 의미하므로 2 또는 3을 스택에 push.
                if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
                    stack.push(new Element(true, 2));
                    i++;
                } else if (s.charAt(i) == '[' && s.charAt(i + 1) == ']') {
                    stack.push(new Element(true, 3));
                    i++;
                } else { // 상수가 아닌 *2 또는 *3을 위한 괄호라면 해당 여는 괄호를 stack에 push.
                    stack.push(new Element(false, s.charAt(i)));
                }
                continue;
            }
            
            // 현재 괄호열의 문자가 닫는 괄호인 경우 (곱셈 수행이 필요한 경우.)
            // 여는 괄호가 나타날 때까지 스택에 push된 모든 숫자를 pop해 더함.
            // 여는 괄호가 나타나면 더한 숫자에 *2 또는 *3 수행.
            int k = 0;
            if (s.charAt(i) == ')') {
                
                while (!stack.isEmpty() && stack.peek().bracket != '(') {
                    Element e = stack.pop();
                    if (!e.isNum) {
                        System.out.println(0);
                        return;
                    }
                    k += e.num;
                }
                if (stack.isEmpty()) { // '('가 스택에 존재하지 않은 경우
                    System.out.println(0);
                    return;
                }
                stack.pop(); // '('을 스택에서 제거
                k *= 2;
                stack.push(new Element(true, k)); // 연산 결과를 다시 스택에 push.
            } else if (s.charAt(i) == ']') {
                while (!stack.isEmpty() && stack.peek().bracket != '[') {
                    Element e = stack.pop();
                    if (!e.isNum) {
                        System.out.println(0);
                        return;
                    }
                    k += e.num;
                }

                if (stack.isEmpty()) { // '['가 존재하지 않은 경우
                    System.out.println(0);
                    return;
                }
                stack.pop(); // '[' 스택에서 제거
                k *= 3;
                stack.push(new Element(true, k)); // 연산 결과를 다시 스택에 push.
            } else {
                System.out.println(0);
                return;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            Element e = stack.pop();
            if (!e.isNum) {
                System.out.println(0);
                return;
            }
            result += e.num;
        }
        if (result == 0) { // 스택이 비어 있던 경우
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }

    static class Element {
        boolean isNum;
        char bracket;
        int num;

        Element(boolean isNum, char bracket) {
            this.isNum = isNum;
            this.bracket = bracket;
        }
        Element(boolean isNum, int num) {
            this.isNum = isNum;
            this.num = num;
        }
    }
}