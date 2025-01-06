import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] A;
    static int[] answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        answer = new int[N];
        
        st = new StringTokenizer(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i=0; i<N; i++) {
            int a = Integer.parseInt(st.nextToken());
            A[i] = a;
            
            while(!stack.isEmpty() && A[stack.peekLast()] < a) {
                int idx = stack.pollLast();
                answer[idx] = a;
            }
            stack.addLast(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            if(answer[i] == 0)
                sb.append(-1);
            else
                sb.append(answer[i]);

            sb.append(' ');
        }

        System.out.println(sb);
    }
}
