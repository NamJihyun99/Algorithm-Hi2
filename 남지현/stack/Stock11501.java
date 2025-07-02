import java.util.*;
import java.io.*;

// 백준 11501번 주식
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            long answer = 0l;
            for (int i=0; i<N; i++) {
                stack.addLast(Integer.valueOf(st.nextToken()));
            }
            while (!stack.isEmpty()) {
                Integer top = stack.pollLast();
                while (!stack.isEmpty() && stack.peekLast() <= top) {
                    answer = answer - stack.pollLast() + top;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
