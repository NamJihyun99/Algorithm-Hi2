import java.util.*;
import java.io.*;

// 백준 2504번 - 괄호의 값

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        int len = str.length();
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        int[] values = new int[31];
        int answer = 0;
        for(int i=0; i<len; i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[') {
                stack.addLast(new int[]{i, c});
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peekLast()[1]!='(') {
                    System.out.println(answer);
                    return;
                }
                int start = stack.pollLast()[0];
                int sum = 0;
                if (start+1 == i) {
                    sum = 1;
                } else {
                    for (int j=start; j<i; j++) {
                        if (values[j] != 0) {
                            sum += values[j];
                            values[j] = 0;
                        }
                    }
                }
                values[i] = sum*2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peekLast()[1]!='[') {
                    System.out.println(answer);
                    return;
                }
                int start = stack.pollLast()[0];
                int sum = 0;
                if (start+1 == i) {
                    sum = 1;
                } else {
                    for (int j=start; j<i; j++) {
                        if (values[j] != 0) {
                            sum += values[j];
                            values[j] = 0;
                        }
                    }
                }
                values[i] = sum*3;
            }
        }
        if (stack.isEmpty()) {
            answer = 0;
        } else {
            for (int i=1; i<len; i++) {
                answer += values[i];
            }
        }
        System.out.println(answer);
    }
}
