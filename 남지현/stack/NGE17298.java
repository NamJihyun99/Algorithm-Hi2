import java.util.*;
import java.io.*;

// 백준 17298번 오큰수
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] A = new Integer[N];
        int[] NGE = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.valueOf(st.nextToken());
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i=N-1; i>=0; i--) {
            while (!stack.isEmpty() && stack.peekLast() <= A[i]) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                NGE[i] = -1;
            } else {
                NGE[i] = stack.peekLast();
            }
            stack.addLast(A[i]);
        }
        StringBuilder answer = new StringBuilder();
        for (Integer e : NGE) {
            answer.append(e).append(" ");
        }
        System.out.println(answer);
    }
}
