import java.util.*;
import java.io.*;

// 백준 2493 - 탑 (골드5)

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] answer = new int[N];
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i=0; i<N; i++) {
            int height = Integer.parseInt(st.nextToken());
            int[] pop = stack.pollLast();
            while (pop!=null && pop[1] < height) {
                pop = stack.pollLast();
                if (pop == null) {
                    answer[i] = 0;
                    break;
                }
            }
            if (pop!=null) {
                answer[i] = pop[0];
                stack.addLast(pop); 
            }
            stack.addLast(new int[]{i+1, height});
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
