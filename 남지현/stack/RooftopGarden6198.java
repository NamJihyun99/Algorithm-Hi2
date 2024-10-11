import java.util.*;
import java.io.*;

// 백준 6198번 - 옥상 정원 꾸미기

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int N = Integer.parseInt(bf.readLine());
        int[] heights = new int[N];
        int[] sum = new int[N];
         for (int i=0; i<N; i++) {
             heights[i] = Integer.parseInt(bf.readLine());
         }
        stack.addLast(N-1);
        for (int i=N-2; i>=0; i--) {
            int peek = i;
            while (!stack.isEmpty() && heights[stack.peekLast()]<heights[i]) {
                peek = stack.pollLast();
            }
            if (sum[peek]==0) {
                sum[i] = peek-i;
                stack.addLast(peek);
            }
            stack.addLast(i);
        }
        long answer = 0;
        for (int i=0; i<N ;i++) {
            answer += sum[i];
        }
        System.out.println(answer);
    }
}
