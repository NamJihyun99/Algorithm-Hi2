import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Integer> q = new Stack<>();
        
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            while (!q.isEmpty() && A[q.peek()] < A[i]) {
                A[q.pop()] = A[i];
            }
            q.push(i);
        }

        while (!q.isEmpty()) {
            A[q.pop()] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for (int e : A) {
            sb.append(e).append(" ");
        }
        System.out.println(sb.toString());
    }
}